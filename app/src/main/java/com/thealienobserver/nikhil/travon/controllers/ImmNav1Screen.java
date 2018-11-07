package com.thealienobserver.nikhil.travon.controllers;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.thealienobserver.nikhil.travon.R;

public class ImmNav1Screen extends AppCompatActivity {
    private Button imm1, imm2,imm3,imm4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_immi_screen);
        imm1 = (Button) findViewById(R.id.imm1);
        imm2 = (Button) findViewById(R.id.imm2);
        imm3 = (Button) findViewById(R.id.imm3);
        imm4 = (Button) findViewById(R.id.imm4);
        imm1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(ImmNav1Screen.this, ImmiList1.class);
                startActivity(myintent);
            }
        });
        imm2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(ImmNav1Screen.this, ImmiList2.class);
                startActivity(myintent);

            }
            });
        imm3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(ImmNav1Screen.this, ImmiList3.class);
                startActivity(myintent);

            }
        });
        imm4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(ImmNav1Screen.this, ImmiList4.class);
                startActivity(myintent);

            }
        });

    }


}

//





