package com.example.salvatorepetrillo.justhavefun;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class prenotazione extends AppCompatActivity {

    private TextView vPrenota;
    private TextView vCodice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prenotazione);

        vPrenota = findViewById(R.id.txtRisposta);
        vCodice = findViewById(R.id.txtCodice);
    }
}
