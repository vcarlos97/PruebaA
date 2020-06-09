package edu.upc.login.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import edu.upc.login.Entidades.Inventario;
import edu.upc.login.R;

public class AdapterInventario extends RecyclerView.Adapter<AdapterInventario.ViewHolder> implements View.OnClickListener {
    LayoutInflater inflater;
    ArrayList<Inventario> model;

    //listener
    private View.OnClickListener listener;

    public AdapterInventario(Context context, ArrayList<Inventario> model){
        this.inflater= LayoutInflater.from(context);
        this.model=model;

    }

    @NonNull
    @Override
    public AdapterInventario.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.lista_items,parent,false);
        view.setOnClickListener(this);
        return new AdapterInventario.ViewHolder(view);
    }

    public void setOnClickListener (View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterInventario.ViewHolder holder, int position) {

        int idObjeto = model.get(position).getIdObjeto();
        int cantidad = model.get(position).getCantidad();
        //int imagen = model.get(position).getImagenid();
        holder.nombre.setText("" + idObjeto);
        holder.cantidad.setText("" + cantidad);
        //holder.imagen.setImageResource(imagen);
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    @Override
    public void onClick(View view) {
        if(listener!=null)
            listener.onClick(view);

    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView nombre, cantidad;
        ImageView imagen;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre=itemView.findViewById(R.id.nombre_item);
            cantidad=itemView.findViewById(R.id.cantidad_item);
            //imagen=itemView.findViewById(R.id.imagen_item);
        }
    }
}
