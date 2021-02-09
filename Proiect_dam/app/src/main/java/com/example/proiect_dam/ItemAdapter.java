package com.example.proiect_dam;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class ItemAdapter extends BaseAdapter {
private  ArrayList<Item> list;
private Context context;
private LayoutInflater layoutInflater;

    public ItemAdapter (Context context , ArrayList<Item> list ){
this.list=list;
this.context=context;
layoutInflater=LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Item getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }




    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View item=layoutInflater.inflate(R.layout.itemlista,parent,false);
        TextView text=item.findViewById(R.id.descriere);
        TextView multumim =item.findViewById(R.id.plus);

        Item produs=list.get(position);
        Log.v("adapter",produs.toString());
        String afisat=produs.toString();
        text.setText(afisat);

        String da=context.getResources().getString(R.string.Start)+produs.getData();
        multumim.setText(da);



        return item;
    }
}
