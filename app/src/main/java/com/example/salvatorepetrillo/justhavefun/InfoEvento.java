package com.example.salvatorepetrillo.justhavefun;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InfoEvento extends AppCompatActivity {

    private TextView vNome;
    private TextView vDescrizione;
    private Button vPrenota;
    private Button vTornaAEventi;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_evento);

        vNome = findViewById(R.id.txtNomeEvento);
        vDescrizione = findViewById(R.id.txtDescrizioneEvento);
        vPrenota = findViewById(R.id.btnEffettuaPrenotazione);
        vTornaAEventi = findViewById(R.id.btnIndietro);

        vPrenota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        vTornaAEventi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
