package edu.upc.login.Fragments;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.upc.login.iComunicaFragments;
import edu.upc.login.Adaptadores.AdapterItem;
import edu.upc.login.Entidades.Item;
import edu.upc.login.R;

public class FragmentItems extends Fragment {

    AdapterItem adapterItem;
    RecyclerView recyclerViewItems;
    ArrayList<Item> listaItems;
    //referencias para comunicar fragment
    Activity actividad;
    iComunicaFragments interfaceComunicaFragments;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.item_fragment,container,false);
        recyclerViewItems = view.findViewById(R.id.recyclerView);
        listaItems = new ArrayList<Item>();
        //cargar la lista
        cargarLista();
        //mostrar datos
        mostrarDatos();

        return view;
    }
    public void cargarLista(){
        listaItems.add(new Item(1,"Desinfectante","Obtén +10 de vida", 75,  R.drawable.des1));
        listaItems.add(new Item(2,"Desinfectante +","Obtén +25 de vida", 125, R.drawable.des2));
        listaItems.add(new Item(3,"Desinfectante Pro","Obtén +50 de vida", 200,R.drawable.des3));
        listaItems.add(new Item(4,"Mascarilla ","Incrementa en +25 tu vida al empezar", 150, R.drawable.mas1));
        listaItems.add(new Item(5,"Mega Mascarilla","Incrementa en +50 tu vida al empezar", 300, R.drawable.mas2));
        listaItems.add(new Item(6,"Pastilla de Jabón","Cúrate del virus",25, R.drawable.soap));
    }

    public void mostrarDatos(){

        recyclerViewItems.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterItem = new AdapterItem(getContext(),listaItems);
        recyclerViewItems.setAdapter(adapterItem);
        adapterItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                interfaceComunicaFragments.enviarObjeto(listaItems.get(recyclerViewItems.getChildAdapterPosition(view)));
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
