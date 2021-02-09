package com.example.proiect_dam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

public class regulisisfaturi extends AppCompatActivity {

    private ReguliDB database;
    private TextView tx1;
    private TextView tx2;
    private TextView tx3;
    private TextView tx4;
    private TextView tx5;
    private TextView tx6;
    private TextView tx7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regulisisfaturi);
        database = Room.databaseBuilder(this,
                ReguliDB.class, "database-name").allowMainThreadQueries().build();

        insertReguli();
        insertSfaturi();
        List<Regulibunafunct> reg=database.regulibunafunct().getAll();
        List<Sfaturi> sfat=database.sfaturi().getAll();
        init();
        publicareTOT(reg,sfat);


    }

    private void insertReguli() {

        String nume1 = "Regula nr 1";
        String continut1 = "Nu poti sa oferi un pret mai mic ca cel afisat pe ecran.";
        Regulibunafunct regula1 = new Regulibunafunct(nume1,continut1 );
        database.regulibunafunct().insertRegula(regula1);
        String nume2 = "Regula nr 2";
        String continut2 = "Daca ai vandut produsul , da un mail la andrei@stud.ro pentru a dezactiva anuntul.";
        Regulibunafunct regula2 = new Regulibunafunct(nume2,continut2 );
        database.regulibunafunct().insertRegula(regula2);
        String nume3 = "Regula nr 3";
        String continut3 = "O Licitație oarbă vă oferă un avantaj special: nimeni în afară de dvs. nu poate vedea oferta pe care ați plasat-o. De aceea, acești ofertanți nu vă pot depăși oferta în cunoștință de cauză și nu pot încerca să mărească prețul. Grozav! Însă există și un dezavantaj: nici dvs. nu puteți vedea ofertele plasate de ei. Așadar, asigurați-vă că plasați o ofertă cât se poate de bună, aveți dreptul doar la o încercare. Dar nu licitați niciodată mai mult decât sunteți pregătit să plătiți. Şi nu uitați să includeți și costurile pentru transport și import în respectiva sumă. Dacă nu se specifică altfel, veți ști, în două zile lucrătoare, dacă vânzătorul a acceptat oferta dvs. sau nu.";
        Regulibunafunct regula3 = new Regulibunafunct(nume3,continut3 );
        database.regulibunafunct().insertRegula(regula3);

        String nume4 = "Regula nr 4";
        String continut4 = "Contacteaza vanzatorul la adresa din campul contact. Orice alta modalitate este interzisa.";
        Regulibunafunct regula4 = new Regulibunafunct(nume4,continut4 );
        database.regulibunafunct().insertRegula(regula4);


        String nume5 = "Regula nr 5";
        String continut5 = "Nu ne asumam responsabilitati , fa ce crezi ca e mai bine pentru tine.";
        Regulibunafunct regula5 = new Regulibunafunct(nume5,continut5 );
        database.regulibunafunct().insertRegula(regula5);

    }
    private void insertSfaturi() {
        String nume1 = "Sfat nr 1";
        String continut1 = "Nu supraevalua un produs , oferte apar zilnic.";
        Sfaturi sfat1 = new Sfaturi(nume1,continut1 );
        database.sfaturi().insertSfat(sfat1);
        String nume2 = "Sfat nr 2";
        String continut2 = "Nu licita mai mult decat iti permiti. Joaca responsabil.";
        Sfaturi sfat2 = new Sfaturi(nume2,continut2 );
        database.sfaturi().insertSfat(sfat2);
    }
    public void publicareTOT(List<Regulibunafunct> l1,List<Sfaturi> l2){
        String cont1=l1.get(0).nume+"\n"+l1.get(0).continut+".";
        tx1.setText(cont1);
        String cont2=l1.get(1).nume+"\n"+l1.get(1).continut+".";
        tx2.setText(cont2);
        String cont3=l1.get(2).nume+"\n"+l1.get(2).continut;
        tx3.setText(cont3);
        String cont4=l1.get(3).nume+"\n"+l1.get(3).continut+".";
        tx4.setText(cont4);
        String cont5=l1.get(4).nume+"\n"+l1.get(4).continut+".";
        tx5.setText(cont5);
        String cont6=l2.get(0).nume+"\n"+l2.get(0).continut+".";
        tx6.setText(cont6);
        String cont7=l2.get(1).nume+"\n"+l2.get(1).continut+".";
        tx7.setText(cont7);

    }
    public void init(){
        tx1=findViewById(R.id.regula1);
        tx2=findViewById(R.id.regula2);
        tx3=findViewById(R.id.regula3);
        tx4=findViewById(R.id.regula4);
        tx5=findViewById(R.id.regula5);
        tx6=findViewById(R.id.sfat1);
        tx7=findViewById(R.id.sfat2);

    }


}