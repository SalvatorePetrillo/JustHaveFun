package com.example.salvatorepetrillo.justhavefun;


import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Autenticazione extends AppCompatActivity implements View.OnClickListener {

    private EditText vInserisciEmail;
    private EditText vInserisciPassword;
    private Button vAccedi;
    private Button vRegistrati;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autenticazione);

        firebaseAuth = FirebaseAuth.getInstance();

        vInserisciEmail = findViewById(R.id.editEmail);
        vInserisciPassword = findViewById(R.id.editPassword);
        vAccedi = findViewById(R.id.btnAccedi);
        vRegistrati = findViewById(R.id.btnRegistrati);

        vRegistrati.setOnClickListener(this);
        vAccedi.setOnClickListener(this);

        }

        private  void registerUser()
        {
            String email = vInserisciEmail.getText().toString();
            String password = vInserisciPassword.getText().toString();

            if(TextUtils.isEmpty(email))
            {
                Toast.makeText(getApplicationContext(),"Per favore inserisci l'indirizzo email",Toast.LENGTH_SHORT).show();
                return;
            }
            if(TextUtils.isEmpty(password))
            {
                Toast.makeText(getApplicationContext(),"Per favore inserisci la password",Toast.LENGTH_SHORT).show();
                return;
            }

            firebaseAuth.createUserWithEmailAndPassword(email,password).
                    addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(getApplicationContext(),"La registrazione è andata a buon fine.",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),Eventi.class);
                                startActivity(intent);
                                findViewById(R.id.btnAddEvento).setVisibility(View.VISIBLE);
                                findViewById(R.id.btnEffettuaPrenotazione).setVisibility(View.VISIBLE);
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"La registrazione non è andata a buon fine,riprova.",Toast.LENGTH_SHORT).show();
                            }

                        }
                    });

        }

        private void userLogin()
        {
            String email = vInserisciEmail.getText().toString();
            String password = vInserisciPassword.getText().toString();

            if(TextUtils.isEmpty(email))
            {
                Toast.makeText(getApplicationContext(),"Per favore inserisci l'indirizzo email",Toast.LENGTH_SHORT).show();
                return;
            }
            if(TextUtils.isEmpty(password))
            {
                Toast.makeText(getApplicationContext(),"Per favore inserisci la password",Toast.LENGTH_SHORT).show();
                return;
            }

            firebaseAuth.signInWithEmailAndPassword(email,password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful())
                            {
                                Toast.makeText(getApplicationContext(),"L'accesso è andato a buon fine.",Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(getApplicationContext(),Eventi.class);
                                startActivity(intent);
                            }
                            else
                            {
                                Toast.makeText(getApplicationContext(),"L'accesso non è andato a buon fine,riprova.",Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }


        public void onClick(View view)
        {
            if(view == vRegistrati)
            {
                registerUser();
            }
            if(view == vAccedi)
            {
                userLogin();
            }
        }

    }
