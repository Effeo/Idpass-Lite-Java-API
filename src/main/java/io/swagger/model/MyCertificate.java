package io.swagger.model;

import org.api.proto.Certificates;
import org.idpass.lite.IDPassHelper;
import org.idpass.lite.IDPassReader;
import org.idpass.lite.exceptions.IDPassException;
import org.idpass.lite.exceptions.InvalidKeyException;
import org.idpass.lite.proto.Certificate;

import java.util.Base64;
import io.swagger.DTO.MyCertificateDTO;

public class MyCertificate {
    private static byte[] rootkey;
    private static Certificate rootcert;
    private static Certificates rootcerts;
    private static Certificate childcert;
    private static Certificates certchain;
    private static byte[] signaturekey;
    private static byte[] publicVerificationKey;
    private static byte[] encryptionkey;

    private static MyCertificateDTO myCertificateDTO;

    public static void initialize(){
        // chiedere bene questa parte
        signaturekey = IDPassHelper.generateSecretSignatureKey();
        encryptionkey = IDPassHelper.generateEncryptionKey();
        
        try {
            publicVerificationKey = IDPassHelper.getPublicKey(signaturekey);
            rootkey = IDPassHelper.generateSecretSignatureKey();
            rootcert = IDPassReader.generateRootCertificate(rootkey);
            rootcerts = Certificates.newBuilder().addCert(rootcert).build();
            childcert = IDPassReader.generateChildCertificate(rootkey, publicVerificationKey);
            certchain = Certificates.newBuilder().addCert(childcert).build();

            myCertificateDTO = MyCertificate.toDTO();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IDPassException e) {
            e.printStackTrace();
        }
    }

    public static byte[] getRootkey() {
        return rootkey;
    }

    public static Certificate getRootcert() {
        return rootcert;
    }

    public static Certificates getRootcerts() {
        return rootcerts;
    }

    public static Certificate getChildcert() {
        return childcert;
    }

    public static Certificates getCertchain() {
        return certchain;
    }

    public static byte[] getSignaturekey() {
        return signaturekey;
    }

    public static byte[] getPublicVerificationKey() {
        return publicVerificationKey;
    }

    public static MyCertificateDTO getMyCertificateDTO() {
        return myCertificateDTO;
    }

    public static byte[] getEncryptionkey() {
        return encryptionkey;
    }

    private static MyCertificateDTO toDTO() {
        MyCertificateDTO myCertificateDTO = new MyCertificateDTO();

        myCertificateDTO.setRootkey(Base64.getEncoder().encodeToString(rootkey));
        myCertificateDTO.setRootcert(Base64.getEncoder().encodeToString(rootcert.toByteArray()));
        myCertificateDTO.setRootcerts(Base64.getEncoder().encodeToString(rootcerts.toByteArray()));
        myCertificateDTO.setChildcert(Base64.getEncoder().encodeToString(childcert.toByteArray()));
        myCertificateDTO.setCertchain(Base64.getEncoder().encodeToString(certchain.toByteArray()));
        myCertificateDTO.setSignaturekey(Base64.getEncoder().encodeToString(signaturekey));
        myCertificateDTO.setPublicVerificationKey(Base64.getEncoder().encodeToString(publicVerificationKey));
        myCertificateDTO.setEncryptionkey(Base64.getEncoder().encodeToString(encryptionkey));
        
        return myCertificateDTO;
    }
}