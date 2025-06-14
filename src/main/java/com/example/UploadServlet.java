package com.example;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;

import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.auth.credentials.StaticCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ssm.SsmClient;
import software.amazon.awssdk.services.ssm.model.*;

@WebServlet("/upload")
public class UploadServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accessKey = request.getParameter("accessKey");
        String secretKey = request.getParameter("secretKey");
        String regionStr = request.getParameter("region");
        String paramName = request.getParameter("paramName");
        String paramValue = request.getParameter("paramValue");
        String paramType = request.getParameter("paramType");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            AwsBasicCredentials awsCreds = AwsBasicCredentials.create(accessKey, secretKey);
            SsmClient ssm = SsmClient.builder()
                    .region(Region.of(regionStr))
                    .credentialsProvider(StaticCredentialsProvider.create(awsCreds))
                    .build();

            PutParameterRequest putRequest = PutParameterRequest.builder()
                    .name(paramName)
                    .value(paramValue)
                    .type(paramType.equals("SecureString") ? ParameterType.SECURE_STRING : ParameterType.STRING)
                    .overwrite(true)
                    .build();

            ssm.putParameter(putRequest);

            out.println("<h3>Parameter uploaded successfully!</h3>");

        } catch (Exception e) {
            e.printStackTrace(out);
            out.println("<h3>Failed to upload parameter.</h3>");
        }
    }
}
