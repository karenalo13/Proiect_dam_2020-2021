package com.example.proiect_dam;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Add_new_Member extends AppCompatActivity {
    public static final String CHEIE = "cheie";
    private EditText nume;
    private EditText prenume;
    private EditText email;
    private EditText parola;
    private RadioGroup rgpfpj;
    private Spinner spncat1;
    private Button btnSubmit;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new__member);
        initializare();
        popSp();
        intent = getIntent();
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validare_nume_prenume_parola())
                {  Membru membru = constructie();
                intent.putExtra(CHEIE, membru);
                setResult(RESULT_OK, intent);
                finish();}

            }
        });

    }


    private void initializare() {

        nume= findViewById(R.id.tv_name);
        parola= findViewById(R.id.tv_parola);
        prenume = findViewById(R.id.tv_prenume);
        email = findViewById(R.id.tv_email);
        rgpfpj = findViewById(R.id.rg_pf_pj);
        spncat1 = findViewById(R.id.spn_am_sau_nu);
        btnSubmit = findViewById(R.id.btn_submit);
    }
    private void popSp() {

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.cat1,
                android.R.layout.simple_spinner_dropdown_item);
        spncat1.setAdapter(adapter);
    }

    private boolean validare_nume_prenume_parola() {

        if (nume.getText() == null || nume.getText().toString().trim().length() < 3) {
             Toast.makeText(getApplicationContext(),
                    R.string.nume_invalid,
                    Toast.LENGTH_LONG)
                    .show();

            return false;
        }
        if (prenume.getText() == null || prenume.getText().toString().trim().length() < 3) {
             Toast.makeText(getApplicationContext(),
                    R.string.prenume_invalid,
                    Toast.LENGTH_LONG)
                    .show();
            return false;
        }
        if (parola.getText() == null || parola.getText().toString().trim().length() < 3) {
            Toast.makeText(getApplicationContext(),
                    R.string.parola_invalida,
                    Toast.LENGTH_LONG)
                    .show();
            return false;
        }
        if(!verificare_email(email.getText().toString()))
        {
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

    private Membru constructie() {

        String snume = nume.getText().toString();
        String sprenume = prenume.getText().toString();
        String sparola = parola.getText().toString();
        String semail = email.getText().toString();


        return new Membru(snume, sprenume, sparola, semail);
    }
}