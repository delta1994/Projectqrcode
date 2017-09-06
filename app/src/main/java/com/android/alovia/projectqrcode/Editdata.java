package com.android.alovia.projectqrcode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

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
        final Button btnE_Edit = (Button) findViewById(R.id.btnE_edit);


        Intent intent = getIntent();
        String Number = intent.getStringExtra("number");
        String Date = intent.getStringExtra("date");
        String Locate = intent.getStringExtra("locate");
        String  Detail = intent.getStringExtra("detail");
        String  Status = intent.getStringExtra("status");

        T1.setText(Number);
        T2.setText(Date);
        T3.setText(Locate);
        T4.setText(Detail);
        T5.setText(Status);

        /*String message ="welcome to your user area " + name + " " +surname;
        welcomeMes.setText(message);*/
    }
}
