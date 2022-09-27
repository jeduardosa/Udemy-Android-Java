package learn.exercicio.listadetarefas.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.sql.SQLData;
import java.util.ArrayList;
import java.util.List;

import learn.exercicio.listadetarefas.model.Tarefa;

public class TarefaDAO implements ITarefaDAO{

    private SQLiteDatabase escreve;
    private SQLiteDatabase ler;

    public TarefaDAO(Context context) {
        DbHelper db = new DbHelper( context );
        escreve = db.getReadableDatabase();
        ler = db.getReadableDatabase();
    }

    @Override
    public boolean salvar(Tarefa tarefa) {
        ContentValues cv = new ContentValues();
        cv.put("nome", tarefa.getNomeTarefa() );

        try {
            escreve.insert(DbHelper.TABELA_TAREFAS, null, cv);
            Log.i("INFO", "Sucesso ao salvar a tarefa" );
        } catch (Exception e){
            Log.i("INFO", "Erro ao salvar a tarefa" + e.getMessage() );
        }

        return true;
    }

    @Override
    public boolean atualizar(Tarefa tarefa) {
        ContentValues cv = new ContentValues();
        cv.put("Nome", tarefa.getNomeTarefa());

        try {
            String[] args = {tarefa.getId().toString()};
            escreve.update(DbHelper.TABELA_TAREFAS, cv, "id=?", args );
            Log.i("INFO", "Sucesso ao atualizada a tarefa" );
        } catch (Exception e){
            Log.i("INFO", "Erro ao atualizada a tarefa" + e.getMessage() );
            return false;
        }

        return true;
    }

    @Override
    public boolean deletar(Tarefa tarefa) {

        try {
            String[] args = {tarefa.getId().toString()};
            escreve.delete(DbHelper.TABELA_TAREFAS, "id=?", args );
            Log.i("INFO", "Tarefa removida a tarefa" );
        } catch (Exception e){
            Log.i("INFO", "Erro ao remover a tarefa" + e.getMessage() );
            return false;
        }

        return true;
    }

    @Override
    public List<Tarefa> listar() {

        List<Tarefa> tarefas = new ArrayList<>();

        String sql = "SELECT * FROM " +  DbHelper.TABELA_TAREFAS + " ;";
        Cursor c = ler.rawQuery(sql, null);

        while (c.moveToNext() ){
            Tarefa tarefa = new Tarefa();

            Long id = c.getLong( c.getColumnIndex( "id") );
            String nomeTarefa = c.getString( c.getColumnIndex( "nome") );


            tarefa.setId( id );
            tarefa.setNomeTarefa( nomeTarefa );

            //add no array
            tarefas.add( tarefa );
        }

        return tarefas;

    }
}
