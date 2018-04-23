package com.example.salvatorepetrillo.justhavefun.datamodel;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class DataSource {
    //abbiamo creato il dataSource

    private Hashtable<String,Evento> elencoEventi;

    private static DataSource istance = null;

    //Costruttore DataSource
    private DataSource(){
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
    private void popolaDataSource(){
        // come posso popolarlo in maniera tale che io possa aggiungere gli eventi dal tasto "Aggiungi evento"?
        addEvento(new Evento("Festa in maschera", "Grandissiam .....", "XXXXX"));
        addEvento(new Evento("Festa in discoteca", "Piccolissima .....", "YYYYY"));
        addEvento(new Evento("Festa a casa", "Bruttissima .....", "ZZZZ"));
    }
}
