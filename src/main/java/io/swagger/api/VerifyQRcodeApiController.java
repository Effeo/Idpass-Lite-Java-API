package io.swagger.api;

import io.swagger.model.MyCertificate;
import io.swagger.model.MyIdent;
import io.swagger.model.QrCodeAndFace;
import io.swagger.model.VerificationResponse;
import io.swagger.util.Helper;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import org.idpass.lite.Card;
import org.idpass.lite.exceptions.IDPassException;
import org.idpass.lite.exceptions.InvalidCardException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.threeten.bp.LocalDate;
import org.springframework.web.bind.annotation.RequestBody;
import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

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

    public ResponseEntity<VerificationResponse> verifyQRcodePost(
            @Parameter(in = ParameterIn.DEFAULT, description = "The QR code to verify with the face photo", required = true, schema = @Schema()) @Valid @RequestBody QrCodeAndFace body) {
        String accept = request.getHeader("Accept");

        if (accept != null && accept.contains("application/json")) {
            try {
                BufferedImage qBufferedImage = Helper.toBufferedImage(body.getQrCode());

                // (2) Scan the generated ID PASS Lite QR code with the reader
                final byte[] card = Helper.scanQRCode(qBufferedImage);
                if (card == null) {
                    return new ResponseEntity<VerificationResponse>(HttpStatus.NOT_IMPLEMENTED);
                }

                Card readCard = MyCertificate.getReader().open(card);
                
                // (3) Biometrically authenticate into ID PASS Lite QR code ID using face
                // recognition
                readCard.authenticateWithFace(body.getFace());

                VerificationResponse response = new VerificationResponse();

                MyIdent ident = new MyIdent();

                ident.setGivenName(readCard.getDetails().getGivenName());
                ident.setSurname(readCard.getDetails().getSurName());
                ident.setDateOfBirth(LocalDate.of(readCard.getDetails().getDateOfBirth().getYear(), readCard.getDetails().getDateOfBirth().getMonth(), readCard.getDetails().getDateOfBirth().getDay()));
                ident.setSex(MyIdent.SexEnum.fromValue(readCard.getCardExtras().get("Sex")));
                ident.setNationality(readCard.getCardExtras().get("Nationality"));
                ident.setDateOfIssue(LocalDate.parse(readCard.getCardExtras().get("Date Of Issue")));
                ident.setDateOfExpiry(LocalDate.parse(readCard.getCardExtras().get("Date Of Expiry")));
                ident.setId(readCard.getCardExtras().get("ID"));
                ident.setSsNumber(readCard.getCardExtras().get("SS Number"));

                response.setOutcome(true);
                response.body(ident);

                return new ResponseEntity<VerificationResponse>(response, HttpStatus.OK);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IDPassException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                VerificationResponse response = new VerificationResponse();
                response.setOutcome(false);
                response.body(new MyIdent());
                return new ResponseEntity<VerificationResponse>(response, HttpStatus.OK);
            }
        }
        return new ResponseEntity<VerificationResponse>(HttpStatus.NOT_IMPLEMENTED);
    }
}
