package com.example.salvatorepetrillo.justhavefun;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.salvatorepetrillo.justhavefun.datamodel.Evento;

import java.util.List;

//L'adapter ci è utile per l'interfacciamento tra la listview degli eventi e i dati creati
public class EventoAdapter {

    private Context context;
    private List<Evento> elencoEventi;

    public EventoAdapter(Context context, List<Evento> elencoEventi) {
        this.context = context;
        this.elencoEventi = elencoEventi;
    }

    // Invocato per ottenere il numero di elementi nella lista
    public int getCount(){return  elencoEventi.size();}

    // Invocato per ottenere l'iesimo elemento
    public Object getItem (int i){return elencoEventi.get(i); }

    public long getItemId(int i) {
        return 0;
    }

}
