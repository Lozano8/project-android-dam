package com.example.proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class InicioUsuario extends AppCompatActivity {

    private Button miPerfil, listarEmprendimientos, salir;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_usuario);

        mAuth = FirebaseAuth.getInstance();

        miPerfil = findViewById(R.id.btnMiperfil);
        listarEmprendimientos = findViewById(R.id.btnlistarEmprendimientos);
        salir = findViewById(R.id.btnSalirEmp);

        listarEmprendimientos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openListarActivity();
            }
        });
        salir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Toast.makeText(InicioUsuario.this, "Sesión Cerrada", Toast.LENGTH_SHORT).show();
                salir();
            }
        });
    }
    public void openListarActivity(){
        Intent intent = new Intent(this, BusquedaEmprendimeinto.class);
        startActivity(intent);
    }

    public void salir(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}