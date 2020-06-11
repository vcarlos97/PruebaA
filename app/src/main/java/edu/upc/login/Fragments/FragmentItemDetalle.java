package edu.upc.login.Fragments;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.lang.reflect.Method;

import edu.upc.login.API;
import edu.upc.login.Entidades.Inventario;
import edu.upc.login.Entidades.Item;
import edu.upc.login.HomeActivity;
import edu.upc.login.R;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class FragmentItemDetalle extends Fragment {
    int cant=1;
    API api;
    TextView titulo, descripcion , precio, cantidad;
    ImageView imagenDetalle;
    Button mas, menos, comprar;
    private String obtenerToken() {
        SharedPreferences preferences = this.getActivity().getSharedPreferences("tokenUsuario", Context.MODE_PRIVATE);
        String token = preferences.getString("token", "Login required");
        return token;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.detalle_item_fragment,container,false);
        titulo=view.findViewById(R.id.titulo);
        descripcion=view.findViewById(R.id.textdescricion);
        precio=view.findViewById(R.id.textprecio);
        imagenDetalle=view.findViewById(R.id.imagen_detalle);
        cantidad= view.findViewById(R.id.cantidad);
        mas = view.findViewById(R.id.mas);
        menos = view.findViewById(R.id.menos);
        comprar = view.findViewById(R.id.comprar);
        //Crear objeto bundle para recibir el objeto enviado por argumentos
        Bundle objetoItem = getArguments();
        Item item =null;
        //validacion para verificar si existen argumentos enviados para mostrar
        if(objetoItem!=null){
            item = (Item)objetoItem.getSerializable("objeto");
            //Establecer los datos en las vistas
            titulo.setText(item.getNombre());
            descripcion.setText(item.getDescripcion());
            precio.setText("" + item.getPrecio());
            imagenDetalle.setImageResource(item.getImagenid());
        }
        int idObjeto = item.getIdObjeto();

        menos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (FragmentItemDetalle.this.cant !=1){
                    FragmentItemDetalle.this.cant--;
                    cantidad.setText(String.valueOf(cant));
                }
                else Toast.makeText(getContext(),"La compra mínima es 1", Toast.LENGTH_SHORT).show();
            }
        });
        mas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentItemDetalle.this.cant++;
                cantidad.setText(String.valueOf(cant));
            }
        });
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
        comprar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int c = cantidad.getText();
                String idJugador = obtenerToken();
                Inventario i = new Inventario(idObjeto, FragmentItemDetalle.this.cant, idJugador);
                Call<Void> call = api.comprar(i);
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.code() == 400) Toast.makeText(getContext(), "No tienes suficientes monedas", Toast.LENGTH_SHORT).show();
                        else if (response.code() == 500) Toast.makeText(getContext(), "Error", Toast.LENGTH_SHORT).show();
                        else {
                            String m = actualizarMonedas(Integer.parseInt((String) precio.getText()), Integer.parseInt((String) cantidad.getText()));
                            TextView txtView = getActivity().findViewById(R.id.idmonedas);
                            txtView.setText(m);
                            Toast.makeText(getContext(), "Tu compra se ha realizado con éxito", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(getContext(), "Ohhh! Parece que ha habido algun problema...", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
        return view;
    }

    private String actualizarMonedas(int precio, int cantidad){
        SharedPreferences preferences = getContext().getSharedPreferences("tokenUsuario", Context.MODE_PRIVATE);
        int monedas = preferences.getInt("monedas", 0);
        int newCoins = monedas - (precio*cantidad);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt("monedas", newCoins);
        editor.commit();
        return String.valueOf(newCoins);
    }
}
