package edu.upc.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.unity3d.player.UnityPlayerActivity;

import java.util.ArrayList;
import java.util.List;

import edu.upc.login.Entidades.Inventario;
import edu.upc.login.Entidades.Item;
import edu.upc.login.Entidades.Mapa;
import edu.upc.login.Entidades.Nivel;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Singleton {
    private static Singleton instance;
    private API api;
    List<Inventario> objetos = new ArrayList<>();
    String mapa;
    String enemigos;
    String token;


    private Singleton() {

        Gson gson = new GsonBuilder().create();

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://147.83.7.203:8080/dsaApp/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();

        this.api = retrofit.create(API.class);

    }

    public static Singleton getInstance() {
        if (instance == null) instance = new Singleton();
        return instance;
    }

    public API getApi(){
        return this.api;
    }

    public String getToken(){
        return token;
    }

    public void setToken(String token){
        this.token = token;
    }

    public List<Inventario> getObjetos() {
        return objetos;
    }
    public void setObjetos(List<Inventario> o) {
        this.objetos = o;
    }

    public void requestObjetos() {
        Call<List<Inventario>> call = api.inventario(token);
        call.enqueue(new Callback<List<Inventario>>() {
            @Override
            public void onResponse(Call<List<Inventario>> call, Response<List<Inventario>> response) {
                if (response.isSuccessful()) {
                    Singleton.getInstance().setObjetos(response.body());
                } else {
                    Log.e("DSA", "Error :" + response.errorBody());
                }
            }
            @Override
            public void onFailure(Call<List<Inventario>> call, Throwable t) {
                Log.e("DSA", "Error: No se pudo acceder a la API", t);
            }
        });
    }

    public void updateObjetos(int idObjeto, String token) {
        Inventario i = new Inventario();
        i.setIdObjeto(idObjeto);
        i.setIdJugador(token);
        Call<Void> call = api.useObjeto(i);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Log.e("DSA", "Objeto gastado");
                } else {
                    Log.e("DSA", "Error :" + response.errorBody());
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.e("DSA", "Error: No se pudo acceder a la API", t);
            }
        });
    }

    public String getMapa() {
        Log.e("Mapa1:",""+mapa);
        return mapa;
    }
    public void setMapa(Mapa m) {
        this.mapa = m.getMapa();
    }

    public void requestMapa(int idMapa) {
        Call<Mapa> call = api.getMapa(idMapa);
        call.enqueue(new Callback<Mapa>() {
            @Override
            public void onResponse(Call<Mapa> call, Response<Mapa> response) {
                if (response.isSuccessful()) {
                    Singleton.getInstance().setMapa(response.body());
                } else {
                    Log.e("DSA", "Error :" + response.errorBody());
                }
            }
            @Override
            public void onFailure(Call<Mapa> call, Throwable t) {
                Log.e("DSA", "Error: No se pudo acceder a la API", t);
            }
        });
    }

    public String getEnemigos(){
        return enemigos;
    }

    public void setEnemigos(String enemigos){
        this.enemigos=enemigos;
    }

    public void requestEnemigos(int idNivel){
        Call<Nivel> call = api.getEnemigos(idNivel);
        call.enqueue(new Callback<Nivel>() {
            @Override
            public void onResponse(Call<Nivel> call, Response<Nivel> response) {
                if (response.isSuccessful()) {
                    Nivel n = response.body();
                    Singleton.getInstance().setEnemigos(n.getEnemigos());
                } else {
                    Log.e("DSA", "Error :" + response.errorBody());
                }
            }
            @Override
            public void onFailure(Call<Nivel> call, Throwable t) {
                Log.e("DSA", "Error: No se pudo acceder a la API", t);
            }
        });
    }
}