package edu.upc.login.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import edu.upc.login.API;
import edu.upc.login.Adaptadores.AdapterForo;
import edu.upc.login.Adaptadores.AdapterInventario;
import edu.upc.login.Adaptadores.AdapterItem;
import edu.upc.login.Adaptadores.AdapterRanking;
import edu.upc.login.Entidades.Foro;
import edu.upc.login.Entidades.Inventario;
import edu.upc.login.Entidades.Item;
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

public class FragmentInventario extends Fragment {

    AdapterInventario adapterInventario;
    RecyclerView recyclerViewInventario;
    private API api;
    List<Inventario> inventario;
    //referencias para comunicar fragment
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
        View view  = inflater.inflate(R.layout.inventario_fragment,container,false);
        recyclerViewInventario = view.findViewById(R.id.recyclerViewInventario);
        //cargar la lista
        cargarLista();
        return view;
    }

    private void cargarLista() {
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
                    inventario = response.body();
                    recyclerViewInventario.setLayoutManager(new LinearLayoutManager(getContext()));
                    adapterInventario = new AdapterInventario(getContext(), inventario);
                    recyclerViewInventario.setAdapter(adapterInventario);
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

