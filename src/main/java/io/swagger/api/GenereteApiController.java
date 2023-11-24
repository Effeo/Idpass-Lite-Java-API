package io.swagger.api;

import io.swagger.model.MyIdent;
import io.swagger.util.Helper;

import org.springframework.core.io.Resource;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.api.proto.Certificates;
import org.api.proto.Ident;
import org.api.proto.KeySet;
import org.api.proto.byteArray;
import org.idpass.lite.Card;
import org.idpass.lite.IDPassHelper;
import org.idpass.lite.IDPassLite;
import org.idpass.lite.IDPassReader;
import org.idpass.lite.exceptions.IDPassException;
import org.idpass.lite.proto.*;
import com.google.protobuf.ByteString;
import com.google.zxing.WriterException;
import org.apache.commons.io.IOUtils;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-11-24T12:00:48.949470763Z[GMT]")
@RestController
public class GenereteApiController implements GenereteApi {

    private static final Logger log = LoggerFactory.getLogger(GenereteApiController.class);

    private final ObjectMapper objectMapper;

    private final HttpServletRequest request;

    @org.springframework.beans.factory.annotation.Autowired
    public GenereteApiController(ObjectMapper objectMapper, HttpServletRequest request) {
        this.objectMapper = objectMapper;
        this.request = request;
    }

    public ResponseEntity<Resource> generetePost(@Parameter(in = ParameterIn.DEFAULT, description = "Course created successfully", required=true, schema=@Schema()) @Valid @RequestBody MyIdent body
,@Parameter(in = ParameterIn.QUERY, description = "Format of the returned image (png, jpg, svg)" ,schema=@Schema(allowableValues={ "png", "jpg", "svg" }
)) @Valid @RequestParam(value = "format", required = false) String format
) {
        String accept = request.getHeader("Accept");
        if (accept != null && accept.contains("application/json")) {
            try {
                byte[] encryptionkey = IDPassHelper.generateEncryptionKey();
                byte[] signaturekey = IDPassHelper.generateSecretSignatureKey();
                byte[] publicVerificationKey = IDPassHelper.getPublicKey(signaturekey);

                KeySet keyset = KeySet.newBuilder()
                    .setEncryptionKey(ByteString.copyFrom(encryptionkey))
                    .setSignatureKey(ByteString.copyFrom(signaturekey))
                    .addVerificationKeys(byteArray.newBuilder()
                        .setTyp(byteArray.Typ.ED25519PUBKEY)
                        .setVal(ByteString.copyFrom(publicVerificationKey)).build())
                    .build();

                // Generate certificates (this is optional)
                byte[] rootkey = IDPassHelper.generateSecretSignatureKey();
                Certificate rootcert = IDPassReader.generateRootCertificate(rootkey);
                Certificates rootcerts = Certificates.newBuilder().addCert(rootcert).build();
                Certificate childcert = IDPassReader.generateChildCertificate(rootkey, publicVerificationKey);
                Certificates certchain = Certificates.newBuilder().addCert(childcert).build();

                // Initialize IDPassReader object with the keyset and an optional certificate
                IDPassReader reader = new IDPassReader(keyset, rootcerts);
                
                Resource photo = body.getPhoto();
                // Read bytes from the Resource
                InputStream inputStream = photo.getInputStream();
                byte[] bytesPhoto = IOUtils.toByteArray(inputStream);

                // Set identity details into `Ident` object
                Ident ident = Ident.newBuilder()
                    .setPhoto(ByteString.copyFrom(bytesPhoto))
                    .setGivenName(body.getGivenName())
                    .setSurName(body.getSurname())
                    .setPin("1234")
                    .setDateOfBirth(Date.newBuilder().setYear(body.getDateOfBirth().getYear()).setMonth(body.getDateOfBirth().getMonthValue()).setDay(body.getDateOfBirth().getDayOfMonth()))
                    .addPubExtra(Pair.newBuilder().setKey("Sex").setValue(body.getSex().toString()))
                    .addPubExtra(Pair.newBuilder().setKey("Nationality").setValue(body.getNationality()))
                    .addPubExtra(Pair.newBuilder().setKey("Date Of Issue").setValue(body.getDateOfIssue().toString()))
                    .addPubExtra(Pair.newBuilder().setKey("Date Of Expiry").setValue(body.getDateOfExpiry().toString()))
                    .addPubExtra(Pair.newBuilder().setKey("ID").setValue(body.getId()))
                    .addPrivExtra(Pair.newBuilder().setKey("SS Number").setValue(body.getSsNumber()))
                    .build();

                // Generate a secure ID PASS Lite ID
                Card card = reader.newCard(ident, certchain);
                BufferedImage qrCode = Helper.toBufferedImage(card);
            } catch (IOException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Resource>(HttpStatus.INTERNAL_SERVER_ERROR);
            } catch (IDPassException e) {
                log.error("Couldn't serialize response for content type application/json", e);
                return new ResponseEntity<Resource>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }

        return new ResponseEntity<Resource>(HttpStatus.NOT_IMPLEMENTED);
    }

}
