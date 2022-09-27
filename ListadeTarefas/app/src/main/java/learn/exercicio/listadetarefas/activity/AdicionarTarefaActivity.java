package learn.exercicio.listadetarefas.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import learn.exercicio.listadetarefas.R;
import learn.exercicio.listadetarefas.helper.ITarefaDAO;
import learn.exercicio.listadetarefas.helper.TarefaDAO;
import learn.exercicio.listadetarefas.model.Tarefa;

public class AdicionarTarefaActivity extends AppCompatActivity {

    private TextInputEditText editTarefa;
    private Tarefa tarefaAtual;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_tarefa);

        editTarefa = findViewById(R.id.textTarefa);

        //Recuperar tarefa, caso seja edição
        tarefaAtual = (Tarefa) getIntent().getSerializableExtra("tarefaSelecionada");

        //Configurar tarefa de texto
        if ( tarefaAtual != null) {
            editTarefa.setText( tarefaAtual.getNomeTarefa());
        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_adicionar_tarefa, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch ( item.getItemId() ){
            case R.id.itemSalvar :
                //Executa ação para o item salvar
            TarefaDAO tarefaDAO = new TarefaDAO(getApplicationContext());
            if ( tarefaAtual != null) {     //Edição

                String nomeTarefa = editTarefa.getText().toString();
                if ( !nomeTarefa.isEmpty() ) {
                    Tarefa tarefa = new Tarefa();
                    tarefa.setNomeTarefa( nomeTarefa );
                    tarefa.setId( tarefaAtual.getId() );

                    //Atualizar no banco de dados
                    if ( tarefaDAO.atualizar( tarefa )){
                        finish();
                        Toast.makeText( getApplicationContext(), "Sucesso ao salvar tarefa!", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText( getApplicationContext(), "Erro ao salvar tarefa!", Toast.LENGTH_SHORT).show();
                    }

                }

            } else {    //Salvar

                String nomeTarefa = editTarefa.getText().toString();

                if ( !nomeTarefa.isEmpty() ) {
                    Tarefa tarefa = new Tarefa();
                    tarefa.setNomeTarefa( nomeTarefa) ;

                    if (tarefaDAO.salvar( tarefa ) ){
                    finish();
                    Toast.makeText( getApplicationContext(), "Sucesso ao salvar tarefa!", Toast.LENGTH_SHORT).show();

                    }else {
                    Toast.makeText( getApplicationContext(), "Erro ao salvar tarefa!", Toast.LENGTH_SHORT).show();

                    }
                }

            }

        }
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }
}