package edu.upc.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.unity3d.player.UnityPlayerActivity;

public class apiUnity extends AppCompatActivity {
    String token;
    String objetos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        token = intent.getStringExtra("token");
        Singleton.getInstance().requestObjetos(token);
        objetos = Singleton.getInstance().getObjetos();
        Intent i = new Intent(getApplicationContext(), UnityPlayerActivity.class);
        i.putExtra("objetos", objetos);
        startActivity(i);
    }

}