package learn.exercicio.monitorizze.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import learn.exercicio.monitorizze.R;
import learn.exercicio.monitorizze.config.ConfiguracaoFirebase;
import learn.exercicio.monitorizze.helper.Base64Custom;
import learn.exercicio.monitorizze.helper.DateCustom;
import learn.exercicio.monitorizze.model.Movimentacao;
import learn.exercicio.monitorizze.model.Usuario;

public class DespesasActivity extends AppCompatActivity {

    private TextInputEditText campoData, campoCategoria, campoDescricao;
    private EditText campoValor;
    private Movimentacao movimentacao;
    private DatabaseReference firebaseRef = ConfiguracaoFirebase.getFirebaseDataBase();
    private FirebaseAuth autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
    private Double despesaTotal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesa);

        campoValor = findViewById(R.id.editValor);
        campoData = findViewById(R.id.editData);
        campoCategoria = findViewById(R.id.editCategoria);
        campoDescricao = findViewById(R.id.editDescricao);

        //Preenche o campo data com a data atual
        campoData.setText( DateCustom.dataAtual() );
        recuperarDespesaTotal();
    }

    public void salvarDespesa(View view){

        if ( validarCamposDespesa() ) {
            movimentacao = new Movimentacao();
            String data = campoData.getText().toString();
            Double valorRecuperado = Double.parseDouble(campoValor.getText().toString());
            movimentacao.setValor( valorRecuperado );
            movimentacao.setCategoria(campoCategoria.getText().toString());
            movimentacao.setDescricao(campoDescricao.getText().toString());
            movimentacao.setData(data);
            movimentacao.setTipo("d");

            Double despesaAtualizada = despesaTotal + valorRecuperado;
            atualizarDespesa( despesaAtualizada );

            movimentacao.salvar(data);
        }
    }

    public Boolean validarCamposDespesa(){

        String textoValor = campoValor.getText().toString();
        String textoData = campoData.getText().toString();
        String textoCategoria = campoCategoria.getText().toString();
        String textoDescricao = campoDescricao.getText().toString();

        if ( !textoValor.isEmpty() ){
            if ( !textoData.isEmpty() ){
                if ( !textoCategoria.isEmpty() ){
                    if ( !textoDescricao.isEmpty() ){

                        return true;
                    }else{
                        Toast.makeText(DespesasActivity.this, "Descrição não foi preenchido!", Toast.LENGTH_SHORT).show();
                        return false;
                    }
                }else{
                    Toast.makeText(DespesasActivity.this, "Categoria não foi preenchido!", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }else{
                Toast.makeText(DespesasActivity.this, "Data não foi preenchido!", Toast.LENGTH_SHORT).show();
                return false;
            }
        }else{
            Toast.makeText(DespesasActivity.this, "Valor não foi preenchido!", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public void recuperarDespesaTotal(){
        String emailUsuario = autenticacao.getCurrentUser().getEmail();
        String idUsuario = Base64Custom.codificarBase64( emailUsuario );
        DatabaseReference usuarioRef = firebaseRef.child("usuarios").child( idUsuario );

        usuarioRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Usuario usuario = snapshot.getValue( Usuario.class );
                despesaTotal = usuario.getDespesaTotal();
            }

            @Override
            public void onCancelled(DatabaseError error) {

            }
        });
    }
    public void atualizarDespesa(Double despesa){
        String emailUsuario = autenticacao.getCurrentUser().getEmail();
        String idUsuario = Base64Custom.codificarBase64( emailUsuario );
        DatabaseReference usuarioRef = firebaseRef.child("usuarios").child( idUsuario );

        usuarioRef.child("despesaTotal").setValue( despesa );
    }
}