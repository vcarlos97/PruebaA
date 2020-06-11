package edu.upc.login.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

import edu.upc.login.Entidades.Inventario;
import edu.upc.login.R;

public class AdapterInventario extends RecyclerView.Adapter<AdapterInventario.ViewHolder> implements View.OnClickListener {
    LayoutInflater inflater;
    List<Inventario> model;

    //listener
    private View.OnClickListener listener;

    public AdapterInventario(Context context, List<Inventario> model){
        this.inflater= LayoutInflater.from(context);
        this.model=model;

    }

    @NonNull
    @Override
    public AdapterInventario.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.inventario_item,parent,false);
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
        switch (idObjeto){
            case 1:
                holder.nombre.setText("Desinfectante");
                holder.imagen.setImageResource(R.drawable.des1);
                break;
            case 2:
                holder.nombre.setText("Desinfectante +");
                holder.imagen.setImageResource(R.drawable.des2);
                break;
            case 3:
                holder.nombre.setText("Desinfectante Pro");
                holder.imagen.setImageResource(R.drawable.des3);
                break;
            case 4:
                holder.nombre.setText("Mascarilla");
                holder.imagen.setImageResource(R.drawable.mas1);
                break;
            case 5:
                holder.nombre.setText("Mega Mascarilla ");
                holder.imagen.setImageResource(R.drawable.mas2);
                break;
            case 6:
                holder.nombre.setText("Pastilla de Jabón");
                holder.imagen.setImageResource(R.drawable.soap);
                break;
        }
        holder.cantidad.setText("Cantidad: " + cantidad);

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
            imagen=itemView.findViewById(R.id.imagen_item);
        }
    }
}
