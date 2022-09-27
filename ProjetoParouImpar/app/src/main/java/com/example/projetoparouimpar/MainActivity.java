package com.example.projetoparouimpar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements NumberFragment.OnParImparListener {
    private  ResultFragment resultFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    resultFragment = (ResultFragment)getSupportFragmentManager().findFragmentById(R.id.frag_result);
    }

    @Override
    public void OnParImpar(int number) {
        resultFragment.result(number);
    }
}