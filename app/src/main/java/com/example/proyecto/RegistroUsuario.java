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

public class RegistroUsuario extends AppCompatActivity {
    private Button Registrar, Cancelar, Borrar;
    private EditText Nombre, Apellido, Email, Telefono, Contra;
    private String usuarioId;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_usuario);
        Nombre= findViewById(R.id.txtNombre);
        Apellido= findViewById(R.id.txtApellido);
        Email= findViewById(R.id.txtEmail);
        Telefono= findViewById(R.id.txtTelefono);
        Contra= findViewById(R.id.txtContraseña);

        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();

        Registrar= findViewById(R.id.btnRegistrarU);
        Borrar= findViewById(R.id.btnBorrarU);
        Cancelar= findViewById(R.id.btnCancelarU);

        Registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createUser();
            }
        });
    }

    public void createUser() {
        String name = Nombre.getText().toString().trim();
        String lastname = Apellido.getText().toString().trim();
        String mail = Email.getText().toString().trim();
        String phone = Telefono.getText().toString().trim();
        String password = Contra.getText().toString().trim();

        if(TextUtils.isEmpty(name)){
            Toast.makeText(RegistroUsuario.this, "Ingrese un Nombre", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(lastname)){
            Toast.makeText(RegistroUsuario.this, "Ingrese un Apellido", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(mail)){
            Toast.makeText(RegistroUsuario.this, "Ingrese un Correo", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(phone)){
            Toast.makeText(RegistroUsuario.this, "Ingrese un Número de Telefono", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(password)){
            Toast.makeText(RegistroUsuario.this, "Ingrese una Contraseña", Toast.LENGTH_SHORT).show();
        } else {
            mAuth.createUserWithEmailAndPassword(mail, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        usuarioId = mAuth.getCurrentUser().getUid();
                        DocumentReference documentReference = db.collection("users").document(usuarioId);
                        Map<String,Object> user = new HashMap<>();
                        user.put("Nombre",name);
                        user.put("Apellido",lastname);
                        user.put("Correo",mail);
                        user.put("Telefono",phone);
                        user.put("Contrasenia",password);
                        user.put("type","user");

                        documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Log.d("TAG", "onSucess: Datos Registrados ");
                            }
                        });
                        Toast.makeText(RegistroUsuario.this, "Usuario Registrado", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegistroUsuario.this, InicioUsuario.class));
                    }else {
                        Toast.makeText(RegistroUsuario.this, "Usuario No Registrado: "+task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}