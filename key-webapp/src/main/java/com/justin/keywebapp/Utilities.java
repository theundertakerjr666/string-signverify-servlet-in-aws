package com.justin.keywebapp;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

public class Utilities {
    public static PrivateKey getPrivK(String keyName) throws IOException {
        try {
            if(!keyName.contains(".der")){keyName = "private_"+keyName+".der";}
            String filename = "/home/ec2-user/jetty/jetty-base/resources/" + keyName;
            byte[] keyBytes = Files.readAllBytes(Paths.get(filename));

            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            return kf.generatePrivate(spec);
        } catch (Exception e) {
            e.printStackTrace();
            throw new IOException(e);
        }
    }

    public static PublicKey getPubK(String keyName) throws IOException {
        try {
            if(!keyName.contains(".der")){keyName = "public_"+keyName+".der";}
        String filename = "/home/ec2-user/jetty/jetty-base/resources/" + keyName;
        byte[] keyBytes = Files.readAllBytes(Paths.get(filename));

        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    } catch (Exception e) {
        e.printStackTrace();
        throw new IOException(e);
    }
    }

}
