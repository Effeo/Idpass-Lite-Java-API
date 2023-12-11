package io.swagger.api;

import io.swagger.model.MyCertificate;
import io.swagger.model.MyIdent;
import io.swagger.util.Helper;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.ByteArrayOutputStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import org.api.proto.Ident;
import org.api.proto.KeySet;
import org.api.proto.byteArray;
import org.idpass.lite.Card;
import org.idpass.lite.IDPassReader;
import org.idpass.lite.exceptions.IDPassException;
import org.idpass.lite.proto.*;
import com.google.protobuf.ByteString;
import javax.imageio.ImageIO;
import java.util.Base64;
import org.springframework.http.MediaType;
import java.nio.charset.StandardCharsets;

/*
 * chiedere riga 97
 * chiedere MyCertificate.java
 */

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-12-07T12:10:18.960969171Z[GMT]")
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

    public ResponseEntity<Resource> generetePost(@NotNull @Parameter(in = ParameterIn.QUERY, description = "Format of the returned image (png, jpg, svg)." ,required=true,schema=@Schema(allowableValues={ "png", "jpg", "svg" }
)) @Valid @RequestParam(value = "format", required = true) String format
,@Parameter(in = ParameterIn.DEFAULT, description = "QR code created successfully", required=true, schema=@Schema()) @Valid @RequestBody MyIdent body
) {
        try {
            // Chidere queta parte
            KeySet keyset = KeySet.newBuilder()
                    .setEncryptionKey(ByteString.copyFrom(MyCertificate.getEncryptionkey()))
                    .setSignatureKey(ByteString.copyFrom(MyCertificate.getSignaturekey()))
                    .addVerificationKeys(byteArray.newBuilder()
                            .setTyp(byteArray.Typ.ED25519PUBKEY)
                            .setVal(ByteString.copyFrom(MyCertificate.getPublicVerificationKey())).build())
                    .build();

            // Initialize IDPassReader object with the keyset and an optional certificate
            IDPassReader reader = new IDPassReader(keyset, MyCertificate.getRootcerts());

            // Set identity details into `Ident` object
            Ident ident = Ident.newBuilder()
                    .setPhoto(ByteString.copyFrom(body.getPhoto()))
                    .setGivenName(body.getGivenName())
                    .setSurName(body.getSurname())
                    .setPin(body.getPin())
                    .setDateOfBirth(Date.newBuilder().setYear(body.getDateOfBirth().getYear())
                            .setMonth(body.getDateOfBirth().getMonthValue())
                            .setDay(body.getDateOfBirth().getDayOfMonth()))
                    .addPubExtra(Pair.newBuilder().setKey("Sex").setValue(body.getSex().toString()))
                    .addPubExtra(Pair.newBuilder().setKey("Nationality").setValue(body.getNationality()))
                    .addPubExtra(Pair.newBuilder().setKey("Date Of Issue").setValue(body.getDateOfIssue().toString()))
                    .addPubExtra(Pair.newBuilder().setKey("Date Of Expiry").setValue(body.getDateOfExpiry().toString()))
                    .addPubExtra(Pair.newBuilder().setKey("ID").setValue(body.getId()))
                    .addPrivExtra(Pair.newBuilder().setKey("SS Number").setValue(body.getSsNumber()))
                    .build();

            // Generate a secure ID PASS Lite ID
            Card card = reader.newCard(ident, MyCertificate.getRootcerts());

            if (format.equals("png") || format.equals("jpg")) {
                BufferedImage qrCode = Helper.toBufferedImage(card);

                // Convert BufferedImage to byte array
                ByteArrayOutputStream baosQrCode = new ByteArrayOutputStream();

                FileOutputStream fos = new FileOutputStream("test." + format);
                ImageIO.write(qrCode, format, fos);

                ImageIO.write(qrCode, format, baosQrCode);
                byte[] bytesQrCode = baosQrCode.toByteArray();

                // Convert byte array of QRcode to Base64 string
                String base64StringQrCode = Base64.getEncoder().encodeToString(bytesQrCode);
                
                // Wrap byte array in ByteArrayResource
                ByteArrayResource resource = new ByteArrayResource(base64StringQrCode.getBytes(StandardCharsets.UTF_8));

                return ResponseEntity.ok()
                        .contentType(MediaType.parseMediaType("image/" + format))
                        .body((Resource) resource);
            } else if (format.equals("svg")) {
                String svgString = card.asQRCodeSVG();

                // Convert SVG string to Base64 string
                String base64StringSvg = Base64.getEncoder().encodeToString(svgString.getBytes(StandardCharsets.UTF_8));

                // Convert Base64 string back to byte array and wrap in ByteArrayResource
                ByteArrayResource resource = new ByteArrayResource(base64StringSvg.getBytes(StandardCharsets.UTF_8));

                return ResponseEntity.ok()
                        .contentType(MediaType.TEXT_PLAIN)
                        .body((Resource) resource);
                } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } catch (IOException e) {
            log.error("Couldn't serialize response for content type application/json", e);
            return new ResponseEntity<Resource>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (IDPassException e) {
            log.error("Couldn't serialize response for content type application/json", e);
            return new ResponseEntity<Resource>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
