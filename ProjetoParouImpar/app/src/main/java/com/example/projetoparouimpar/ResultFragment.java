package com.example.projetoparouimpar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ResultFragment extends Fragment {
    private TextView txtResult;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_result, container, false);

        txtResult = view.findViewById(R.id.txt_result);

        return view;
    }
    public void result (int number) {
        if (number % 2 == 0) {
            txtResult.setText(String.format("O número %d é par ", number ));
        }else{
            txtResult.setText(String.format("O número %d é ímpar ", number));
        }
    }
}
