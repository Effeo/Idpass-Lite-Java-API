package io.swagger.DTO;

/**
 * MyCertificateDTO
 */

public class MyCertificateDTO {
    private String rootkey;
    private String rootcert;
    private String rootcerts;
    private String childcert;
    private String certchain;
    private String signaturekey;
    private String publicVerificationKey;
    private String encryptionkey;

    // constructor
    public MyCertificateDTO(String rootkey, String rootcert, String rootcerts, String childcert, String certchain, String signaturekey, String publicVerificationKey, String encryptionkey) {
        this.rootkey = rootkey;
        this.rootcert = rootcert;
        this.rootcerts = rootcerts;
        this.childcert = childcert;
        this.certchain = certchain;
        this.signaturekey = signaturekey;
        this.publicVerificationKey = publicVerificationKey;
        this.encryptionkey = encryptionkey;
    }

    // default constructor
    public MyCertificateDTO() {
    }

    // getters
    public String getRootkey() {
        return rootkey;
    }

    public String getRootcert() {
        return rootcert;
    }

    public String getRootcerts() {
        return rootcerts;
    }

    public String getChildcert() {
        return childcert;
    }

    public String getCertchain() {
        return certchain;
    }

    public String getSignaturekey() {
        return signaturekey;
    }

    public String getPublicVerificationKey() {
        return publicVerificationKey;
    }

    public String getEncryptionkey() {
        return encryptionkey;
    }

    // setters
    public void setRootkey(String rootkey) {
        this.rootkey = rootkey;
    }

    public void setRootcert(String rootcert) {
        this.rootcert = rootcert;
    }

    public void setRootcerts(String rootcerts) {
        this.rootcerts = rootcerts;
    }

    public void setChildcert(String childcert) {
        this.childcert = childcert;
    }

    public void setCertchain(String certchain) {
        this.certchain = certchain;
    }

    public void setSignaturekey(String signaturekey) {
        this.signaturekey = signaturekey;
    }

    public void setPublicVerificationKey(String publicVerificationKey) {
        this.publicVerificationKey = publicVerificationKey;
    }

    public void setEncryptionkey(String encryptionkey) {
        this.encryptionkey = encryptionkey;
    }

    @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class MyCertificateDTO {\n");

    sb.append("    rootkey: ").append(toIndentedString(rootkey)).append("\n");
    sb.append("    rootcert: ").append(toIndentedString(rootcert)).append("\n");
    sb.append("    rootcerts: ").append(toIndentedString(rootcerts)).append("\n");
    sb.append("    childcert: ").append(toIndentedString(childcert)).append("\n");
    sb.append("    certchain: ").append(toIndentedString(certchain)).append("\n");
    sb.append("    signaturekey: ").append(toIndentedString(signaturekey)).append("\n");
    sb.append("    publicVerificatonKey: ").append(toIndentedString(publicVerificationKey)).append("\n");
    sb.append("    encryptionkey: ").append(toIndentedString(encryptionkey)).append("\n");
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
