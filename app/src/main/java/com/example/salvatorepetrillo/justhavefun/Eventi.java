package com.example.salvatorepetrillo.justhavefun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.salvatorepetrillo.justhavefun.datamodel.DataSource;
import com.example.salvatorepetrillo.justhavefun.datamodel.Evento;

public class Eventi extends AppCompatActivity {

    //riferimenti alle view
    private Button vAggiungiEvento;
    private ListView vListaView;

    //Adapter e DataSource
    private EventoAdapter adapter;
    private DataSource dataSource;

    // Chiave per il passaggio parametri alla activity di dettaglio
    private final String EXTRA_EVENTO = "evento";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventi);

        vAggiungiEvento = findViewById(R.id.btnAddEvento);
        vListaView = findViewById(R.id.ListaEventi);

        //ottengo i riferimenti dal DataSource
        dataSource = DataSource.getIstance();


        // Azione aggiungi
        vAggiungiEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AggiungiEvento.class);
                startActivity(intent);
            }
        });

        //creo l'adapter
        adapter = new EventoAdapter(this, dataSource.getElencoEvento());
        //associo l'adapter creato alla lista da visualizzare
        vListaView.setAdapter(adapter);

        vListaView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // E' stato selezionato una riga della lista: devo visualizzare i dettagli
                // sulla nuova activity

                // Ottengo dall'adapter lo studente da visualizzare
                Evento evento = (Evento) adapter.getItem(i);

                // Creo l'Intent per passare alla activity con il dettaglio
                Intent intent = new Intent(view.getContext(), InfoEvento.class);

                intent.putExtra(EXTRA_EVENTO,evento);
                startActivity(intent);
            }
        });

    }
}
