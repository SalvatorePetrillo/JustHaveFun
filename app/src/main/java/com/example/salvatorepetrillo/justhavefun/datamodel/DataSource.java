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


    private final static String FB_NODO_EVENTI1 = "Tutti gli eventi";


    private ValueEventListener listenerEventi;


    //Costruttore DataSource
    public DataSource(){
        elencoEventi = new Hashtable<>();
    }


    public interface UpdateListener
    {
        void eventiAggiornati();
    }

    public void iniziaOsservazioneEventi(final UpdateListener notifica)
    {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference(FB_NODO_EVENTI1);

        listenerEventi = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                elencoEventi.clear();
                for (DataSnapshot elemento:dataSnapshot.getChildren())
                {
                    Evento evento = new Evento();
                    evento.setNomeEvento(elemento.child("Nome evento").getValue(String.class));
                    evento.setDescrizioneEvento(elemento.child("Descrizione").getValue(String.class));
                    evento.setAmministratoreEvento(elemento.child("Amministratore").getValue(String.class));
                    evento.setNumeroEvento(elemento.getKey());

                    elencoEventi.put(elemento.getKey(),evento);
                }
                notifica.eventiAggiornati();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        ref.addValueEventListener(listenerEventi);
    }

    public void terminaOsservazioneStudenti()
    {
        if(listenerEventi != null)
        {
            FirebaseDatabase.getInstance().getReference(FB_NODO_EVENTI1).removeEventListener(listenerEventi);
        }
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

}