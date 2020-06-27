package edu.upc.login.Fragments;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.unity3d.player.UnityPlayerActivity;

import java.util.ArrayList;
import java.util.List;

import edu.upc.login.API;
import edu.upc.login.Adaptadores.AdapterInventario;
import edu.upc.login.Entidades.Inventario;
import edu.upc.login.Entidades.Item;
import edu.upc.login.R;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentHome extends Fragment {

    Button play;
    private API api;
    List<Inventario> objetos = new ArrayList<>();
    List<Integer> vector = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.main_fragment,container,false);
        this.getObjetosPlayer();
        play = view.findViewById(R.id.playBtn);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creamos interceptor
                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

                //Creamos cliente
                OkHttpClient client = new OkHttpClient.Builder()
                        .addInterceptor(interceptor)
                        .build();

                //Crear retrofit
                final Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://147.83.7.203:8080/dsaApp/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(client)
                        .build();

                //Llamamos a servicios que hemos definido en la API
                api = retrofit.create(API.class);
                Call<List<Inventario>> call = api.inventario(obtenerToken());
                call.enqueue(new Callback<List<Inventario>>() {
                    @Override
                    public void onResponse(Call<List<Inventario>> call, Response<List<Inventario>> response) {
                        if(response.isSuccessful()) {
                            objetos = response.body();
                            for(int i=0; i<objetos.size();i++){
                                vector.add(objetos.get(i).getIdObjeto());
                                vector.add(objetos.get(i).getCantidad());
                            }
                            Log.e("objetos", String.valueOf(vector));
                            Intent i = new Intent(getContext(), UnityPlayerActivity.class);
                            i.putExtra("objetos", String.valueOf(vector));
                            startActivity(i);
                        }
                        else {
                            Log.e("DSA","Error :"+response.errorBody());
                        }
                    }
                    @Override
                    public void onFailure(Call<List<Inventario>> call, Throwable t) {
                        Log.e("DSA","Error: No se pudo acceder a la API",t);
                    }
                });
            }
        });

        return view;
    }

    private String obtenerToken(){
        SharedPreferences preferences = this.getActivity().getSharedPreferences("tokenUsuario", Context.MODE_PRIVATE);
        String token = preferences.getString("token", "Login required");
        return token;
    }

    private void getObjetosPlayer() {


    }
}
