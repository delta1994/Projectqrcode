package com.android.alovia.projectqrcode;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.vision.barcode.Barcode;

import org.json.JSONException;
import org.json.JSONObject;

public class Backgroundscan extends AppCompatActivity {
    EditText tess;
    Button scanner;
    TextView result;
    public static final String message11 = "aaaa";
    public static final int REQUEST_CODE = 100;
    public static final int PERMISSION_REQUEST = 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_backgroundscan);
        scanner = (Button) findViewById(R.id.btn_Scan);
        result = (TextView) findViewById(R.id.tvresult);
        tess = (EditText) findViewById(R.id.et_GetText);

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST);
        }
        scanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Backgroundscan.this, Scanqrcode.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            if (data != null){
                final Barcode barcode = data.getParcelableExtra("barcode");
                final Button btnSearch = (Button) findViewById(R.id.btnB_Search);
                result.post(new Runnable() {
                    @Override
                    public void run() {
                        result.setText(barcode.displayValue);
                        tess.setText(barcode.displayValue);

                        btnSearch.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                final String getText = tess.getText().toString();

                                Response.Listener<String> responseListener = new Response.Listener<String>() {
                                    @Override
                                    public void onResponse(String response) {
                                        try {
                                            JSONObject jsonResponse = new JSONObject(response);
                                            boolean success = jsonResponse.getBoolean("success");
                                            if (success){
                                                String getText2 = jsonResponse.getString("data_number");
                                                String getText3 = jsonResponse.getString("data_date");
                                                String getText4 = jsonResponse.getString("data_locate");
                                                String getText5 = jsonResponse.getString("data_detail");
                                                String getText6 = jsonResponse.getString("data_status");

                                                Intent intent = new Intent(Backgroundscan.this, Editdata.class);

                                                intent.putExtra("number", getText2);
                                                intent.putExtra("date", getText3);
                                                intent.putExtra("locate", getText4);
                                                intent.putExtra("detail", getText5);
                                                intent.putExtra("status", getText6);
                                                Backgroundscan.this.startActivity(intent);

                                                AlertDialog.Builder builder = new AlertDialog.Builder(Backgroundscan.this);
                                                builder.setMessage("Search success")
                                                        .setNegativeButton("OK", null)
                                                        .create()
                                                        .show();
                                            }
                                            else {
                                                AlertDialog.Builder builder = new AlertDialog.Builder(Backgroundscan.this);
                                                builder.setMessage("Search Failed")
                                                        .setNegativeButton("Retry", null)
                                                        .create()
                                                        .show();
                                            }
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                };
                                Backgroundsearch backgroundsearch = new Backgroundsearch(getText, responseListener);
                                RequestQueue queue = Volley.newRequestQueue(Backgroundscan.this);
                                queue.add(backgroundsearch);
                            }
                        });
/*try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success){
                                String name = jsonResponse.getString("name");
                                String surname = jsonResponse.getString("surname");
                                String email = jsonResponse.getString("email");

                                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                                builder.setMessage("Login success")
                                        .setNegativeButton("OK", null)
                                        .create()
                                        .show();
                                Intent intent = new Intent(Login.this, AdminArea.class);

                                intent.putExtra("name", name);
                                intent.putExtra("username", username);
                                intent.putExtra("surname", surname);
                                intent.putExtra("email", email);

                                Login.this.startActivity(intent);
                            }
                            else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
                                builder.setMessage("Login Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };*/

                        /*String message = tess.getText().toString();
                        Intent intent = new Intent(Backgroundscan.this, AdminArea.class);
                        intent.putExtra(message11, message);
                        startActivity(intent);*///ค้างไว้ก่อน ไม่แน่ใจต้องทำ
                    }
                });
            }


        }
    }


}
