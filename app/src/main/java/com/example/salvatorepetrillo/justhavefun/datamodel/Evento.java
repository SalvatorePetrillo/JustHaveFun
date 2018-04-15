package com.example.salvatorepetrillo.justhavefun.datamodel;

// classe utile a costruire un evento
public class Evento {

    //definisco gli attributi
    private String nomeEvento;
    private String descrizioneEvento;

    // definisco il costruttore
    public Evento(String nomeEvento, String descrizioneEvento) {
        this.nomeEvento = nomeEvento;
        this.descrizioneEvento = descrizioneEvento;
    }

    //diamo la possibilità di prendere e settare i nostri attributi
    public String getNomeEvento (){return nomeEvento;}
    public void setNomeEvento (String nomeEvento){this.nomeEvento = nomeEvento;}

    public String getDescrizioneEvento (){return descrizioneEvento;}
    public void setDescrizioneEvento (String descrizioneEvento){this.descrizioneEvento = descrizioneEvento;}
}
