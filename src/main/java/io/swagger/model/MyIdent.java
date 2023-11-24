package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.core.io.Resource;
import org.threeten.bp.LocalDate;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * MyIdent
 */
@Validated
@javax.annotation.Generated(value = "io.swagger.codegen.v3.generators.java.SpringCodegen", date = "2023-11-24T12:00:48.949470763Z[GMT]")


public class MyIdent   {
  @JsonProperty("photo")
  private Resource photo = null;

  @JsonProperty("given_name")
  private String givenName = null;

  @JsonProperty("surname")
  private String surname = null;

  @JsonProperty("pin")
  private String pin = null;

  @JsonProperty("date_of_birth")
  private LocalDate dateOfBirth = null;

  /**
   * Gets or Sets sex
   */
  public enum SexEnum {
    F("F"),
    
    M("M");

    private String value;

    SexEnum(String value) {
      this.value = value;
    }

    @Override
    @JsonValue
    public String toString() {
      return String.valueOf(value);
    }

    @JsonCreator
    public static SexEnum fromValue(String text) {
      for (SexEnum b : SexEnum.values()) {
        if (String.valueOf(b.value).equals(text)) {
          return b;
        }
      }
      return null;
    }
  }
  @JsonProperty("sex")
  private SexEnum sex = null;

  @JsonProperty("nationality")
  private String nationality = null;

  @JsonProperty("date_of_issue")
  private LocalDate dateOfIssue = null;

  @JsonProperty("date_of_expiry")
  private LocalDate dateOfExpiry = null;

  @JsonProperty("id")
  private String id = null;

  @JsonProperty("ss_number")
  private String ssNumber = null;

  public MyIdent photo(Resource photo) {
    this.photo = photo;
    return this;
  }

  /**
   * Get photo
   * @return photo
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public Resource getPhoto() {
    return photo;
  }

  public void setPhoto(Resource photo) {
    this.photo = photo;
  }

  public MyIdent givenName(String givenName) {
    this.givenName = givenName;
    return this;
  }

  /**
   * Get givenName
   * @return givenName
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getGivenName() {
    return givenName;
  }

  public void setGivenName(String givenName) {
    this.givenName = givenName;
  }

  public MyIdent surname(String surname) {
    this.surname = surname;
    return this;
  }

  /**
   * Get surname
   * @return surname
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public MyIdent pin(String pin) {
    this.pin = pin;
    return this;
  }

  /**
   * Get pin
   * @return pin
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getPin() {
    return pin;
  }

  public void setPin(String pin) {
    this.pin = pin;
  }

  public MyIdent dateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
    return this;
  }

  /**
   * Get dateOfBirth
   * @return dateOfBirth
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public MyIdent sex(SexEnum sex) {
    this.sex = sex;
    return this;
  }

  /**
   * Get sex
   * @return sex
   **/
  @Schema(required = true, description = "")
      @NotNull

    public SexEnum getSex() {
    return sex;
  }

  public void setSex(SexEnum sex) {
    this.sex = sex;
  }

  public MyIdent nationality(String nationality) {
    this.nationality = nationality;
    return this;
  }

  /**
   * Get nationality
   * @return nationality
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getNationality() {
    return nationality;
  }

  public void setNationality(String nationality) {
    this.nationality = nationality;
  }

  public MyIdent dateOfIssue(LocalDate dateOfIssue) {
    this.dateOfIssue = dateOfIssue;
    return this;
  }

  /**
   * Get dateOfIssue
   * @return dateOfIssue
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public LocalDate getDateOfIssue() {
    return dateOfIssue;
  }

  public void setDateOfIssue(LocalDate dateOfIssue) {
    this.dateOfIssue = dateOfIssue;
  }

  public MyIdent dateOfExpiry(LocalDate dateOfExpiry) {
    this.dateOfExpiry = dateOfExpiry;
    return this;
  }

  /**
   * Get dateOfExpiry
   * @return dateOfExpiry
   **/
  @Schema(required = true, description = "")
      @NotNull

    @Valid
    public LocalDate getDateOfExpiry() {
    return dateOfExpiry;
  }

  public void setDateOfExpiry(LocalDate dateOfExpiry) {
    this.dateOfExpiry = dateOfExpiry;
  }

  public MyIdent id(String id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public MyIdent ssNumber(String ssNumber) {
    this.ssNumber = ssNumber;
    return this;
  }

  /**
   * Get ssNumber
   * @return ssNumber
   **/
  @Schema(required = true, description = "")
      @NotNull

    public String getSsNumber() {
    return ssNumber;
  }

  public void setSsNumber(String ssNumber) {
    this.ssNumber = ssNumber;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    MyIdent myIdent = (MyIdent) o;
    return Objects.equals(this.photo, myIdent.photo) &&
        Objects.equals(this.givenName, myIdent.givenName) &&
        Objects.equals(this.surname, myIdent.surname) &&
        Objects.equals(this.pin, myIdent.pin) &&
        Objects.equals(this.dateOfBirth, myIdent.dateOfBirth) &&
        Objects.equals(this.sex, myIdent.sex) &&
        Objects.equals(this.nationality, myIdent.nationality) &&
        Objects.equals(this.dateOfIssue, myIdent.dateOfIssue) &&
        Objects.equals(this.dateOfExpiry, myIdent.dateOfExpiry) &&
        Objects.equals(this.id, myIdent.id) &&
        Objects.equals(this.ssNumber, myIdent.ssNumber);
  }

  @Override
  public int hashCode() {
    return Objects.hash(photo, givenName, surname, pin, dateOfBirth, sex, nationality, dateOfIssue, dateOfExpiry, id, ssNumber);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MyIdent {\n");
    
    sb.append("    photo: ").append(toIndentedString(photo)).append("\n");
    sb.append("    givenName: ").append(toIndentedString(givenName)).append("\n");
    sb.append("    surname: ").append(toIndentedString(surname)).append("\n");
    sb.append("    pin: ").append(toIndentedString(pin)).append("\n");
    sb.append("    dateOfBirth: ").append(toIndentedString(dateOfBirth)).append("\n");
    sb.append("    sex: ").append(toIndentedString(sex)).append("\n");
    sb.append("    nationality: ").append(toIndentedString(nationality)).append("\n");
    sb.append("    dateOfIssue: ").append(toIndentedString(dateOfIssue)).append("\n");
    sb.append("    dateOfExpiry: ").append(toIndentedString(dateOfExpiry)).append("\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    ssNumber: ").append(toIndentedString(ssNumber)).append("\n");
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
