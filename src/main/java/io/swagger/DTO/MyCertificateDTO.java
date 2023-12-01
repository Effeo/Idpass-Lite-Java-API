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

    // constructor
    public MyCertificateDTO(String rootkey, String rootcert, String rootcerts, String childcert, String certchain, String signaturekey, String publicVerificationKey) {
        this.rootkey = rootkey;
        this.rootcert = rootcert;
        this.rootcerts = rootcerts;
        this.childcert = childcert;
        this.certchain = certchain;
        this.signaturekey = signaturekey;
        this.publicVerificationKey = publicVerificationKey;
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

    // toString
    @Override
    public String toString() {
        return "class MyCertificateDTO{\n" +
                "\t rootkey='" + rootkey + '\n' +
                "\t rootcert='" + rootcert + '\n' +
                "\t rootcerts='" + rootcerts + '\n' +
                "\t childcert='" + childcert + '\n' +
                "\t certchain='" + certchain + '\n' +
                "\t signaturekey='" + signaturekey + '\n' +
                "\t publicVerificationKey='" + publicVerificationKey + '\n' +
                '}';
    }
}
