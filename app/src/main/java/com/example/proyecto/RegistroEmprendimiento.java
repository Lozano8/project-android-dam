package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegistroEmprendimiento extends AppCompatActivity {
    private EditText nombre,direccion,contacto,descripcion;
    private Button btnCrear, btnCancelar;
    private String userId;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registroemprendimiento);

        nombre = findViewById(R.id.NameEmprendimiento);
        direccion =findViewById(R.id.DirecEmprendimiento);
        contacto = findViewById(R.id.ContacEmprendimiento);
        descripcion = findViewById(R.id.DescrEmprendimiento);
        btnCancelar = findViewById(R.id.btnCancelarEmprendimiento);
        btnCrear = findViewById(R.id.btnRegistrarEmprendimiento);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        btnCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                crear();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                back();
            }
        });
    }

    public void back(){
        Intent intent = new Intent(this, InicioEmprendedor.class);
        startActivity(intent);
    }

    public void crear(){
        String name = nombre.getText().toString().trim();
        String direction = direccion.getText().toString().trim();
        String contact = contacto.getText().toString().trim();
        String description = descripcion.getText().toString().trim();

        if(TextUtils.isEmpty(name)){
            Toast.makeText(RegistroEmprendimiento.this, "Ingrese un Nombre", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(direction)){
            Toast.makeText(RegistroEmprendimiento.this, "Ingrese una Dirección", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(contact)){
            Toast.makeText(RegistroEmprendimiento.this, "Ingrese una forma de Contacto", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(description)){
            Toast.makeText(RegistroEmprendimiento.this, "Ingrese una Descripción", Toast.LENGTH_SHORT).show();
        }else{
            userId = mAuth.getCurrentUser().getUid();
            DocumentReference documentReference = db.collection("emprendimientos").document(userId);
            Map<String,Object> emprendimiento = new HashMap<>();
            emprendimiento.put("nombre",name);
            emprendimiento.put("direccion",direction);
            emprendimiento.put("descripcion",description);
            emprendimiento.put("contacto",contact);

            documentReference.set(emprendimiento).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void unused) {
                    Log.d("TAG", "onSucess: Emprendimiento del Usuario: "+userId);
                }
            });
            Toast.makeText(RegistroEmprendimiento.this, "Información del emprendimiento Agregada", Toast.LENGTH_SHORT).show();
        }
    }
}