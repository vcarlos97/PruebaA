package edu.upc.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


//preguntar como arreglar tema de diseño en el layout,
public class MainActivity extends Activity {

    private API api;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Creamos interceptor
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //Creamos cliente
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();

        //Crear retrofit
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:8080/dsaApp/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        //Llamamos a servicios que hemos definido en la API
        api = retrofit.create(API.class);

        //Inicializamos el LayOut
        final TextView nombre = findViewById((R.id.nombreText));
        final TextView password = findViewById((R.id.passwordText));
        Button registrarseButton = findViewById(R.id.registrarseButton);
        registrarseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(i);
            }
        });

        Button pruebaHome = findViewById(R.id.pruebaHome);
        pruebaHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this,HomeActivity.class);
                startActivity(i);
            }
        });


        Button loginButton = findViewById(R.id.loginButton);
        //Cuando clickamos en loginButton -> Creamos llamamos a los strings nombre y password, los metemos en variable login
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginCredentials loginc = new LoginCredentials();
                loginc.setNombre((String) nombre.getText().toString());
                loginc.setPassword((String) password.getText().toString());

                Call<LoginCredentials> call = api.login(loginc);

                call.enqueue(new Callback<LoginCredentials>() {
                    @Override
                    public void onResponse(Call<LoginCredentials> call, Response<LoginCredentials> response) {
                        if(response.isSuccessful()) {
                            Intent i = new Intent(MainActivity.this, HomeActivity.class);
                            startActivity(i);
                        }

                        else {
                            Toast.makeText(getApplicationContext(), "Error " + response.code() + ": " +response.message(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginCredentials> call, Throwable t) {
                        Toast toast = Toast.makeText(getApplicationContext(), "Error al acceder a la API", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                });
            }

            });

    }


}
