package com.example.proyecto;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TipoUsuario extends AppCompatActivity {

    private Button emprendedor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_usuario);

        emprendedor = findViewById(R.id.btnEmprendedor);
        emprendedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEmprendedorActivity();
            }
        });
    }

    public void openEmprendedorActivity() {
        Intent intent = new Intent(this, RegistroEmprendedor.class);
        startActivity(intent);
    }
}