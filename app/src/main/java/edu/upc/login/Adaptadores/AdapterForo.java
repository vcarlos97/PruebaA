package edu.upc.login.Adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

import edu.upc.login.Entidades.Foro;
import edu.upc.login.R;

public class AdapterForo extends RecyclerView.Adapter<AdapterForo.ViewHolder> implements View.OnClickListener {

    LayoutInflater inflater;
    private List<Foro> comentarios;
    private View.OnClickListener listener;

    public AdapterForo(Context context, List<Foro> model){
        this.comentarios = model;
        this.inflater = LayoutInflater.from(context);
    }

    public void setOnClickListener (View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onClick(View view) {
        if(listener!=null)
            listener.onClick(view);

    }


    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView nombre, comentario, fecha;
        public View layout;
        public ViewHolder(View v){
            super(v);
            layout=v;
            nombre=v.findViewById(R.id.username);
            comentario=v.findViewById(R.id.comentario);
            fecha=v.findViewById(R.id.fecha);
        }
    }

    @NonNull
    @Override
    public AdapterForo.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.comentario_layout,parent,false);
        return new AdapterForo.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterForo.ViewHolder holder, int position) {
        Foro foro = comentarios.get(position);
        holder.nombre.setText(foro.getNombre());
        holder.comentario.setText(foro.getComentario());
        holder.fecha.setText(foro.getFecha());
    }

    @Override
    public int getItemCount() {
        return comentarios.size();
    }
}



/*LayoutInflater inflater;
    List<Foro> model;

    //listener
    private View.OnClickListener listener;

    public AdapterForo(Context context, List<Foro> model){
        this.inflater= LayoutInflater.from(context);
        this.model=model;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.comentario_layout,parent,false);
        view.setOnClickListener(this);
        return new ViewHolder(view);
    }

    public void setOnClickListener (View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String nombre=model.get(position).getNombre();
        String comentario=model.get(position).getComentario();
        String fecha = model.get(position).getFecha();
        holder.nombre.setText(nombre);
        holder.comentario.setText(comentario);
        holder.fecha.setText(fecha);
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

        TextView nombre, comentario, fecha;

        public ViewHolder(@NonNull View foroView) {
            super(foroView);
            nombre=foroView.findViewById(R.id.username);
            comentario=foroView.findViewById(R.id.comentario);
            fecha=foroView.findViewById(R.id.fecha);
            //imagen=itemView.findViewById(R.id.foto);
        }
    }*/
