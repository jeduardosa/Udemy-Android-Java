package com.example.loginproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.loginproject.dominio.Curso;

import java.util.List;

public class CursoAdapter extends RecyclerView.Adapter<CursoAdapter.CursoHolder> {

    private List<Curso> cursos;
    private Context context;

    public CursoAdapter(List<Curso> cursos, Context context) {
        this.cursos = cursos;
        this.context = context;
    }

    @NonNull
    @Override
    public CursoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {        //Layout da linha
        View view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_2, parent, false);
        CursoHolder cursoHolder = new CursoHolder(view);
        return cursoHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CursoHolder holder, int position) {
        Curso curso = cursos.get(position);
        holder.txtId.setText(String.valueOf(curso.getId()));
        holder.txtNome.setText(curso.getNome());
    }

    @Override
    public int getItemCount() {
        return cursos.size();
    }

    public class CursoHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txtId;
        private TextView txtNome;

        public CursoHolder(@NonNull View view) {
            super(view);

            txtId = view.findViewById(android.R.id.text1);
            txtNome = view.findViewById(android.R.id.text2);

            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int pos = getLayoutPosition();
            cursos.remove(pos);
            Toast.makeText(context, "item removido com sucesso!", Toast.LENGTH_SHORT).show();
            notifyItemRemoved(pos);
        }
    }

}
