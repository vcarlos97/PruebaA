package edu.upc.login.Fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import edu.upc.login.API;
import edu.upc.login.R;
import edu.upc.login.User;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class FragmentPerfil extends Fragment {

    private API api;
    private static String TAG ="Usuario";
    TextView username;
    TextView password;
    TextView mail;
    TextView informacion;
    TextView informacion2;
    EditText newusername;
    EditText newpassword;
    EditText newmail;
    TextView verpassword;
    EditText vernewpassword;
    Button modificar;
    String id;
    String nombre;
    String contraseña;
    String email;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.perfil_fragment,container,false);
        username = view.findViewById(R.id.username);
        password = view.findViewById(R.id.password);
        mail = view.findViewById(R.id.mail);
        verpassword = view.findViewById(R.id.verpassword);
        vernewpassword = view.findViewById(R.id.vernewpassword);
        informacion = view.findViewById(R.id.informacion);
        informacion2 = view.findViewById(R.id.textView5);
        modificar = view.findViewById(R.id.modificar);
        newusername = view.findViewById(R.id.newusername);
        newpassword = view.findViewById(R.id.newpassword);
        newmail = view.findViewById(R.id.newmail);
        cargarUsuario();

        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (modificar.getText().equals("Modificar")){
                    modificar.setText("Enviar");
                    username.setText("Username:");
                    mail.setText("Mail:");
                    password.setVisibility(View.VISIBLE);
                    newusername.setVisibility(View.VISIBLE);
                    newusername.setText(nombre);
                    newmail.setVisibility(View.VISIBLE);
                    newmail.setText(email);
                    newpassword.setVisibility(View.VISIBLE);
                    newpassword.setText("");
                    verpassword.setVisibility(View.VISIBLE);
                    vernewpassword.setVisibility(View.VISIBLE);
                    informacion.setVisibility(View.VISIBLE);
                    informacion2.setVisibility(View.VISIBLE);
                    vernewpassword.setText("");
                }

                else{
                    if (newpassword.getText().toString().equals(vernewpassword.getText().toString())) {
                        modificarUsuario();
                        modificar.setText("Modificar");
                        password.setVisibility(View.INVISIBLE);
                        newusername.setVisibility(View.INVISIBLE);
                        newpassword.setVisibility(View.INVISIBLE);
                        newmail.setVisibility(View.INVISIBLE);
                        verpassword.setVisibility(View.INVISIBLE);
                        vernewpassword.setVisibility(View.INVISIBLE);
                        informacion.setVisibility(View.INVISIBLE);
                        informacion2.setVisibility(View.INVISIBLE);
                        cargarUsuario();
                    }

                    else
                        Toast.makeText(getContext(),"Las contraseñas no coinciden",Toast.LENGTH_SHORT).show();
                }
            }
        });
        return view;
    }

    //Funcion que lee SharedPreferences para obtener el token
    private String obtenerToken(){
        SharedPreferences preferences = this.getActivity().getSharedPreferences("tokenUsuario", Context.MODE_PRIVATE);
        String token = preferences.getString("token", "Login required");
        return token;
    }

    public void cargarUsuario() {

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
        Call<User> call = api.getUser(obtenerToken());
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {

                if (response.isSuccessful()){
                    User user = response.body();
                    username.setText("Username: " + user.nombre);
                    mail.setText("Email: " + user.mail);
                    nombre = user.nombre;
                    id = user.idUser;
                    email = user.mail;
                }

                else{
                    Log.e(TAG,"ERROR:" + response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
            }
        });
    }

    public void modificarUsuario(){

        id = obtenerToken();

        if (newpassword.getText().toString().equals(""))
            contraseña = null;
        else
            contraseña = newpassword.getText().toString();




        User user = new User(id, nombre, email, contraseña, "active");

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
            Call<Void> call = api.updateUser(user);
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {

                    if (response.isSuccessful()){
                        Toast.makeText(getContext(),"Usuario modificado con éxito",Toast.LENGTH_SHORT).show();
                    }

                    else{
                        Log.e(TAG,"ERROR:" + response.errorBody());
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                }
            });

    }
}
