package com.example.codexsstorm.finance.RESTclient;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import com.example.codexsstorm.finance.Constants.Constants;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by codexsstorm on 7/4/18.
 */

public class UpdateTask extends AsyncTask<String, String,String>{
    @Override
    protected String doInBackground(String... strings) {
        String BOUNDRY = "==================================";
        HttpURLConnection conn = null;
        try {

            // These strings are sent in the request body. They provide information about the file being uploaded
            String token = "token : S13FMT99AR31ZXDE";
            // This is the standard format for a multipart request
            StringBuffer requestBody = new StringBuffer();
            requestBody.append("--");
            requestBody.append(BOUNDRY);
            requestBody.append('\n');
            requestBody.append(token);
            requestBody.append('\n');
            requestBody.append("bill :"+Constants.f);
            requestBody.append('\n');
            requestBody.append("--");
            requestBody.append(BOUNDRY);
            requestBody.append("--");


            // Make a connect to the server
            URL url = new URL(Constants.CAPTURE);
            conn = (HttpURLConnection) url.openConnection();

            // Put the authentication details in the request
            conn.setRequestProperty ("token","S13FMT99AR31ZXDE");

            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDRY);

            // Send the body
            DataOutputStream dataOS = new DataOutputStream(conn.getOutputStream());
            dataOS.writeBytes(requestBody.toString());
            dataOS.flush();
            dataOS.close();

            // Ensure we got the HTTP 200 response code
            int responseCode = conn.getResponseCode();
            if (responseCode != 200) {
                throw new Exception(String.format("Received the response code %d from the URL %s", responseCode, url));
            }

            // Read the response
            InputStream is = conn.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] bytes = new byte[1024];
            int bytesRead;
            while((bytesRead = is.read(bytes)) != -1) {
                baos.write(bytes, 0, bytesRead);
            }
            byte[] bytesReceived = baos.toByteArray();
            baos.close();

            is.close();
            String response = new String(bytesReceived);

            // TODO: Do something here to handle the 'response' string

        }catch (Exception e) {
            throw new RuntimeException(e);
        }
        finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return null;

    }

}
