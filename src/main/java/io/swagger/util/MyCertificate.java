package io.swagger.util;

import org.api.proto.Certificates;
import org.idpass.lite.IDPassHelper;
import org.idpass.lite.IDPassReader;
import org.idpass.lite.exceptions.IDPassException;
import org.idpass.lite.exceptions.InvalidKeyException;
import org.idpass.lite.proto.Certificate;

public class MyCertificate {
    private static byte[] rootkey;
    private static Certificate rootcert;
    private static Certificates rootcerts;
    private static Certificate childcert;
    private static Certificates certchain;
    private static byte[] signaturekey;
    private static byte[] publicVerificationKey;

    public static void initialize(){
        // chiedere bene questa parte
        signaturekey = IDPassHelper.generateSecretSignatureKey();
        
        try {
            publicVerificationKey = IDPassHelper.getPublicKey(signaturekey);
            rootkey = IDPassHelper.generateSecretSignatureKey();
            rootcert = IDPassReader.generateRootCertificate(rootkey);
            rootcerts = Certificates.newBuilder().addCert(rootcert).build();
            childcert = IDPassReader.generateChildCertificate(rootkey, publicVerificationKey);
            certchain = Certificates.newBuilder().addCert(childcert).build();
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
}