package com.example.salvatorepetrillo.justhavefun;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.salvatorepetrillo.justhavefun.datamodel.DataSource;
import com.example.salvatorepetrillo.justhavefun.datamodel.Evento;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;


public class Eventi extends AppCompatActivity implements View.OnClickListener{

    //riferimenti alle view
    private Button vAggiungiEvento;
    private Button vLogIn;
    private Button vLogOut;
    private ListView vListaView;


    //Adapter e DataSource
    private EventoAdapter adapter;
    private DataSource dataSource;

    // Chiave per il passaggio parametri alla activity di dettaglio
    private final String EXTRA_EVENTO = "evento";

    // Costanti con i result code
    private final int REQ_DELETE_EVENTO = 1;
    private final int REQ_EDIT_EVENTO = 2;

    private String numeroEventoCorrente;

    FirebaseUser currentFirebaseUser = FirebaseAuth.getInstance().getCurrentUser() ;
    public String CODICE_UTENTE="0";


    private static int K = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eventi);

        if(K==0)
        {
            findViewById(R.id.btnAddEvento).setVisibility(View.GONE);
            K=K+1;
        }


        vAggiungiEvento = findViewById(R.id.btnAddEvento);
        vListaView = findViewById(R.id.ListaEventi);
        vLogIn = findViewById(R.id.btnQui);
        vLogOut = findViewById(R.id.btnOut);

        vLogOut.setOnClickListener(this);


        //collego la prima layout con la layout di accesso
        vLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),Autenticazione.class);
                startActivity(intent);
            }
        });

        //ottengo i riferimenti dal DataSource
        dataSource = DataSource.getIstance();

        // Azione aggiungi
        vAggiungiEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AggiungiEvento.class);
                startActivity(intent);
            }
        });

        //creo l'adapter
        adapter = new EventoAdapter(this, dataSource.getElencoEvento());


        dataSource.iniziaOsservazioneEventi(new DataSource.UpdateListener() {
            @Override
            public void eventiAggiornati() {
                adapter.update(dataSource.getElencoEvento());
            }
        });


        //associo l'adapter creato alla lista da visualizzare
        vListaView.setAdapter(adapter);

        vListaView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // E' stato selezionato una riga della lista con l'intent passerò alla infoEvento

                // Ottengo dall'adapter l'evento da visualizzare
                Evento evento = (Evento) adapter.getItem(i);

                // Creo l'Intent per passare alla activity che mi descrive l'evento
                Intent intent = new Intent(view.getContext(), InfoEvento.class);

                intent.putExtra(EXTRA_EVENTO,evento);
                startActivity(intent);
            }
        });


        // Imposto il menù a tendina sulle righe della listview
        registerForContextMenu(vListaView);

    }

    protected void onDestroy()
    {
        super.onDestroy();
        dataSource.terminaOsservazioneStudenti();
    }


    //Questo codice che segue riguarda tutto il menù a tendina
    //Mi da la possibilità di modificare o eliminare un evento
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        switch (requestCode) {

            case REQ_EDIT_EVENTO :    // Modifico l'evento
                if (resultCode == RESULT_OK  ) {   // Pulsante Modifica premuto

                    // Estraggo le informazioni sull'evento in questione
                    Evento evento = (Evento) data.getSerializableExtra(EXTRA_EVENTO);

                    if (evento != null) {
                        // Sostituisco lo studente nel datasource
                        dataSource.deleteEvento(numeroEventoCorrente);
                        dataSource.addEvento(evento);
                        // Imposto il nuovo set di dati
                        adapter.setElencoEventi(dataSource.getElencoEvento());
                    }
                }
                break;

            case REQ_DELETE_EVENTO :    // Elimino l'evento
                if (resultCode == RESULT_OK) {   // Pulsante Elimina premuto

                    // Estraggo le informazioni sull'evento in questione
                    Evento evento = (Evento) data.getSerializableExtra(EXTRA_EVENTO);

                    if (evento != null) {
                        // Elimino l'evento nel datasource
                        dataSource.deleteEvento(numeroEventoCorrente);
                        // Aggiorno l'elenco degli eventi
                        adapter.setElencoEventi(dataSource.getElencoEvento());
                    }
                }
                break;

            default:
                break;
        }

    }

    // Creazione del context menu al click prolungato sulla lista degli eventi
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.lista_eventi, menu);
    }



    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();

        Evento evento = adapter.getItem(info.position); //Chiedo l'evento all'adapter
        // All'interno del menù a tendina che si apre devo capire l'elemento selezionato

        String amm = evento.getAmministratoreEvento();

        if(currentFirebaseUser==null)
        {
            Toast.makeText(getApplicationContext(), "Non hai fatto alcun accesso. ", Toast.LENGTH_SHORT).show();
        }
        else
        {
            CODICE_UTENTE =currentFirebaseUser.getUid();
        }

        if (CODICE_UTENTE.equals(amm)) {

            switch (item.getItemId()) {

                case R.id.itemDelete:
                    // Eliminazione evento
                    dataSource.deleteEvento(adapter.getItem(info.position).getNumeroEvento());
                    adapter.setElencoEventi(dataSource.getElencoEvento());
                    return true;

                case R.id.itemEdit:
                    // Modifica evento

                    numeroEventoCorrente = evento.getNumeroEvento();
                    Intent intent = new Intent(getApplicationContext(), EditEventoActivity.class);
                    intent.putExtra(EXTRA_EVENTO, evento);
                    // Faccio partire l'activiy in modalità edit
                    startActivityForResult(intent, REQ_EDIT_EVENTO);
                    return true;

                default:
                    return super.onContextItemSelected(item);
            }

        }
        else
        {
            Toast.makeText(getApplicationContext(), "Non sei amministratore per questo evento.", Toast.LENGTH_SHORT).show();
        }

        return super.onContextItemSelected(item);
    }

    public void SignOut()
    {
        if(currentFirebaseUser != null) {
            FirebaseAuth.getInstance().signOut();
            Toast.makeText(getApplicationContext(), "Il LOG-OUT ha avuto esito positivo. ", Toast.LENGTH_SHORT).show();
            CODICE_UTENTE="0";
            currentFirebaseUser=null;
        }
        else
        {
            Toast.makeText(getApplicationContext(), "Non hai fatto alcun accesso. ", Toast.LENGTH_SHORT).show();
        }
    }

    public void onClick(View view)
    {
        if(view == vLogOut)
        {
            SignOut();
        }
    }

}
