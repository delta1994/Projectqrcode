package com.android.alovia.projectqrcode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class Editdata extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editdata);

        final EditText T1 = (EditText) findViewById(R.id.etE_number);
        final EditText T2 = (EditText) findViewById(R.id.etE_date);
        final EditText T3 = (EditText) findViewById(R.id.etE_locate);
        final EditText T4 = (EditText) findViewById(R.id.etE_detail);
        final EditText T5 = (EditText) findViewById(R.id.etE_status);
        final TextView TV3 = (TextView) findViewById(R.id.tv333);
        final Button btnE_Edit = (Button) findViewById(R.id.btnE_edit);


        Intent intent = getIntent();
        final String Number = intent.getStringExtra("number");
        final String Date = intent.getStringExtra("date");
        final String Locate = intent.getStringExtra("locate");
        final String Detail = intent.getStringExtra("detail");
        final String Status = intent.getStringExtra("status");

        T1.setText(Number);
        T2.setText(Date);
        T3.setText(Locate);
        T4.setText(Detail);
        T5.setText(Status);


        btnE_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String enumber = T1.getText().toString();
                final String edata = T2.getText().toString();
                final String elocate = T3.getText().toString();
                final String edetail = T4.getText().toString();
                final String estatus = T5.getText().toString();


                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");

                            if (success) {
                                Intent intent = new Intent(Editdata.this, AdminArea.class);

                                Editdata.this.startActivity(intent);

                                AlertDialog.Builder builder = new AlertDialog.Builder(Editdata.this);
                                builder.setMessage("Edit success")
                                        .setNegativeButton("OK", null)
                                        .create()
                                        .show();
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(Editdata.this);
                                builder.setMessage("Edit Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                EditRequest editRequest = new EditRequest(enumber, edata, elocate, edetail, estatus, responseListener);
                RequestQueue queue = Volley.newRequestQueue(Editdata.this);
                queue.add(editRequest);
            }
        });
    }
}


