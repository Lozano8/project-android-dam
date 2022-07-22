package com.example.proyecto;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proyecto.model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class Dashboard extends AppCompatActivity {
    private String userId;
    private FirebaseAuth mAuth;
    private FirebaseFirestore db;;
    private User userData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_dashboard);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
    }

    protected void onStart(){
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        userId = mAuth.getCurrentUser().getUid();
        Log.i("TAG", "userId: "+userId);
        DocumentReference docRef = db.collection("users").document(userId);;
        if(user == null){
            startActivity(new Intent(Dashboard.this, Login.class ));
        }else {
            docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    Log.i("TAG", "userLogin: entrando emp" + documentSnapshot.toObject(User.class));
                    User userData = documentSnapshot.toObject(User.class);

                    Log.i("TAG", "userLogin: type: "+userData.getType());

                    String type = userData.getType().toString().trim();

                    if(type.equals("emp")){
                        Log.i("TAG", "userLogin: entrando emp");
                        startActivity(new Intent(Dashboard.this, InicioEmprendedor.class ));
                    }else if(type.equals("user")){
                        Log.i("TAG", "userLogin: entrando user");
                        startActivity(new Intent(Dashboard.this, InicioUsuario.class ));
                    }else{
                        Log.i("TAG", "userLogin: no encontrando");
                        Toast.makeText(Dashboard.this, "Usuario no encontrado", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Dashboard.this, Login.class ));
                    }
                }
            });

        }
    }

}




























