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
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.List;

import edu.upc.login.API;
import edu.upc.login.Adaptadores.AdapterForo;
import edu.upc.login.Entidades.Foro;
import edu.upc.login.HomeActivity;
import edu.upc.login.MainActivity;
import edu.upc.login.R;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentSignOut extends Fragment {

    Button salir;
    API api;

    private String obtenerToken(){
        SharedPreferences preferences = getActivity().getSharedPreferences("tokenUsuario", Context.MODE_PRIVATE);
        String token = preferences.getString("token", "Login required");
        return token;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.sign_out_fragment,container,false);
        salir = view.findViewById(R.id.salirBtn);
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
                interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

                //Creamos cliente
                OkHttpClient client = new OkHttpClient.Builder()
                        .addInterceptor(interceptor)
                        .build();

                //Crear retrofit
                Retrofit retrofit = new Retrofit.Builder()
                        .baseUrl("http://147.83.7.203:8080/dsaApp/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(client)
                        .build();

                //Llamamos a servicios que hemos definido en la API
                api = retrofit.create(API.class);

                String token = obtenerToken();
                Call<Void> signOut = api.signOut(token);
                signOut.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        Toast.makeText(getContext(), "Vuelve pronto :(", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getContext(), MainActivity.class);
                        startActivity(i);
                        getActivity().finish();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Log.d("minimo2", "Error", t);
                    }
                });
            }
        });

        return view;
    }
}
