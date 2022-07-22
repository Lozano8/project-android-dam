package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Resena extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private String userId;
    private FirebaseFirestore db;

    private EditText comentario;
    private Button guardar, cancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resena);

        comentario = findViewById(R.id.comment);
        guardar=findViewById(R.id.btnGuardarResena);
        cancelar=findViewById(R.id.btnCancelarResenia);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        guardar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
        GuardarComentario();
        }
        });
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cancelar();
            }
        });
    }

    public void Cancelar(){
        startActivity(new Intent(Resena.this, BusquedaEmprendimeinto.class));
    }

    public void GuardarComentario(){
        String comment = comentario.getText().toString().trim();
        if(TextUtils.isEmpty(comment)){
            Toast.makeText(Resena.this, "Ingrese un Comentario", Toast.LENGTH_SHORT).show();
        }else{
            FirebaseUser user = mAuth.getCurrentUser();
            if(user == null){
                Toast.makeText(Resena.this, "Inicie Sesión", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(Resena.this, BusquedaEmprendimeinto.class ));
            }else{
                userId = mAuth.getCurrentUser().getUid();
                DocumentReference documentReference = db.collection("comentarios").document(userId);
                Map<String,Object> comentario = new HashMap<>();
                comentario.put("Comentario",comment);

                documentReference.set(comentario).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Log.d("TAG", "onSucess: Comentario del Usuario: "+userId);
                    }
                });
                Toast.makeText(Resena.this, "Comentario Agregado", Toast.LENGTH_SHORT).show();
            }
        }
    }
}