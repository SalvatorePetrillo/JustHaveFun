package com.example.salvatorepetrillo.justhavefun;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Autenticazione extends AppCompatActivity {

    private EditText vInserisciEmail;
    private EditText vInserisciPassword;
    private Button vAccedi;
    private Button vRegistrati;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autenticazione);

        vInserisciEmail = findViewById(R.id.editEmail);
        vInserisciPassword = findViewById(R.id.editPassword);
        vAccedi = findViewById(R.id.btnAccedi);
        vRegistrati = findViewById(R.id.btnRegistrati);

    }
}
