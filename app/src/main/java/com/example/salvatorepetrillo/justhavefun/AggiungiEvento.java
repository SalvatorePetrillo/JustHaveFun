package com.example.salvatorepetrillo.justhavefun;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AggiungiEvento extends AppCompatActivity {

    private EditText vInserisciNome;
    private EditText vInserisciDescrizione;
    private EditText vInserisciCodice;
    private Button vConferma;
    private Button vIndietro;

    private String infNome;
    private String infDescrizione;
    private String infCodice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aggiungi_evento);

        //impostazione dei riferimenti delle view
        vInserisciNome = findViewById(R.id.editAddNomeEvento);
        vInserisciDescrizione = findViewById(R.id.editAddDescrizioneEvento);
        vInserisciCodice = findViewById(R.id.editAddNumeroEvento);
        vConferma = findViewById(R.id.btnAddConferma);
        vIndietro = findViewById(R.id.btnTornaIndietro);

        vConferma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                leggiInformazioni();
            }
        });


    }


    public void leggiInformazioni(){
        infNome = vInserisciNome.getText().toString();
        infDescrizione = vInserisciDescrizione.getText().toString();
        infCodice = vInserisciCodice.getText().toString();
    }
}

