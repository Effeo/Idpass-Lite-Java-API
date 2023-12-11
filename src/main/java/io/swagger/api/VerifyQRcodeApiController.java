package io.swagger.api;

import io.swagger.model.MyCertificate;
import io.swagger.model.QrCodeAndFace;
import io.swagger.model.VerificationResponse;
import io.swagger.util.Helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.ByteString;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

import org.api.proto.Ident;
import org.api.proto.KeySet;
import org.idpass.lite.Card;
import org.idpass.lite.IDPassReader;
import org.idpass.lite.exceptions.IDPassException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import javax.imageio.ImageIO;
import org.api.proto.byteArray;


@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-12-07T12:10:18.960969171Z[GMT]")
@RestController
public class VerifyQRcodeApiController implements VerifyQRcodeApi {

    private static final Logger log = LoggerFactory.getLogger(VerifyQRcodeApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public VerifyQRcodeApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<VerificationResponse> verifyQRcodePost(@Parameter(in = ParameterIn.DEFAULT, description = "The QR code to verify with the face photo", required=true, schema=@Schema()) @Valid @RequestBody QrCodeAndFace body
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            KeySet keyset = KeySet.newBuilder()
                    .setEncryptionKey(ByteString.copyFrom(MyCertificate.getEncryptionkey()))
                    .setSignatureKey(ByteString.copyFrom(MyCertificate.getSignaturekey()))
                    .addVerificationKeys(byteArray.newBuilder()
                            .setTyp(byteArray.Typ.ED25519PUBKEY)
                            .setVal(ByteString.copyFrom(MyCertificate.getPublicVerificationKey())).build())
                    .build();

            
            try {
                IDPassReader reader = new IDPassReader(keyset, MyCertificate.getRootcerts());
                
                ByteArrayInputStream bais = new ByteArrayInputStream(body.getQrCode());     
                BufferedImage bufferedImageQrCode = ImageIO.read(bais);

                // (2) Scan the generated ID PASS Lite QR code with the reader
                Card readCard = reader.open(Helper.scanQRCode(bufferedImageQrCode));

                // (3) Biometrically authenticate into ID PASS Lite QR code ID using face recognition
                readCard.authenticateWithFace(body.getFace());

                // Private identity details shall be available when authenticated
                System.out.println(readCard.getGivenName());

                return new ResponseEntity<VerificationResponse>(HttpStatus.NOT_IMPLEMENTED);          
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            catch (IDPassException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return new ResponseEntity<VerificationResponse>(HttpStatus.NOT_IMPLEMENTED);
    }

}
