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
    StringBuffer o;


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

    public String getObjetos() {
        return o.toString();
    }
    public void setObjetos(StringBuffer o) {
        this.o = o;
    }

    public void requestObjetos(String token) {
        Call<List<Inventario>> call = api.inventario(token);
        call.enqueue(new Callback<List<Inventario>>() {
            @Override
            public void onResponse(Call<List<Inventario>> call, Response<List<Inventario>> response) {
                if (response.isSuccessful()) {
                    objetos = response.body();
                    o = new StringBuffer();
                    for (int i = 0; i < objetos.size(); i++) {
                        o.append(objetos.get(i).getIdObjeto() + "/");
                        if (i != objetos.size() - 1) o.append(objetos.get(i).getCantidad() + "/");
                        else o.append(objetos.get(i).getCantidad());
                    }
                    Singleton.getInstance().setObjetos(o);
                    Log.e("objetos", o.toString());
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
    /*public void requestStats() {

        Call<Stats> call = authApi.getStats(username);

        call.enqueue(new Callback<Stats>() {
            @Override
            public void onResponse(Call<Stats> call, Response<Stats> response) {

                if (response.isSuccessful())
                {
                    Singleton.getInstance().setStats(response.body());
                }
            }

            @Override
            public void onFailure(Call<Stats> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void sendStats() {
        Call<Void> call = authApi.updateStats(username, stats);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public Inventario getInventario() {
        return this.inventario;
    }

    public void requestInventario() {
        Call<Inventario> call = authApi.getInventory(username);

        call.enqueue(new Callback<Inventario>() {
            @Override
            public void onResponse(Call<Inventario> call, Response<Inventario> response) {

                Singleton.getInstance().setInventario(response.body());
            }

            @Override
            public void onFailure(Call<Inventario> call, Throwable t) {

            }
        });
    }

    public void updateInventario() {
        Call<Void> call = authApi.setInventory(this.inventario);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {

            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });
    }

    public void setMaps(List<Map> maps) {
        this.map = maps;
    }

    public Map getMap(int i) {
        return this.map.get(i - 1);
    }

    public void requestMaps() {

        Call<List<Map>> call = authApi.getMaps();

        call.enqueue(new Callback<List<Map>>() {
            @Override
            public void onResponse(Call<List<Map>> call, Response<List<Map>> response) {
                if (response.isSuccessful())
                    Singleton.getInstance().setMaps(response.body());
            }

            @Override
            public void onFailure(Call<List<Map>> call, Throwable t) {

            }
        });

    }*/

}