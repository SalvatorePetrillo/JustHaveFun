package com.example.salvatorepetrillo.justhavefun;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class riga_evento extends AppCompatActivity {

    private TextView vRigaEvento;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riga_evento);

        vRigaEvento = findViewById(R.id.txtNomeCompleto);
    }
}
