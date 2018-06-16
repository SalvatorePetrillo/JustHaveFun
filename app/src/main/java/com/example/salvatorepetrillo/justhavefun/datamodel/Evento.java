package com.example.salvatorepetrillo.justhavefun.datamodel;

// classe utile a costruire un evento

import java.io.Serializable;

//OK
public class Evento implements Serializable {

    //definisco gli attributi
    private String nomeEvento;
    private String descrizioneEvento;
    private String numeroEvento;
    private String amministratoreEvento;

   public Evento(){
   }

    // definisco il costruttore
    public Evento(String nomeEvento, String descrizioneEvento, String numeroEvento) {
        this.nomeEvento = nomeEvento;
        this.descrizioneEvento = descrizioneEvento;
        this.numeroEvento = numeroEvento;
        this.amministratoreEvento = amministratoreEvento;
    }

    //diamo la possibilità di prendere e settare i nostri attributi
    public String getNomeEvento (){return nomeEvento;}
    public void setNomeEvento (String nomeEvento){this.nomeEvento = nomeEvento;}

    public String getDescrizioneEvento (){return descrizioneEvento;}
    public void setDescrizioneEvento (String descrizioneEvento){this.descrizioneEvento = descrizioneEvento;}

    public String getNumeroEvento(){return  numeroEvento;}
    public void setNumeroEvento(String numeroEvento){this.numeroEvento = numeroEvento;}

    public String getAmministratoreEvento(){return  amministratoreEvento;}
    public void setAmministratoreEvento(String amministratoreEvento){this.amministratoreEvento = amministratoreEvento;}

}

