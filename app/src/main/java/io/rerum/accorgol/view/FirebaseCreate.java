package io.rerum.accorgol.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
        mDatabase = FirebaseDatabase.getInstance().getReference();
        UsuarioDAO usuarioDAO = new UsuarioDAO(this);
        if(usuarioDAO.getIDBanco()  !=  0){
            Log.e("COEEEE", "TEM CADSATRO");
//            aqui sera setado o id do usuario para poder colocar nas URLs do fireabse, pegando fotos e videos DO USUARIO
            Intent intent = new Intent(this, EmpresarioHome .class);
            startActivity(intent);
        } else {
            Log.e("COEEEE", "Nao tem cadastro!!");
        }
//        UsuarioDAO usuarioDAO = new UsuarioDAO(this);
//        if(usuarioDAO.getIDBanco()  !=  0){
//            Log.e("COEEEE", "tem cadastro!");
//            //aqui sera setado o id do usuario para poder colocar nas URLs do fireabse, pegando fotos e videos DO USUARIO
//            UsuarioDAO dao = new UsuarioDAO(getApplicationContext());
//            int id = dao.getIDBanco();
//            HelperDAO helperDAO = new HelperDAO();
//            DaoVideoHelper daoVideoHelper = new DaoVideoHelper();
//            Log.e("USUARIO no começo, ando o valor pro helperDAO", String.valueOf(id));
//            helperDAO.setIdUsuario(id);
//            Intent intent = new Intent(this, Jogador_Perfil.class);
//            startActivity(intent);
//        }
//        else{
//            Log.e("COEEEE", "Nao tem cadastro!!");
//        }


        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        Log.e(TAG, "quero ver!!!! "+currentUser);
        super.onStart();
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
                            Toast.makeText(FirebaseCreate.this, "Conta criada com sucesso!",
                                    Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(FirebaseCreate.this, "Login efetuado com sucesso!",
                                    Toast.LENGTH_SHORT).show();
                            startActivity(intent);
//                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
//                            Log.w(TAG, "signInWithEmail:failure", task.getException());
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

    public static final String PREFS_NAME = "MyPrefsFile";
    private String recuperar(Context context){
        SharedPreferences settings = context.getSharedPreferences(PREFS_NAME, 0);
        return settings.getString("id", "");
    }
}
