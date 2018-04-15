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
    public static DataSource getIstance{
        if(istance==null)
            istance = new DataSource();
        return istance;
    }

    public void addEvento(Evento evento){
        elencoEventi.put()
    }


    //Mi servirà per popolare il DataSource
    private void popolaDataSource(){

    }
}
