package com.justin.keywebapp;

import java.io.IOException;
// import java.security.*;
// import java.util.Base64;

// import org.eclipse.jetty.ee10.servlet.ServletHandler;
// import org.eclipse.jetty.server.Server;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class KeyServlet extends HttpServlet {
        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
            // Hardcoded ECC keys
            String myKey1 = "key1";//System.getenv("KEY1");
            String myKey2 = "key2";//System.getenv("KEY2");
            String myKey3 = "key3";//System.getenv("KEY3");
            String[] keys = {myKey1, myKey2, myKey3};
            response.setContentType("application/json");
            response.setStatus(HttpServletResponse.SC_OK);
            for (String key : keys) {
                response.getWriter().println(key);
            }
        }
        
}

