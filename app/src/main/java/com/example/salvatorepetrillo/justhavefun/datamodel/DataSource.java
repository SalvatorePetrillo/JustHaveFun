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




    private final static String TAG = "DataStore";
    private final static String DB_EVENTI = "Tutti gli eventi";
    private final static String KEY_AMMINISTRATORE = "amministratore";
    private final static String KEY_DESCRIZIONE = "Descrizione";
    private ValueEventListener listenerEventi;
    private ArrayList<Evento> eventi;
    public DataSource() {
        eventi = new ArrayList<>();
    }
    public interface UpdateListener {
        void eventiAggiornati();
    }
    public void iniziaOsservazioneStudenti(final UpdateListener notifica) {

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference ref = database.getReference(DB_EVENTI);

        listenerEventi = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                eventi.clear();
                for (DataSnapshot elemento:dataSnapshot.getChildren()) {
                    Evento evento = new Evento();
                    evento.setNomeEvento(elemento.getKey());
                    evento.setDescrizioneEvento(elemento.child(KEY_DESCRIZIONE).getValue(String.class));
                    eventi.add(evento);
                }
                notifica.eventiAggiornati();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };

        ref.addValueEventListener(listenerEventi);
    }

    public void terminaOsservazioneStudenti() {
        if (listenerEventi != null)
            FirebaseDatabase.getInstance().getReference(DB_EVENTI).removeEventListener(listenerEventi);
    }
    public void aggiungiEvento(Evento evento){
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(DB_EVENTI).child(evento.getNomeEvento());
        ref.setValue(evento);
    }
    public void aggiornaEvento(Evento evento) {
        int posizione = getEventoIndex(evento.getNomeEvento());
        if (posizione == -1)
            aggiungiEvento(evento);
        else
            eventi.set(posizione, evento);
    }
    public void eliminaEvento(String nomeEvento) {
        int posizione = getEventoIndex(nomeEvento);
        if (posizione != -1)
            eventi.remove(posizione);
    }

    public Evento leggiEvento(String nomeEvento) {
        int posizione = getEventoIndex(nomeEvento);
        if (posizione == -1)
            return null;
        else
            return eventi.get(posizione);
    }

    public List<Evento> ElencoEventi() {
        return eventi;
    }
    public int numeroStudenti() {
        return eventi.size();
    }

    private int getEventoIndex(String nomeEvento) {
        boolean trovato = false;
        int index = 0;
        while (!trovato && index < eventi.size()) {
            if (eventi.get(index).getNomeEvento().equals(nomeEvento)) {
                return index;
            }
            ++index;
        }
        return -1;
    }





    private Hashtable<String,Evento> elencoEventi;

    private static DataSource istance = null;

    //Interfacciamento con Firebase
   /* FirebaseDatabase database = FirebaseDatabase.getInstance();
    String FB_NODO_EVENTI1 = "Tutti gli eventi";
    DatabaseReference myRef1 = database.getReference(FB_NODO_EVENTI1);*/


    //Costruttore DataSource
    /*public DataSource(){
        elencoEventi = new Hashtable<>();
        popolaDataSource();
    }*/

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
