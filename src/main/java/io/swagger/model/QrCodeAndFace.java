package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.core.io.Resource;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * QrCodeAndFace
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-12-07T12:10:18.960969171Z[GMT]")


public class QrCodeAndFace   {
  @JsonProperty("qrCode")
  private Resource qrCode = null;

  @JsonProperty("face")
  private Resource face = null;

  public QrCodeAndFace qrCode(Resource qrCode) {
    this.qrCode = qrCode;
    return this;
  }

  /**
   * Get qrCode
   * @return qrCode
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public Resource getQrCode() {
    return qrCode;
  }

  public void setQrCode(Resource qrCode) {
    this.qrCode = qrCode;
  }

  public QrCodeAndFace face(Resource face) {
    this.face = face;
    return this;
  }

  /**
   * Get face
   * @return face
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public Resource getFace() {
    return face;
  }

  public void setFace(Resource face) {
    this.face = face;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    QrCodeAndFace qrCodeAndFace = (QrCodeAndFace) o;
    return Objects.equals(this.qrCode, qrCodeAndFace.qrCode) &&
        Objects.equals(this.face, qrCodeAndFace.face);
  }

  @Override
  public int hashCode() {
    return Objects.hash(qrCode, face);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class QrCodeAndFace {\n");
    
    sb.append("    qrCode: ").append(toIndentedString(qrCode)).append("\n");
    sb.append("    face: ").append(toIndentedString(face)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
