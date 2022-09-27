package com.example.loginproject.views;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginproject.R;

public class MainActivity extends AppCompatActivity {

    private EditText editEmail;
    private EditText editSenha;

    private SharedPreferences preferences; //Salvar login em arquivo

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editEmail = findViewById(R.id.editEmailCadastro);
        editSenha = findViewById(R.id.editSenhaCadastro);

        preferences = getSharedPreferences("login", MODE_PRIVATE);
    }

    public void entrar(View view) {
        String email = editEmail.getText().toString();
        String senha = editSenha.getText().toString();

        if (email.isEmpty()) {
            editEmail.setError("Campo e-mail obrigatório");
            return;
        }
        if (senha.isEmpty()) {
            editSenha.setError("Campo senha obrigatório");
            return;
        }

        String emailLogin = preferences.getString("email", null);
        String senhaLogin = preferences.getString("senha", null);

        if(email.equals(emailLogin) && senha.equals(senhaLogin)) {
            Intent intent = new Intent(this, PrincipalActivity.class);

            intent.putExtra("email", email);
            intent.putExtra("senha", senha);

            startActivity(intent);

            finish();

        } else {
            Toast.makeText(this, "E-mail ou senha incorreto!", Toast.LENGTH_LONG).show();
        }
    }

    public void cadastrar(View view) {
     //   Toast.makeText(this, "Tela de cadastro", Toast.LENGTH_LONG).show();
     Intent intent = new Intent(this, CadastroActivity.class);
     startActivityForResult(intent, 10);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 10 && resultCode == RESULT_OK) {

            String email = data.getStringExtra("email");
            String senha = data.getStringExtra("senha");

            editEmail.setText(email);
            editSenha.setText(senha);

        }
    }
}