package com.example.codexsstorm.finance.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.icu.util.Calendar;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.codexsstorm.finance.Constants.Constants;
import com.example.codexsstorm.finance.Entity.ReimbursementEntity;
import com.example.codexsstorm.finance.R;
import com.example.codexsstorm.finance.RESTclient.UpdateTask;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class ReimbursementActivity extends AppCompatActivity {

    ImageButton datesetter;
    EditText etDate;
    TextView tvUploadBill;
    EditText etReason;
    EditText etPaidfor;
    EditText etAmount;
    TextView tvProceed;
    int captureflag = 0;
    int CAMERA_REQUEST = 1888;
    int type = 0;
    String picturePath = "";
    ReimbursementEntity reimbursementEntity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reimbursement);
        datesetter = (ImageButton) findViewById(R.id.setDate);
        etDate = (EditText) findViewById(R.id.etDate);
        etAmount = (EditText)findViewById(R.id.etAmount);
        etPaidfor = (EditText)findViewById(R.id.etPaidFor);
        etReason = (EditText)findViewById(R.id.etReason);
        tvUploadBill = (TextView) findViewById(R.id.tvUploadBill);
        tvProceed = (TextView)findViewById(R.id.tvProceed);
        tvUploadBill.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();
            }
        });

        datesetter.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                int yy = calendar.get(Calendar.YEAR);
                int mm = calendar.get(Calendar.MONTH);
                int dd = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePicker = new DatePickerDialog(ReimbursementActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(android.widget.DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        String date = String.valueOf(dayOfMonth) + "/" + String.valueOf(monthOfYear+1)
                                + "/" + String.valueOf(year);
                        etDate.setText(date);

                    }
                }, yy, mm, dd);
                datePicker.show();
            }
        });

        tvProceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Validation();
                if (captureflag == 1) {
                    AlertDialog.Builder builder;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        builder = new AlertDialog.Builder(ReimbursementActivity.this, android.R.style.Theme_Material_Dialog_Alert);
                    } else {
                        builder = new AlertDialog.Builder(ReimbursementActivity.this);
                    }
                    builder.setTitle("Delete entry")
                            .setMessage("Are you sure the amount of the bill is Rs"+etAmount.getText())
                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    reimbursementEntity = new ReimbursementEntity(picturePath, etDate.getText().toString(), etPaidfor.getText().toString(), etReason.getText().toString(), etAmount.getText().toString(), type,ReimbursementActivity.this);
                                    new UpdateTask(reimbursementEntity).execute();
                                }
                            })
                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(ReimbursementActivity.this,"Enter Amount Correctly",Toast.LENGTH_SHORT).show();

                                }
                            })
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .show();

                }
                else{
                    Toast.makeText(ReimbursementActivity.this,"invalid Request",Toast.LENGTH_SHORT).show();
                }
            }

        });
    }

    private void Validation() {
        if(etReason.getText().length()<1||etPaidfor.getText().length()<1||etAmount.getText().length()<1||etDate.getText().length()<1)
            captureflag = 0;
    }

    private void selectImage() {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(ReimbursementActivity.this);

        builder.setTitle("Add Photo!");

        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override

            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("Take Photo"))

                {

                    Intent cameraIntent = new  Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);

                } else if (options[item].equals("Choose from Gallery"))

                {

                    Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                    startActivityForResult(intent, 2);


                } else if (options[item].equals("Cancel")) {

                    dialog.dismiss();

                }

            }

        });

        builder.show();

    }


    @Override

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            if (requestCode == CAMERA_REQUEST) {
                if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
                    type = 1;
                    Bitmap picture = (Bitmap) data.getExtras().get("data");
                    Constants.bit = picture;
                    captureflag = 1;
                    Toast.makeText(ReimbursementActivity.this,"Successfully Uploaded",Toast.LENGTH_SHORT).show();
                }

            } else if (requestCode == 2) {

                type = 2;
                Uri selectedImage = data.getData();
                String[] filePath = {MediaStore.Images.Media.DATA};

                Cursor c = getContentResolver().query(selectedImage, filePath, null, null, null);
                c.moveToFirst();

                int columnIndex = c.getColumnIndex(filePath[0]);
                picturePath = c.getString(columnIndex);
                Log.e("Check",picturePath);
                c.close();
                captureflag = 1;
                Toast.makeText(ReimbursementActivity.this,"Successfully Uploaded",Toast.LENGTH_SHORT).show();
            }

        }
    }
}
