package com.example.proiect_dam;

public class UtilizatorSingleton {

    private static UtilizatorSingleton instance;
    private static String email;
    private static String parola;

    private UtilizatorSingleton(String em , String par){
        email=em;
        parola=par;

    }

    public static UtilizatorSingleton getInstance(String em , String par)
    {
        if(instance==null){
            instance=new UtilizatorSingleton(em,par);
        }
        return instance;
    }

    public  static void clear()
    {
        instance=null;

    }

    public static String getEmail() {
        return email;
    }

    private static void setEmail(String email) {
        UtilizatorSingleton.email = email;
    }

    public static String getParola() {
        return parola;
    }

    private static void setParola(String parola) {
        UtilizatorSingleton.parola = parola;
    }
}
