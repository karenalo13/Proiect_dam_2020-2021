package com.example.proiect_dam;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class Aldoileaadapter extends BaseAdapter {

    private ArrayList<Item> list;
    private Context context;
    private LayoutInflater layoutInflater;

    public Aldoileaadapter(ArrayList<Item> list, Context context) {
        this.list = list;
        this.context = context;
        layoutInflater=LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        View view=layoutInflater.inflate(R.layout.aldoilea,parent,false);
        final ViewGroup p=parent;
        TextView textView=view.findViewById(R.id.cevaneimportant);
        TextView datafinal=view.findViewById(R.id.datafinal);
        final Item produs=list.get(position);
        textView.setText(produs.toString());
        //ImageView img=view.findViewById(R.id.imagineObiect);
        //Log.v("imagine",produs.getmImageUrl());
        //img.setImageBitmap(getBitmapFromURL(produs.getmImageUrl()));
        //new ImageLoadTask(produs.getmImageUrl(), img).execute();

//        Picasso.get().load(produs.getmImageUrl())
//         .fit().centerInside().into(img);
        String da=context.getResources().getString(R.string.Start)+produs.getData();
        datafinal.setText(da);
        Button btn=view.findViewById(R.id.ButoonImagine);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context,ImgVizualizare.class);
                intent.putExtra("url",produs.getNume_poza() );
                p.getContext().startActivity(intent);
            }
        });
        return view
                ;
    }
}
