package com.example.codexsstorm.finance.RESTclient;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.example.codexsstorm.finance.Activity.HomeActivity;
import com.example.codexsstorm.finance.Activity.ReimbursementActivity;
import com.example.codexsstorm.finance.Common.UserDetails;
import com.example.codexsstorm.finance.Constants.Constants;
import com.example.codexsstorm.finance.Entity.ReimbursementEntity;
import com.google.gson.Gson;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;

/**
 * Created by codexsstorm on 7/4/18.
 */

public class UpdateTask extends AsyncTask<String, String,String>{

    ReimbursementEntity reimbursementEntity ;

    public UpdateTask(ReimbursementEntity reimbursementEntity) {
        this.reimbursementEntity = reimbursementEntity;
    }

    @Override
    protected String doInBackground(String... strings) {
        HttpURLConnection conn = null;
        try {
           HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(Constants.CAPTURE);
            String token = UserDetails.getUserToken(reimbursementEntity.getContext());
            Log.e("Token Tes : ",token);
            MultipartEntity entity = new MultipartEntity();
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            byte data[] = null;
            if (reimbursementEntity.getType()==2) {


                Bitmap imageBitmap = (BitmapFactory.decodeFile(reimbursementEntity.getFilepath()));
                if (imageBitmap != null) {
                    imageBitmap.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                    data = bos.toByteArray();
                   // Log.e("Checking", "in if");
                    entity.addPart("bill", new ByteArrayBody(data, "image/jpeg", "test2.jpg"));
                }
            }
            else if (reimbursementEntity.getType()==1) {
                if (Constants.bit != null) {
                    Constants.bit.compress(Bitmap.CompressFormat.JPEG, 100, bos);
                    data = bos.toByteArray();
                    Log.e("Checking", "in if");
                    entity.addPart("bill", new ByteArrayBody(data, "image/jpeg", "test2.jpg"));
                }
            }


            entity.addPart("token", new StringBody(token,"text/plain", Charset.forName("UTF-8")));
            entity.addPart("paid_for", new StringBody(reimbursementEntity.getPaidFor(),"text/plain", Charset.forName("UTF-8")));
            entity.addPart("total_amount", new StringBody(reimbursementEntity.getTotalAmount(),"text/plain", Charset.forName("UTF-8")));
            entity.addPart("reason", new StringBody(reimbursementEntity.getReason(),"text/plain", Charset.forName("UTF-8")));
            entity.addPart("dob", new StringBody(reimbursementEntity.getDOB(),"text/plain", Charset.forName("UTF-8")));


            httppost.setEntity(entity);
            HttpResponse resp = httpclient.execute(httppost);
            HttpEntity resEntity = resp.getEntity();
            String string= EntityUtils.toString(resEntity);
            JSONObject parentObject = new JSONObject(string);
            int code = parentObject.getInt("code");

            Log.e("hgujyky", code+"");

            if(code==200) {
                Intent i = new Intent(reimbursementEntity.getContext(), HomeActivity.class);
                reimbursementEntity.getContext().startActivity(i);
            }

            else {
                Toast.makeText(reimbursementEntity.getContext(),"Some Problem",Toast.LENGTH_SHORT).show();
            }
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
