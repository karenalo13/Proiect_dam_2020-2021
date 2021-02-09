package com.example.proiect_dam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class already_member extends AppCompatActivity {

    private EditText email;
    private EditText parola;
    private Button login;
    private boolean exista=false;
    private ProgressBar simpleProgressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_already_member);
        ititializare();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.v("email", email.getText().toString());
                if (validare()) {

                    simpleProgressBar.setVisibility(View.VISIBLE);
                    citireFirebase(email.getText().toString(), parola.getText().toString());


                }
                else {
                    Toast.makeText(getApplicationContext(),
                            "Introduceti un cont valid",
                            Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    private void ititializare() {
        email = findViewById(R.id.et_email_log);
        parola = findViewById(R.id.et_parola_log);
        simpleProgressBar=findViewById(R.id.simpleProgressBar);
        login = findViewById(R.id.btn_intraincont);

    }

    private boolean validare() {
        if (parola.getText() == null || parola.getText().toString().trim().length() < 3) {
            Toast.makeText(getApplicationContext(),
                    R.string.parola_invalida,
                    Toast.LENGTH_LONG)
                    .show();
            return false;
        }
        if (!verificare_email(email.getText().toString())) {
            Toast.makeText(getApplicationContext(),
                    R.string.email_gresit,
                    Toast.LENGTH_LONG)
                    .show();
            return false;
        }
        return true;
    }

    public static boolean verificare_email(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    public void citireFirebase(final String ema, final String par) {
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference membruref = rootRef.child("membru");
        membruref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    String nume = ds.child("nume").getValue(String.class);
                    String prenume = ds.child("prenume").getValue(String.class);
                    String email = ds.child("email").getValue(String.class);
                    String parola = ds.child("parola").getValue(String.class);
                    if(ema.equals(email)  && parola.equals(par) ){ Log.v("exista1111","true");
                    exista=true;
                        makeitTrue();
                    }
                    Log.v("TAG", nume+" "+prenume+" "+email+" "+parola);
                }
                if(exista==true){
                    Toast.makeText(getApplicationContext(),
                            "Felicitari , ai intrat",
                            Toast.LENGTH_LONG).show();
                    UtilizatorSingleton.getInstance(ema,par);
                    parola.setText("");
                    email.setText("");
                    SharedPreferences sharedPreferences= getSharedPreferences("sharedPref", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor=sharedPreferences.edit();
                    editor.putString("key_String_last_user",ema);
                    editor.putString("key_String_ultima_parola",par);
                    editor.putBoolean("user_parola",true);
                    Date date= Calendar.getInstance().getTime();
                    SimpleDateFormat dataformat=new SimpleDateFormat("dd/MM/yyyy");
                    editor.putString("last_log_in",dataformat.format(date));
                    editor.commit();
                    Intent intent= new Intent(getApplicationContext(),MainActivity2.class);
                    intent.putExtra("PAROLA", par);
                    intent.putExtra("EMAIL", ema);
                    startActivity(intent);
                    UtilizatorSingleton.clear();
                }
                else{
                    Toast.makeText(getApplicationContext(),
                            "Introduceti un cont valid",
                            Toast.LENGTH_LONG).show();
                    Log.v("existaaadsa","fals");
                }
                exista=false;
                simpleProgressBar.setVisibility(View.INVISIBLE);

                String mes = getResources().getString(R.string.numarMembrii
                );
                Log.v("string",mes);
                mes+=String.valueOf(dataSnapshot.getChildrenCount());
                Log.v("string",mes);
                Toast.makeText(getApplicationContext(),
                        mes,
                        Toast.LENGTH_LONG).show();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

    }

    public void makeitTrue(){
        exista=true;
    }
}