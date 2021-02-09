package com.example.proiect_dam;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class addNewItem extends Fragment {
    private static final int PICK_IMG_REQUEST = 1;
    private Button submit;
    private Button upload;
    private EditText pret;
    private EditText descriere;
    private EditText titlu;
    private Uri mImageUri;
    private ImageView img;
    private ProgressBar progressbar;

    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_add_new_item, container, false);
        init();

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alegeopoza();
                Toast.makeText(getActivity(),
                        "bum bum bum",
                        Toast.LENGTH_LONG).show();
            }
        });


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validare()) {
                    addItem();
                }
            }
        });

        return rootView;
    }


    private void init() {
        upload = (Button) rootView.findViewById(R.id.alegepoza);
        submit = (Button) rootView.findViewById(R.id.Additem);
        titlu = (EditText) rootView.findViewById(R.id.Titluanunt);
        descriere = (EditText) rootView.findViewById(R.id.descriere);
        pret = (EditText) rootView.findViewById(R.id.pret);
        img = (ImageView) rootView.findViewById(R.id.img);

    }

    private void alegeopoza() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMG_REQUEST);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMG_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            mImageUri = data.getData();
            Picasso.get().load(mImageUri).into(img);


        }
    }

    public boolean validare() {
        if (titlu.getText() == null || titlu.getText().toString().trim().length() < 3) {
            Toast.makeText(getActivity(),
                    R.string.titlu_invalid,
                    Toast.LENGTH_LONG)
                    .show();
            return false;
        }


        if (descriere.getText() == null || descriere.getText().toString().trim().length() < 20) {
            Toast.makeText(getActivity(),
                    R.string.descriere_invalid,
                    Toast.LENGTH_LONG)
                    .show();
            return false;
        }


        try {
            Integer.parseInt(pret.getText().toString());
            if (pret.getText() == null) {
                Toast.makeText(getActivity(),
                        R.string.pret_invalid,
                        Toast.LENGTH_LONG)
                        .show();
                return false;
            }

            if (Integer.parseInt(pret.getText().toString()) <= 0) {
                Toast.makeText(getActivity(),
                        R.string.pret_invalid,
                        Toast.LENGTH_LONG)
                        .show();
                return false;
            }
        } catch (NumberFormatException e) {
            Toast.makeText(getActivity(),
                    R.string.pret_invalid,
                    Toast.LENGTH_LONG)
                    .show();
            return false;
        }

        if (mImageUri == null) {
            Toast.makeText(getActivity(),
                    R.string.imagine_invalida,
                    Toast.LENGTH_LONG)
                    .show();
            return false;
        }

        return true;
    }

    public void addItem() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        final DatabaseReference main = database.getReference("produs");

//        String par=UtilizatorSingleton.getParola();
//        String ema=UtilizatorSingleton.getEmail();

        //merge si asta dar am zis sa fiu mai elegant
        String pa = getActivity().getIntent().getStringExtra("PAROLA");
        final String em = getActivity().getIntent().getStringExtra("EMAIL");
        DatabaseReference put = main.child("produs" + System.currentTimeMillis());

        StorageReference sto = FirebaseStorage.getInstance().getReference("produs");
        final String numeiMG="produs" + System.currentTimeMillis()+"."+getFileExtension(mImageUri);
        final StorageReference file = sto.child(numeiMG);
        file.putFile(mImageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                Log.v("avem o problema", "problema");
            }
        }).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {
                double progress = (100.0 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                //progressbar.setProgress((int) progress);
                //candva am avut pe aici un progressbar , dar nu stiam ca exista onsucces asa ca nu puteam sa il mai opresc din rotit
                //in loc sa caut o varianta sa il opresc din rotit , l am scos
            }
        })
        .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                file.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                    @Override
                    public void onSuccess(Uri uri) {
                        Log.d("deaiciosa", "onSuccess: uri= "+ uri.toString());

                        Date c1 = Calendar.getInstance().getTime();
                        SimpleDateFormat simpleDate =  new SimpleDateFormat("dd/MM/yyyy");

                        String c = simpleDate.format(c1);
                        Item item = new Item(titlu.getText().toString(),
                                descriere.getText().toString(),
                                Integer.parseInt(pret.getText().toString()),
                                "   ",c,em,
                                uri.toString());
                        String id = main.push().getKey();
                        main.child(id).setValue(item);

                        Toast.makeText(getActivity(), R.string.ok, Toast.LENGTH_LONG).show();
                        FragmentTransaction fragmentTransaction = getActivity()
                                .getSupportFragmentManager().beginTransaction();
                        fragmentTransaction.replace(R.id.nav_host_fragment, new Show_items());
                        fragmentTransaction.commit();


                    }
                });
            }
        });


    }

    private String getFileExtension(Uri uri) {
        ContentResolver contentResolver = getActivity().getApplicationContext().getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(contentResolver.getType(uri));

    }

    public void ChangeFragment(Fragment fragment){
        FragmentTransaction fragmentTransaction=getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.nav_host_fragment,fragment);
        fragmentTransaction.commit();
    }
}