package com.example.loginproject.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.loginproject.R;
import com.example.loginproject.adapter.CursoAdapter;
import com.example.loginproject.dominio.Curso;

import java.util.ArrayList;
import java.util.List;

public class Lista2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista2);

        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        RecyclerView recyclerView = findViewById(R.id.recyclerview);    // Criação da Recycler
        recyclerView.setLayoutManager(new LinearLayoutManager(this));   //Set Layout

        List<Curso> cursoList = new ArrayList<>(); //Integração com a Curso.class

        cursoList.add(new Curso(1, "Programador Moblie"));
        cursoList.add(new Curso(2, "Programador Web"));
        cursoList.add(new Curso(3, "Programador Gamer"));
        cursoList.add(new Curso(4, "Programador Moblie"));
        cursoList.add(new Curso(5, "Programador Web"));
        cursoList.add(new Curso(6, "Programador Gamer"));
        cursoList.add(new Curso(7, "Programador Moblie"));
        cursoList.add(new Curso(8, "Programador Web"));
        cursoList.add(new Curso(9, "Programador Gamer"));


        CursoAdapter cursoAdapter = new CursoAdapter(cursoList, this); //Criação do Adapter

        DividerItemDecoration dic = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        recyclerView.addItemDecoration(dic);

        recyclerView.setAdapter(cursoAdapter);
    }
}