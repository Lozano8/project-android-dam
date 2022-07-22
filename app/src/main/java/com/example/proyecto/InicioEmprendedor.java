package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class InicioEmprendedor extends AppCompatActivity {

    private Button salir, crearEmprendimiento;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_emprendedor);
        mAuth = FirebaseAuth.getInstance();

        salir= findViewById(R.id.btnSalirEmp);
        crearEmprendimiento= findViewById(R.id.btnCrearEmprendimiento);

        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Toast.makeText(InicioEmprendedor.this, "Sesión Cerrada", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(InicioEmprendedor.this, Login.class));
            }
        });
        crearEmprendimiento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(InicioEmprendedor.this, RegistroEmprendimiento.class));
            }
        });
    }
}