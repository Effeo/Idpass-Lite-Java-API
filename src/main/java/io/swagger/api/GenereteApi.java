/**
 * NOTE: This class is auto generated by the swagger code generator program (3.0.51).
 * https://github.com/swagger-api/swagger-codegen
 * Do not edit the class manually.
 */
package io.swagger.api;

import io.swagger.model.MyIdent;
import org.springframework.core.io.Resource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CookieValue;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;

@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-12-07T12:10:18.960969171Z[GMT]")
@Validated
public interface GenereteApi {

    @Operation(summary = "", description = "Use this API to create the QR code.", tags={  })
    @ApiResponses(value = { 
        @ApiResponse(responseCode = "200", description = "200 response. Returns an image based on the specified format.", content = @Content(mediaType = "image/png", schema = @Schema(implementation = Resource.class))) })
    @RequestMapping(value = "/generete",
        produces = { "image/png", "image/jpg", "image/svg" }, 
        consumes = { "application/json" }, 
        method = RequestMethod.POST)
    ResponseEntity<Resource> generetePost(@NotNull @Parameter(in = ParameterIn.QUERY, description = "Format of the returned image (png, jpg, svg)." ,required=true,schema=@Schema(allowableValues={ "png", "jpg", "svg" }
)) @Valid @RequestParam(value = "format", required = true) String format
, @Parameter(in = ParameterIn.DEFAULT, description = "QR code created successfully", required=true, schema=@Schema()) @Valid @RequestBody MyIdent body
);

}

