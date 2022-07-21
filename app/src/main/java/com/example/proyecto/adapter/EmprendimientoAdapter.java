package com.example.proyecto.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto.R;
import com.example.proyecto.model.Empredimiento;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class EmprendimientoAdapter extends FirestoreRecyclerAdapter<Empredimiento, EmprendimientoAdapter.ViewHolder> {
    /**
     * Create a new RecyclerView adapter that listens to a Firestore Query.  See {@link
     * FirestoreRecyclerOptions} for configuration options.
     *
     * @param options
     */
    public EmprendimientoAdapter(@NonNull FirestoreRecyclerOptions<Empredimiento> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Empredimiento model) {
        holder.nombre.setText(model.getNombre());
        holder.contacto.setText(model.getContacto());
        holder.direccion.setText(model.getDireccion());
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_emprendimiento_single, parent, false);
        return new ViewHolder(v);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView nombre, direccion, contacto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombreEmprendimiento);
            direccion = itemView.findViewById(R.id.contactoEmprendimeinto);
            contacto = itemView.findViewById(R.id.direccionEmprendimiento);
        }
    }
}
