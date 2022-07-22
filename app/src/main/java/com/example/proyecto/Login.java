package com.example.proyecto;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

   private EditText usuario, contraseña;
    private Button iniciarsesion, registrarse;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usuario = findViewById(R.id.Usuario);
        contraseña =findViewById(R.id.password);
        iniciarsesion = findViewById(R.id.btnIngresar);
        registrarse = findViewById(R.id.btnRegistrar);

        mAuth = FirebaseAuth.getInstance();

        registrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRegisterActivity();
            }
        });
        iniciarsesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userLogin();
            }
        });
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menug, menu);
        return true;

    }

    public boolean onOptionsItemSelected(MenuItem item){
        int id = item.getItemId();

        if(id == R.id.acercade){
            Toast.makeText( this,"Acerca de", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, AcercaDE.class);
            startActivity(intent);
        }  else if(id == R.id.descripcion){
            Toast.makeText( this,"Descripcion", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, DescripcionDE.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void openRegisterActivity(){
        Intent intent = new Intent(this, TipoUsuario.class);
        startActivity(intent);
    }

    public void userLogin(){
        Log.i("TAG", "userLogin: entando");
        String mail = usuario.getText().toString().trim();
        String password = contraseña.getText().toString().trim();

        if(TextUtils.isEmpty(mail)){
            Toast.makeText(Login.this, "Ingrese un Correo", Toast.LENGTH_SHORT).show();
        }else if(TextUtils.isEmpty(password)){
            Toast.makeText(Login.this, "Ingrese una contraseña", Toast.LENGTH_SHORT).show();
        }else {
            mAuth.signInWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Toast.makeText(Login.this, "Bienvenid@", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Login.this, Dashboard.class));
                    }else{
                        Log.w("TAG", "Error: ", task.getException());
                    }
                }
            });
        }
    }
}




























