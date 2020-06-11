package edu.upc.login.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.upc.login.API;
import edu.upc.login.Adaptadores.AdapterPartidas;
import edu.upc.login.Adaptadores.AdapterRanking;
import edu.upc.login.Entidades.Partida;
import edu.upc.login.Entidades.Ranking;
import edu.upc.login.R;
import edu.upc.login.iComunicaFragments;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentEstadisticas extends Fragment {

    AdapterRanking adapterRanking;
    AdapterPartidas adapterPartida;
    RecyclerView recyclerViewRanking;
    private API api;
    Activity actividad;
    iComunicaFragments interfaceComunicaFragments;

    private String obtenerToken(){
        SharedPreferences preferences = this.getActivity().getSharedPreferences("tokenUsuario", Context.MODE_PRIVATE);
        String token = preferences.getString("token", "Login required");
        return token;
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.estadisticas_fragment, container, false);
        recyclerViewRanking = view.findViewById(R.id.recyclerId);
        //cargar la lista
        cargarLista();
        Button top5button = view.findViewById(R.id.top5personal);
        Button generalButton = view.findViewById(R.id.top5general);
        top5button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Creamos interceptor
                top5button.setVisibility(view.INVISIBLE);
                generalButton.setVisibility(view.VISIBLE);

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

                String token = obtenerToken();
                Call<List<Partida>> call = api.getRankingPersonal(token);

                call.enqueue(new Callback<List<Partida>>() {
                    @Override
                    public void onResponse(Call<List<Partida>> call, Response<List<Partida>> response) {
                        if(response.isSuccessful()) {
                            List<Partida> rankingRespuesta = response.body();
                            mostrarInfo(rankingRespuesta);
                        }
                        else {
                            Log.e("DSA","Error :"+response.errorBody());
                        }
                    }
                    @Override
                    public void onFailure(Call<List<Partida>> call, Throwable t) {
                        Log.e("DSA","Error: No se pudo acceder a la API",t);
                    }
                });
                generalButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        top5button.setVisibility(view.VISIBLE);
                        generalButton.setVisibility(view.INVISIBLE);
                        cargarLista();
                    }
                });
            }
        });
        return view;
    }

    public void cargarLista() {
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
        Call<List<Ranking>> call = api.getRanking();

        call.enqueue(new Callback<List<Ranking>>() {
            @Override
            public void onResponse(Call<List<Ranking>> call, Response<List<Ranking>> response) {
                if(response.isSuccessful()) {
                    List<Ranking> rankingRespuesta = response.body();
                    //listaRanking.addAll(rankingRespuesta);
                   mostrarDatos(rankingRespuesta);
                }
                else {
                    Log.e("DSA","Error :"+response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<Ranking>> call, Throwable t) {
                Log.e("DSA","Error: No se pudo acceder a la API",t);
            }
        });
    }
    public void mostrarDatos(List<Ranking> listaRanking) {

        recyclerViewRanking.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterRanking = new AdapterRanking(getContext(), listaRanking);
        recyclerViewRanking.setAdapter(adapterRanking);
    }
    public void mostrarInfo(List<Partida> listaPartida) {

        recyclerViewRanking.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterPartida = new AdapterPartidas(getContext(), listaPartida);
        recyclerViewRanking.setAdapter(adapterPartida);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof Activity){
            this.actividad=(Activity) context;
            interfaceComunicaFragments=(iComunicaFragments) this.actividad;
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
    }
    }








