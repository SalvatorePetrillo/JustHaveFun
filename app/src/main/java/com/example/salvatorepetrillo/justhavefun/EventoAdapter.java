package com.example.salvatorepetrillo.justhavefun;
//OK
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.salvatorepetrillo.justhavefun.datamodel.Evento;

import java.util.Hashtable;
import java.util.List;

//L'adapter ci è utile per l'interfacciamento tra la listview degli eventi e i dati creati
public class EventoAdapter extends BaseAdapter {

    private Context context;
    private List<Evento> elencoEventi;

    public EventoAdapter(Context context, List<Evento> elencoEventi) {
        this.context = context;
        this.elencoEventi = elencoEventi;
    }

    public void update(List<Evento> newList)
    {
        elencoEventi = newList;
        notifyDataSetChanged();
    }

    // Invocato per ottenere il numero di elementi nella lista
    public int getCount(){return  elencoEventi.size();}

    // Invocato per ottenere l'iesimo elemento
    public Evento getItem (int i){return elencoEventi.get(i); }

    public long getItemId(int i) {
        return 0;
    }

    // Invocato per ottenere la view della riga da visualizzare
    public View getView (int i, View view, ViewGroup viewGroup){

        //Provvedo a costruire il layout nel caso in cui esso non sia stato iniettato
        if (view == null){
            view = LayoutInflater.from(context).inflate(R.layout.activity_riga_evento, null);
        }

        //ottengo il nome completo corrente
        TextView vNomeCompleto = view.findViewById(R.id.txtNomeCompleto);

        //imposto i valori da visualizzare
        Evento e = elencoEventi.get(i);
        vNomeCompleto.setText(e.getNomeEvento());


        return view;
    }

    public void setElencoEventi(List<Evento> elencoEventi)
    {
        this.elencoEventi=elencoEventi;
        notifyDataSetChanged();
    }

}
