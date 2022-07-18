package com.example.proyecto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class RegistroEmprendedor extends AppCompatActivity {
    private Button btnRegister, btnCancelar, btnReset;
    private EditText nombre, apellido, correo, telefono, contrasenia;
    private String userId;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emprendedor);

        nombre = findViewById(R.id.NombreEmprendedor);
        apellido =findViewById(R.id.ApellidoEmprendedor);
        correo = findViewById(R.id.CorreoEmprendedor);
        telefono = findViewById(R.id.TelfEmprendedor);
        contrasenia = findViewById(R.id.editTextTextPassword3);
        btnRegister = findViewById(R.id.btnRegEmprendedor);
        btnCancelar = findViewById(R.id.bntCancEmprendedor);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUserEmprendedor();
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openLoginActivity();
            }
        });

    }

    public void openLoginActivity(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    public void createUserEmprendedor() {
        String name = nombre.getText().toString().trim();
        String lastname = apellido.getText().toString().trim();
        String mail = correo.getText().toString().trim();
        String phone = telefono.getText().toString().trim();
        String password = contrasenia.getText().toString().trim();

        if(TextUtils.isEmpty(name)){
            Toast.makeText(RegistroEmprendedor.this, "Ingrese un Nombre", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(lastname)){
            Toast.makeText(RegistroEmprendedor.this, "Ingrese un Apellido", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(mail)){
            Toast.makeText(RegistroEmprendedor.this, "Ingrese un Correo", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(phone)){
            Toast.makeText(RegistroEmprendedor.this, "Ingrese un Número de Telefono", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(password)){
            Toast.makeText(RegistroEmprendedor.this, "Ingrese una Contraseña", Toast.LENGTH_SHORT).show();
        } else {
            mAuth.createUserWithEmailAndPassword(mail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        userId = mAuth.getCurrentUser().getUid();
                        DocumentReference documentReference = db.collection("users").document(userId);
                        Map<String,Object> user = new HashMap<>();
                        user.put("Nombre",name);
                        user.put("Apellido",lastname);
                        user.put("Correo",mail);
                        user.put("Telefono",phone);
                        user.put("Contrasenia",password);
                        user.put("type","emp");

                        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Log.d("TAG", "onSucess: Datos Registrados "+userId);
                            }
                        });
                        Toast.makeText(RegistroEmprendedor.this, "Usuario Registrado: ", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegistroEmprendedor.this, InicioEmprendedor.class));
                    }else {
                        Toast.makeText(RegistroEmprendedor.this, "Usuario No Registrado: "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}


















































































