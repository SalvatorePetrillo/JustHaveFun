package com.example.salvatorepetrillo.justhavefun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.salvatorepetrillo.justhavefun.datamodel.Evento;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class InfoEvento extends AppCompatActivity {

    private TextView vNome;
    private TextView vDescrizione;
    private Button vPrenota;


    //chiave per il passaggio parametri alla activity info (mi rappresenta quello che andrò a mettere
    // in parentesi nel getSerializableExtra

    private final String EXTRA_EVENTO = "evento";



    FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_evento);

        if (currentFirebaseUser == null){
            findViewById(R.id.btnEffettuaPrenotazione).setVisibility(View.GONE);
        }
        else {
            findViewById(R.id.btnEffettuaPrenotazione).setVisibility(View.VISIBLE);
        }
        vNome = findViewById(R.id.txtNomeEvento);
        vDescrizione = findViewById(R.id.txtDescrizioneEvento);
        vPrenota = findViewById(R.id.btnEffettuaPrenotazione);

        //come faccio a riportarmi in queste textView le informazioni inserite in aggiungi evento?

        vPrenota.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //dovremmo passare alla layout prenotazione

                Intent intent = new Intent(getApplicationContext(),prenotazione.class);
                startActivity(intent);

            }
        });



        //faccio in modo da ottenere i dati passati dalla classe aggiungiEvento
        Intent intent = getIntent();
        Evento evento = (Evento)intent.getSerializableExtra(EXTRA_EVENTO);

        if (evento != null){
            vNome.setText(evento.getNomeEvento());
            vDescrizione.setText(evento.getDescrizioneEvento());
        }

    }
}
