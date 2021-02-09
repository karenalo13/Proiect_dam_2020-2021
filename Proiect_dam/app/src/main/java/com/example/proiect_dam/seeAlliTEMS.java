
package com.example.proiect_dam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class seeAlliTEMS extends AppCompatActivity {
    private ListView lv ;
    private DatabaseReference dbr;
    private List<Item> lista;
    private ItemAdapter itemAdapter;
    private Aldoileaadapter al2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_see_alli_t_e_m_s);
        lv= findViewById(R.id.lv_items);
        lista=new ArrayList<Item>();
        dbr= FirebaseDatabase.getInstance().getReference("produs");
        runOnUiThread(new Runnable() {
            public void run() {


                dbr.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot postSnapshot : snapshot.getChildren()) {
                            Item upload = postSnapshot.getValue(Item.class);
                            lista.add(upload);
//                    try {
//                        Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(upload.getData());
//                        Date currentTime = Calendar.getInstance().getTime();
//                        long diffInMillies = Math.abs(currentTime.getTime() - date1.getTime());
//                        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
//                        //if(diff<3)
//                            lista.add(upload);
//                    } catch (ParseException e) {
//                        e.printStackTrace();
//                    }

                        }
                        for (Item ceva : lista) {
                            Log.v("produs",ceva.toString());
                        }
                        al2=new Aldoileaadapter((ArrayList<Item>) lista, getApplicationContext());
                        lv.setAdapter(al2);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(seeAlliTEMS.this,error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });




            }});
    }
}