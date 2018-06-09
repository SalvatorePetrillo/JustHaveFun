package com.example.salvatorepetrillo.justhavefun;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class prenotazione extends AppCompatActivity {

    private TextView vPrenota;
    private TextView vCodice;

    FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
    private String FB_NODO_CODICE_UTENTE =currentFirebaseUser.getUid();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prenotazione);

        vPrenota = findViewById(R.id.txtRisposta);
        vCodice = findViewById(R.id.txtCodice);

        vPrenota.setText("Il codice generato è il seguente: ");
        vCodice.setText(FB_NODO_CODICE_UTENTE);

        //dobbiamo variare il messaggio di risposta a seconda di se la prenotazione è generata correttamente o meno
        //dobbiamo generare un codice univoco da dare all'utente
    }
}
