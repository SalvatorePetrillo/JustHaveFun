package com.example.salvatorepetrillo.justhavefun.datamodel;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class DataSource extends Fragment{
    //abbiamo creato il dataSource

    private Hashtable<String,Evento> elencoEventi;

    private static DataSource istance = null;

    //Interfacciamento con Firebase
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    String FB_NODO_EVENTI1 = "Tutti gli eventi";
    DatabaseReference myRef1 = database.getReference(FB_NODO_EVENTI1);


    //Costruttore DataSource
    public DataSource(){
        elencoEventi = new Hashtable<>();
        popolaDataSource();
    }

    //Riferimento ai dati
    public static DataSource getIstance(){
        if(istance==null)
            istance = new DataSource();

        return istance;
    }

    public void addEvento(Evento evento){
        elencoEventi.put(evento.getNumeroEvento(),evento);
    }

    public void deleteEvento(String numeroEvento){
        elencoEventi.remove(numeroEvento);
    }

    public Evento getEvento(String numeroEvento){
        return elencoEventi.get(numeroEvento);
    }

    public List<Evento> getElencoEvento()
    {
        ArrayList<Evento> risultato = new ArrayList<Evento>();

        //itero tutti gli elementi per aggiungerli alla lista creata
        for(Map.Entry<String , Evento> elemento : elencoEventi.entrySet())
        {
            risultato.add(elemento.getValue());
        }
        return risultato;
    }



    //Mi servirà per popolare il DataSource
    private void popolaDataSource() {
        addEvento(new Evento("Festa in maschera", "Grandissima .....", "XXXXX"));
        addEvento(new Evento("Festa in discoteca", "Piccolissima .....", "YYYYY"));
        addEvento(new Evento("Festa a casa", "Bruttissima .....", "ZZZZ"));
        }

}
