package com.example.salvatorepetrillo.justhavefun;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import com.example.salvatorepetrillo.justhavefun.datamodel.DataSource;

public class Eventi extends AppCompatActivity {

    //riferimenti alle view
    private Button vAggiungiEvento;
    private ListView vListaView;

    //Adapter e DataSource
    private EventoAdapter adapter;
    private DataSource dataSource;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventi);

        vAggiungiEvento = findViewById(R.id.btnAddEvento);
        vListaView = findViewById(R.id.ListaEventi);

        //ottengo i riferimenti dal DataSource
        dataSource = DataSource.getIstance();

        //creo l'adapter
        //adapter = new EventoAdapter(); // Non cosa passare come secoondo parametro e non sono neanche sicuro che vada fatto qualcosa

        //vListaView.setAdapter(); //logicamente non so quale adapter settare.

    }
}
