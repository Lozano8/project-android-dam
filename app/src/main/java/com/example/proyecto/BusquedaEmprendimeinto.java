package com.example.proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyecto.adapter.EmprendimientoAdapter;
import com.example.proyecto.model.Empredimiento;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class BusquedaEmprendimeinto extends AppCompatActivity {
    private FirebaseFirestore db;
    private RecyclerView mRecycler;
    private EmprendimientoAdapter adapter;
    private Button agregarComentarios,salir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda_emprendimiento);

        agregarComentarios = findViewById(R.id.Resenia);
        salir = findViewById(R.id.SalirBusqueda);


        db = FirebaseFirestore.getInstance();
        mRecycler = findViewById(R.id.recycleViewEmprendimeinto);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        Query query = db.collection("emprendimientos");
        FirestoreRecyclerOptions<Empredimiento> firestoreRecyclerOptions =
                new FirestoreRecyclerOptions.Builder<Empredimiento>().setQuery(query,Empredimiento.class).build();
        adapter = new EmprendimientoAdapter(firestoreRecyclerOptions);
        adapter.notifyDataSetChanged();
        mRecycler.setAdapter(adapter);

        agregarComentarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(BusquedaEmprendimeinto.this, Resena.class));
            }
        });
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cancelar();
            }
        });


    }
    public void Cancelar(){
        startActivity(new Intent(BusquedaEmprendimeinto.this, InicioUsuario.class));
    }


    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}