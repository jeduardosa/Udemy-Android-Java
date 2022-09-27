package com.example.loginproject.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.loginproject.R;

import java.util.ArrayList;
import java.util.List;

public class Lista1Activity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista1);

        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        List<String> listCursos = new ArrayList<>(); //Criando um Array de listView

        listCursos.add("Programador de Dispositivos móveis");
        listCursos.add("Programador Web");
        listCursos.add("Programador de Jogos Digitais");
        listCursos.add("Programador de Dispositivos móveis");
        listCursos.add("Programador Web");
        listCursos.add("Programador de Jogos Digitais");
        listCursos.add("Programador de Dispositivos móveis");
        listCursos.add("Programador Web");
        listCursos.add("Programador de Jogos Digitais");
        listCursos.add("Programador de Dispositivos móveis");
        listCursos.add("Programador Web");
        listCursos.add("Programador de Jogos Digitais");
        listCursos.add("Programador de Dispositivos móveis");
        listCursos.add("Programador Web");
        listCursos.add("Programador de Jogos Digitais");

        ListView listView = findViewById(R.id.listview);

        ArrayAdapter<String> listAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listCursos); //Criando o designer da lista
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(this);


    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        String curso = (String) adapterView.getItemAtPosition(position);
        Toast.makeText(this, curso, Toast.LENGTH_SHORT).show();
    }

}