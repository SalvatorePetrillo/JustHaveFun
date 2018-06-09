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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AggiungiEvento extends AppCompatActivity {

    private EditText vInserisciNome;
    private EditText vInserisciDescrizione;
    private EditText vInserisciCodice;
    private Button vConferma;

    // Interfacciamento con Firebase
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
    private String FB_NODO_CODICE_UTENTE =currentFirebaseUser.getUid();
    private final String FB_NODO_UTENTI = "utenti";
    private final String FB_NODO_EVENTI = "eventi";
    private final String FB_NODO_EVENTI1 = "eventi";

    private String infNome;
    private String infDescrizione;
    private String infCodice;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aggiungi_evento);

        //impostazione dei riferimenti delle view
        vInserisciNome = findViewById(R.id.editAddNomeEvento);
        vInserisciDescrizione = findViewById(R.id.editAddDescrizioneEvento);
        vInserisciCodice = findViewById(R.id.editAddNumeroEvento);
        vConferma = findViewById(R.id.btnAddConferma);

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

                    // Ottengo un riferimento al nodo da cui partire
                    DatabaseReference myRef = database.getReference(FB_NODO_UTENTI);

                    // Scrivo al nodo puntato
                    myRef.child(FB_NODO_CODICE_UTENTE).child(FB_NODO_EVENTI).child(infCodice).child("Nome evento").setValue(infNome);
                    myRef.child(FB_NODO_CODICE_UTENTE).child(FB_NODO_EVENTI).child(infCodice).child("Descrizione evento").setValue(infDescrizione);

                    DatabaseReference myRef1 = database.getReference(FB_NODO_EVENTI1);
                    myRef1.child(infCodice).child("Amministratore").setValue(FB_NODO_CODICE_UTENTE);
                    myRef1.child(infCodice).child("Descrizione").setValue(infDescrizione);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), R.string.nonSonoCompilatiTutti,Toast.LENGTH_LONG).show();
                }
            }
        });


    }


    public boolean leggiInformazioni(){
        infNome = vInserisciNome.getText().toString();
        infDescrizione = vInserisciDescrizione.getText().toString();
        infCodice = vInserisciCodice.getText().toString();

        return infNome.length() != 0 && infDescrizione.length() != 0 && infCodice.length() != 0;
    }
}

