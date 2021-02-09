package com.example.proiect_dam;

public interface IJsonResponse {
    void onSucces(String response);
    void onFail(int errorCode, Throwable error);
}
