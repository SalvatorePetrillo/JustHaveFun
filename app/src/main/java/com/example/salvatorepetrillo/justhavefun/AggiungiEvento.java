package com.example.salvatorepetrillo.justhavefun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.salvatorepetrillo.justhavefun.datamodel.DataSource;
import com.example.salvatorepetrillo.justhavefun.datamodel.Evento;

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
                if (leggiInformazioni()) {
                    Evento evento = new Evento(infNome, infDescrizione, infCodice);
                    DataSource dataSource = DataSource.getIstance();
                    dataSource.addEvento(evento);
                    // Creo un intent e torno ...

                    Intent intent = new Intent(getApplicationContext(),Eventi.class);
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), R.string.nonSonoCompilatiTutti,Toast.LENGTH_LONG).show();
                }
            }
        });


        //dobbiamo ancora implementare il tasto indietro ritornando al layout precedente


    }


    public boolean leggiInformazioni(){
        infNome = vInserisciNome.getText().toString();
        infDescrizione = vInserisciDescrizione.getText().toString();
        infCodice = vInserisciCodice.getText().toString();

        return infNome.length() != 0 && infDescrizione.length() != 0 && infCodice.length() != 0;
    }
}

