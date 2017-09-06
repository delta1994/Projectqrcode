package com.android.alovia.projectqrcode;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class AdminArea extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_area);

        /*final EditText etU_username = (EditText) findViewById(R.id.etu_Username);
        final EditText etU_name = (EditText) findViewById(R.id.etu_Username);
        final EditText etU_email = (EditText) findViewById(R.id.etu_Username);
        final EditText etU_sername = (EditText) findViewById(R.id.etu_Age);*/
        final TextView welcomeMes = (TextView) findViewById(R.id.textVU_Welcome);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String surname = intent.getStringExtra("surname");
        String username = intent.getStringExtra("username");
        String  email = intent.getStringExtra("email");

        String message ="welcome to your user area " + name + " " +surname;
        welcomeMes.setText(message);

    }

    public void btnA_genqrcode(View view){
        Intent intent = new Intent(AdminArea.this, Generatecode.class);
        startActivity(intent);
    }

    public void btnA_Scanqrcode(View view){
        Intent intent = new Intent(AdminArea.this, Generatecode.class);
        startActivity(intent);
    }
}
