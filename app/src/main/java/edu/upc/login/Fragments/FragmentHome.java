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
import edu.upc.login.Singleton;
import edu.upc.login.apiUnity;
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

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.main_fragment,container,false);
        Singleton.getInstance().requestObjetos(obtenerToken());
        play = view.findViewById(R.id.playBtn);
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), UnityPlayerActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private String obtenerToken(){
        SharedPreferences preferences = this.getActivity().getSharedPreferences("tokenUsuario", Context.MODE_PRIVATE);
        String token = preferences.getString("token", "Login required");
        return token;
    }
}
