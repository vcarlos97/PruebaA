package edu.upc.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.unity3d.player.UnityPlayerActivity;

import java.util.List;

import edu.upc.login.Entidades.Inventario;

public class apiUnity {

    public static String getObjetos(){
        List<Inventario> objetos = Singleton.getInstance().getObjetos();
        StringBuffer o = new StringBuffer();
        for(int i=0; i<objetos.size(); i++){
            o.append(objetos.get(i).getIdObjeto()+"/");
            if(i!=objetos.size()-1) o.append(objetos.get(i).getCantidad()+"/");
            else o.append(objetos.get(i).getCantidad());
        }
        return o.toString();
    }
}