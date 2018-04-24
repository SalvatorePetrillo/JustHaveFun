package com.example.salvatorepetrillo.justhavefun;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class EditEventoActivity extends AppCompatActivity {

    private EditText vInserisciNome;
    private EditText vInserisciDescrizione;
    private EditText vInserisciCodice;
    private Button vConferma;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_evento);

        //ottengo i riferimenti alle view

        vInserisciNome = findViewById(R.id.editModNomeEvento);
        vInserisciDescrizione = findViewById(R.id.editModDescrizioneEvento);
        vInserisciCodice = findViewById(R.id.editModNumeroEvento);
        vConferma = findViewById(R.id.btnModConferma);
    }
}
