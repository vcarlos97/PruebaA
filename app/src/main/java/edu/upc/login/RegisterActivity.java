package edu.upc.login;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {
    private API api;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

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

        final TextView nombre = findViewById(R.id.nombreText);
        final TextView mail = findViewById(R.id.correoText);
        final TextView password = findViewById(R.id.contraseñaText);
        final TextView confPassword = findViewById(R.id.confContraseñaText);
        Button registrarseButton = findViewById(R.id.registrarseButton);
            registrarseButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   RegisterCredentials registerC = new RegisterCredentials();
                   registerC.setNombre((String) nombre.getText());
                   registerC.setNombre((String) mail.getText());
                   registerC.setPassword((String) password.getText());
                   registerC.setPassword((String) confPassword.getText());

                   Call<RegisterCredentials> call = api.register(registerC);
                    call.enqueue(new Callback<RegisterCredentials>() {
                        @Override
                        public void onResponse(Call<RegisterCredentials> call, Response<RegisterCredentials> response) {
                            if(response.isSuccessful()) {
                                Intent i = new Intent(RegisterActivity.this, HomeActivity.class);
                                startActivity(i);
                            }

                            else {
                                Toast.makeText(getApplicationContext(), "Error " + response.code() + ": " +response.message(), Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<RegisterCredentials> call, Throwable t) {
                            Toast toast = Toast.makeText(getApplicationContext(), "Error al acceder a la API", Toast.LENGTH_SHORT);
                            toast.show();
                        }
                    });


                }
            });
    }

}
