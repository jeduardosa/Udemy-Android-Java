package com.example.projetoparouimpar;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class NumberFragment extends Fragment implements View.OnClickListener {
    private EditText edtNumber;
    private Button btnResultado;

    private OnParImparListener listener;


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);

        if (!(context instanceof OnParImparListener)) {
            throw new RuntimeException("Não é um OnParImparListener");
        }
        listener = (OnParImparListener)context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_number, container, false);

        //Passar a captura dos botoes pra outro fragmento através da Main (FragmentEx1)
        edtNumber = view.findViewById(R.id.edt_number);
        btnResultado = view.findViewById(R.id.btn_result);

        btnResultado.setOnClickListener(this);

        return view;

    }

    @Override
    public void onClick(View view) {
        if (listener != null) {
            int number = Integer.parseInt(edtNumber.getText().toString());
            listener.OnParImpar(number);
        }
    }
    public interface OnParImparListener {
        void OnParImpar (int number);
    }

}
