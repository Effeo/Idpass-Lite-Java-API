package io.swagger.DTO;

public class ResponseDTO{
    private String qrCode;
    private String keyset;
    private String rootcerts;

    public ResponseDTO(String qrCode, String keyset, String rootcerts) {
        this.qrCode = qrCode;
        this.keyset = keyset;
        this.rootcerts = rootcerts;
    }

    public ResponseDTO() {
    }

    public String getQrCode() {
        return this.qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }

    public String getKeyset() {
        return this.keyset;
    }

    public void setKeyset(String keyset) {
        this.keyset = keyset;
    }

    public String getRootcerts() {
        return this.rootcerts;
    }

    public void setRootcerts(String rootcerts) {
        this.rootcerts = rootcerts;
    }

    public ResponseDTO qrCode(String qrCode) {
        this.qrCode = qrCode;
        return this;
    }

    public ResponseDTO keyset(String keyset) {
        this.keyset = keyset;
        return this;
    }

    public ResponseDTO rootcerts(String rootcerts) {
        this.rootcerts = rootcerts;
        return this;
    }

    @Override
    public String toString() {
        return "{" +
            " qrCode='" + getQrCode() + "'" +
            ", keyset='" + getKeyset() + "'" +
            ", rootcerts='" + getRootcerts() + "'" +
            "}";
    }
}