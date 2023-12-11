package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.model.MyIdent;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * VerificationResponse
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-12-07T12:10:18.960969171Z[GMT]")


public class VerificationResponse   {
  @JsonProperty("outcome")
  private Boolean outcome = null;

  @JsonProperty("body")
  private MyIdent body = null;

  public VerificationResponse outcome(Boolean outcome) {
    this.outcome = outcome;
    return this;
  }

  /**
   * Returns true if the outcome is positive, false otherwise.
   * @return outcome
   **/
  @Schema(description = "Returns true if the outcome is positive, false otherwise.")
  
    public Boolean isOutcome() {
    return outcome;
  }

  public void setOutcome(Boolean outcome) {
    this.outcome = outcome;
  }

  public VerificationResponse body(MyIdent body) {
    this.body = body;
    return this;
  }

  /**
   * Get body
   * @return body
   **/
  @Schema(description = "")
  
    @Valid
    public MyIdent getBody() {
    return body;
  }

  public void setBody(MyIdent body) {
    this.body = body;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    VerificationResponse verificationResponse = (VerificationResponse) o;
    return Objects.equals(this.outcome, verificationResponse.outcome) &&
        Objects.equals(this.body, verificationResponse.body);
  }

  @Override
  public int hashCode() {
    return Objects.hash(outcome, body);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class VerificationResponse {\n");
    
    sb.append("    outcome: ").append(toIndentedString(outcome)).append("\n");
    sb.append("    body: ").append(toIndentedString(body)).append("\n");
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
