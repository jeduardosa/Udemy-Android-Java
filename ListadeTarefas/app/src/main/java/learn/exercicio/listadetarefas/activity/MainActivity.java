package learn.exercicio.listadetarefas.activity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import learn.exercicio.listadetarefas.R;
import learn.exercicio.listadetarefas.adapter.TarefaAdapter;
import learn.exercicio.listadetarefas.helper.DbHelper;
import learn.exercicio.listadetarefas.helper.RecyclerItemClickListener;
import learn.exercicio.listadetarefas.helper.TarefaDAO;
import learn.exercicio.listadetarefas.model.Tarefa;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TarefaAdapter tarefaAdapter;
    private List<Tarefa> listaTarefas = new ArrayList<>();
    private  Tarefa tarefaSelecionada;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Configurar recycler
        recyclerView = findViewById(R.id.recyclerView);

        DbHelper db = new DbHelper( getApplicationContext() );

        /* ContentValues cv = new ContentValues(  );
        cv.put( "nome",  );

        db.getReadableDatabase().insert("tarefas", null, cv ); */

        //Adicionar evento de click
        recyclerView.addOnItemTouchListener( new RecyclerItemClickListener(
                getApplicationContext(), recyclerView,
                new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {

                        //Recuperar tarefa para edição
                        Tarefa tarefaSelecionada = listaTarefas.get( position );

                        //Enviar tarefa p/ tela adicionar tarefa
                        Intent intent = new Intent(MainActivity.this, AdicionarTarefaActivity.class);
                        intent.putExtra( "tarefaSelecionada", tarefaSelecionada );

                        startActivity( intent );

                    }

                    @Override
                    public void onLongItemClick(View view, int position) {

                        tarefaSelecionada = listaTarefas.get( position );
                        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);

                        //Configurar Titulo e mensagem
                        dialog.setTitle("Confirmar exclusão");
                        dialog.setMessage("Deseja excluir a tarefa: " + tarefaSelecionada.getNomeTarefa() + " ?" );

                        dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                TarefaDAO tarefaDAO = new TarefaDAO( getApplicationContext() );
                                if (tarefaDAO.deletar( tarefaSelecionada )){
                                    carregarListaTarefas();
                                    Toast.makeText( getApplicationContext(), "Sucesso ao excluir tarefa!", Toast.LENGTH_SHORT).show();
                                }else {
                                    Toast.makeText( getApplicationContext(), "Erro ao excluir tarefa!", Toast.LENGTH_SHORT).show();
                                }

                            }
                        });
                        dialog.setNegativeButton("Não", null);  //null sem evento

                        //exibir dialog
                        dialog.create();
                        dialog.show();
                    }

                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                    }
                }
        ));

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),AdicionarTarefaActivity.class);
                startActivity( intent );
            }
        });
    }

    public void carregarListaTarefas(){
        //Listar tarefas
        TarefaDAO tarefaDAO = new TarefaDAO( getApplicationContext() );
        listaTarefas = tarefaDAO.listar();

        /*
        Exibe lista de tarefas no recyclerView
         */

        //Configurar o adapater
        tarefaAdapter = new TarefaAdapter( listaTarefas );

        //Configurar o recyclerview
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager( getApplicationContext() );
        recyclerView.setLayoutManager( layoutManager );
        recyclerView.setHasFixedSize( true );
        recyclerView.addItemDecoration( new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL ));
        recyclerView.setAdapter( tarefaAdapter );
    }

    @Override
    protected void onStart() {
        carregarListaTarefas();
        super.onStart();
    }


}