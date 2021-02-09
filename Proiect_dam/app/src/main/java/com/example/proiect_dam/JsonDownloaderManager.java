package com.example.proiect_dam;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class JsonDownloaderManager {

    private static JsonDownloaderManager instance;

    private JsonDownloaderManager(){

    }

    public static JsonDownloaderManager getInstance()
    {
        if(instance==null){
            instance=new JsonDownloaderManager();
        }
        return instance;
    }

    public void getData(final IJsonResponse listener){
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    URL url=new URL("https://api.mocki.io/v1/00248769");
                    HttpURLConnection connection= (HttpURLConnection) url.openConnection();
                    InputStream stream=connection.getInputStream();
                    InputStreamReader reader= new InputStreamReader(stream);
                    BufferedReader bufferedReader=new BufferedReader(reader);
                    StringBuilder stringBuilder=new StringBuilder();

                    String line="";
                    while((line=bufferedReader.readLine())!=null){
                        stringBuilder.append(line);
                    }
                    bufferedReader.close();
                    reader.close();
                    stream.close();
                    listener.onSucces(stringBuilder.toString());
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                    listener.onFail(HttpURLConnection.HTTP_BAD_REQUEST,e);
                } catch (IOException e) {
                    e.printStackTrace();
                    listener.onFail(HttpURLConnection.HTTP_INTERNAL_ERROR,e);
                }
            }
        }).start();


    }
}
