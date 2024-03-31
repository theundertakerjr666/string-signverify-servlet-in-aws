package com.justin.keywebapp;

import java.io.IOException;
import java.security.*;
import java.util.Base64;

import org.eclipse.jetty.ee10.servlet.ServletHandler;
import org.eclipse.jetty.server.Server;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class SignServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String keyName = request.getParameter("keyName");
        String message = request.getParameter("message");

        // Decode the base64 message
        //byte[] decodedMessage = Base64.getDecoder().decode(message);

        // Generate a signature for the message using the specified key
        // For simplicity, we'll use a hardcoded "secret" for the key
        try {
            Signature signature = Signature.getInstance("SHA1withRSA");
            // KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
            // SecureRandom random = SecureRandom.getInstance("SHA1PRNG");
            // keyGen.initialize(256, random);
            // KeyPair pair = keyGen.generateKeyPair();
            // PrivateKey priv = pair.getPrivate();
            PrivateKey priv = Utilities.getPrivK(keyName);

            signature.initSign(priv);
            signature.update(message.getBytes("UTF-8"));
            byte[] s = signature.sign();
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println(Base64.getEncoder().encodeToString(s));
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException | IOException e) {
            e.printStackTrace();
        }
    }
}
