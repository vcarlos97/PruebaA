package edu.upc.login.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.upc.login.API;

import edu.upc.login.R;
import edu.upc.login.Ranking;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentEstadisticas extends Fragment {
    /*private API api;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private List<Ranking> lista = new ArrayList<>();

    private void getRanking() {

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

        Call<Ranking> rankingRespuestaCall = api.getRanking();
        rankingRespuestaCall.enqueue(new Callback<Ranking>() {
            @Override
            public void onResponse(Call<Ranking> call, Response<Ranking> response) {
                if(response.isSuccessful()){
                    lista = (List<Ranking>) response.body();
                    mAdapter = new MyAdapter2(lista, FragmentEstadisticas.this);
                    recyclerView.setAdapter(mAdapter);

                    for(int i = 0; i< lista.size(); i++) {
                    Ranking r = lista.get(i);

                    }
                }
                 else{

                }
            }

            @Override
            public void onFailure(Call<Ranking> call, Throwable t) {
                Toast toast = Toast.makeText(getApplicationContext(), "ERROR", Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }*/

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.estadisticas_fragment,container,false);
        /*recyclerView= recyclerView.findViewById(R.id.recyclerId);
        recyclerView.setLayoutManager(layoutManager);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);*/


        return view;





    }
}
