package com.example.loginproject.views;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.loginproject.R;

public class CadastroActivity extends AppCompatActivity {

    private EditText editEmailCadastro;
    private EditText editSenhaCadastro;
    private Button btnCadastrar;
    private SharedPreferences preferences;          //Salvar login em arquivo

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);

        preferences = getSharedPreferences("login", MODE_PRIVATE);

        editEmailCadastro = findViewById(R.id.editEmailCadastro);
        editSenhaCadastro = findViewById(R.id.editSenhaCadastro);
        btnCadastrar = findViewById(R.id.btnCadastrar);

        btnCadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nome = editEmailCadastro.getText().toString();
                String email = editEmailCadastro.getText().toString();
                String senha = editSenhaCadastro.getText().toString();
                SharedPreferences.Editor editor = preferences.edit();

                editor.putString("nome", nome);
                editor.putString("email", email);
                editor.putString("senha", senha);
                editor.apply();             //editor.commit (jeito antigo, não recomendado)

                Intent intent = new Intent();
                intent.putExtra("email", email);
                intent.putExtra("senha", senha);

                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }
}