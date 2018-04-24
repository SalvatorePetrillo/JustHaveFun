package com.example.salvatorepetrillo.justhavefun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.salvatorepetrillo.justhavefun.datamodel.Evento;

public class EditEventoActivity extends AppCompatActivity {

    //riferimenti alle view
    private EditText vInserisciNome;
    private EditText vInserisciDescrizione;
    private EditText vInserisciCodice;
    private Button vModifica;

    //chiave per il passaggio parametri alle activity di dettaglio
    private final String EXTRA_EVENTO = "evento";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_evento);

        //ottengo i riferimenti alle view

        vInserisciNome = findViewById(R.id.editModNomeEvento);
        vInserisciDescrizione = findViewById(R.id.editModDescrizioneEvento);
        vInserisciCodice = findViewById(R.id.editModNumeroEvento);
        vModifica = findViewById(R.id.btnModConferma);


        //mi faccio dare i dati passati e li visualizzo
        final Intent intent = getIntent();
        Evento evento = (Evento)intent.getSerializableExtra(EXTRA_EVENTO);

        if (evento != null){
            vInserisciNome.setText(evento.getNomeEvento());
            vInserisciDescrizione.setText(evento.getDescrizioneEvento());
            vInserisciCodice.setText(evento.getNumeroEvento());
        }


        //imposto il pulsante modifica
        vModifica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 Evento evento = leggiDatiEvento();
                if (evento != null) { // Dati validi
                    Intent intent = new Intent();
                    intent.putExtra(EXTRA_EVENTO, evento);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    // Avviso che i dati sono obbligatori
                    Toast.makeText(getApplicationContext(), R.string.campiObbligatori, Toast.LENGTH_LONG).show();
                }
            }
        });



    }

    private Evento leggiDatiEvento() {

        // Leggo tutti i dati immessi
        Evento evento = new Evento();
        evento.setNomeEvento(vInserisciNome.getText().toString());
        evento.setDescrizioneEvento(vInserisciDescrizione.getText().toString());
        evento.setNumeroEvento(vInserisciCodice.getText().toString());


        if (evento.getNomeEvento().length() != 0 && evento.getDescrizioneEvento().length() != 0 && evento.getNumeroEvento().length() != 0)
            return evento;
        else
            return null;
    }
}
