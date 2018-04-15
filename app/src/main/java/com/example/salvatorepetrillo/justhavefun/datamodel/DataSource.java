package com.example.salvatorepetrillo.justhavefun.datamodel;

import java.util.Hashtable;

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



    //Mi servirà per popolare il DataSource
    private void popolaDataSource(){
        // come posso popolarlo in maniera tale che io possa aggiungere gli eventi dal tasto "Aggiungi evento"?
    }
}
