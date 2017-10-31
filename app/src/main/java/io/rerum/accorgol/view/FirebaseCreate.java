package io.rerum.accorgol.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import io.rerum.accorgol.R;
import io.rerum.accorgol.dao.UsuarioDAO;
import io.rerum.accorgol.view.empresario.EmpresarioHome;
import io.rerum.accorgol.view.jogador.Jogador_Perfil;

/**
 * Created by osman on 18/09/2017.
 */

public class FirebaseCreate extends AppCompatActivity{

    private static final String TAG = "Log do app = ";
    private FirebaseAuth mAuth;

    private DatabaseReference mDatabase;

    private EditText email;
    private EditText senha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createuserfirebase);

        email = (EditText) findViewById(R.id.createUserEmail);
        senha = (EditText) findViewById(R.id.createUserSenha);

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onStart() {
        super.onStart();

        mDatabase = FirebaseDatabase.getInstance().getReference();


        UsuarioDAO usuarioDAO = new UsuarioDAO(this);
        if(usuarioDAO.getIdUsuario()  !=  0){
            Log.e("COEEEE", "tem cadastro!");
            Intent intent = new Intent(this, EmpresarioHome.class);
            startActivity(intent);
        }
        else{
            Log.e("COEEEE", "Nao tem cadastro!!");
        }


        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        Log.e(TAG, "quero ver!!!! "+currentUser);
//        updateUI(currentUser);
    }


    private void createAccount(final String email, final String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(FirebaseCreate.this, MainActivity.class);
                            intent.putExtra("email", email);
                            intent.putExtra("senha", password);
                            startActivity(intent);

//                            updateUI(user);
                        } else {
                        }

                        // ...
                    }
                });

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(FirebaseCreate.this, MainActivity.class);
                            intent.putExtra("email", email);
                            intent.putExtra("senha", password);
                            startActivity(intent);
//                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
//                            Log.w(TAG, "signInWithEmail:failure", task.getException());
//                            Toast.makeText(FirebaseCreate.this, "Authentication failed.",
//                                    Toast.LENGTH_SHORT).show();
//                            updateUI(null);
                        }

                        // ...
                    }
                });
//        EmailPasswordActivity.java
    }

    //irá verificar se o usuário ja tem cadastro, caso tenha ele ira para pagina inicial do app.
    public void CreateFireBase(View view) {
        //minimo 6 caracteres para senha
        createAccount(email.getText().toString(), senha.getText().toString());

    }
}
