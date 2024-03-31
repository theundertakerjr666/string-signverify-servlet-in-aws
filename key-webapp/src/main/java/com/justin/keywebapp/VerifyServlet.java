package com.justin.keywebapp;

import java.io.IOException;
import java.security.*;
import java.util.Base64;

import org.eclipse.jetty.ee10.servlet.ServletHandler;
import org.eclipse.jetty.server.Server;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class VerifyServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String keyName = request.getParameter("keyName");
        String signature = request.getParameter("signature");
        String message = request.getParameter("message");

        // Decode the base64 message
        // byte[] decodedMessage = Base64.getDecoder().decode(message);
        // Decode the base64 signature
        // byte[] decodedSignature = Base64.getDecoder().decode(signature);

        // Generate a signature for the message using the specified key
        // For simplicity, we'll use a hardcoded "secret" for the key
        try {
            Signature sign = Signature.getInstance("SHA1withRSA");
            PublicKey pub = Utilities.getPubK(keyName);
            sign.initVerify(pub);
            sign.update(message.getBytes("UTF-8"));
            System.out.println("Signature in bytes: "+Base64.getDecoder().decode(signature.getBytes("UTF-8")));
            response.setContentType("application/json");
            boolean isVerified = sign.verify(Base64.getDecoder().decode(signature.getBytes("UTF-8")));
            System.out.println("isVerified="+isVerified);
            response.getWriter().println("isVerified="+isVerified);
            response.setStatus(HttpServletResponse.SC_OK);
        } catch (NoSuchAlgorithmException | InvalidKeyException | SignatureException | IOException e) {
            e.printStackTrace();
        }
    }
}