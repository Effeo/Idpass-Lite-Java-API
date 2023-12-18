package io.swagger.model;

import org.api.proto.Certificates;
import org.api.proto.KeySet;
import org.api.proto.byteArray;
import org.idpass.lite.IDPassHelper;
import org.idpass.lite.IDPassReader;
import org.idpass.lite.exceptions.IDPassException;
import org.idpass.lite.exceptions.InvalidKeyException;
import org.idpass.lite.proto.Certificate;

import com.google.protobuf.ByteString;

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

    private static KeySet keyset;
    private static IDPassReader reader;

    private static MyCertificateDTO myCertificateDTO;

    public static void initialize() {
        signaturekey = IDPassHelper.generateSecretSignatureKey();
        encryptionkey = IDPassHelper.generateEncryptionKey();      
        
        try {            
            publicVerificationKey = IDPassHelper.getPublicKey(signaturekey);

            keyset = KeySet.newBuilder()
                .setEncryptionKey(ByteString.copyFrom(encryptionkey))
                .setSignatureKey(ByteString.copyFrom(signaturekey))
                .addVerificationKeys(byteArray.newBuilder()
                        .setTyp(byteArray.Typ.ED25519PUBKEY)
                        .setVal(ByteString.copyFrom(publicVerificationKey)).build())
                .build();
            
            rootkey = IDPassHelper.generateSecretSignatureKey();
            rootcert = IDPassReader.generateRootCertificate(rootkey);
            rootcerts = Certificates.newBuilder().addCert(rootcert).build();
            childcert = IDPassReader.generateChildCertificate(rootkey, publicVerificationKey);
            certchain = Certificates.newBuilder().addCert(childcert).build();

            // Initialize IDPassReader object with the keyset and an optional certificate
            reader = new IDPassReader(keyset, MyCertificate.getRootcerts());

            myCertificateDTO = MyCertificate.toDTO();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IDPassException e) {
            e.printStackTrace();
        }
    }

    public static KeySet getKeyset() {
        return keyset;
    }

    public static IDPassReader getReader() {
        return reader;
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