package br.curso.escola;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    //Declaração das variáveis
    EditText edtN1, edtN2;
    TextView txtM, txtSit;
    ImageView imgSit;
    LinearLayout layResult;
    InputMethodManager imm; //teclado

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    //Ligações
    edtN1 = findViewById(R.id.edtN1);
    edtN2 = findViewById(R.id.edtN2);
    txtM = findViewById(R.id.txtM);
    txtSit = findViewById(R.id.txtSit);
    imgSit = findViewById(R.id.imgSit);
    layResult = findViewById(R.id.layResult);
    imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    //Resultado invisível antes do calculo
        layResult.setVisibility(View.INVISIBLE);

    }
    public void calcular(View view) {

        boolean ok = true;
        if (edtN1.getText().toString().trim().isEmpty()) {
            ok = false;
            edtN1.setError(getString(R.string.msgErro));
        }
        if (edtN2.getText().toString().trim().isEmpty()) {
            ok = false;
            edtN2.setError(getString(R.string.msgErro));
        }
        if (ok) {
            //Sumir teclado
            imm.hideSoftInputFromWindow(edtN1.getWindowToken(),0);
            //Tornar a imagem da situação visivel
            layResult.setVisibility(View.VISIBLE);
            //Pegar o texto e converter para número real e armazenar na variável.
            float n1 = Float.parseFloat(edtN1.getText().toString());
            float n2 = Float.parseFloat(edtN2.getText().toString());
            //Formula p/ média
            float m = (n1 + n2) / 2;
            //Formato da exibição
            txtM.setText(String.format("%.1f", m));
            //Situação
            if (m < 5) {
                //Reprovado
                txtSit.setText(getString(R.string.strSitRp));
                //txtSit.setTextColor(Color.RED);
                txtSit.setTextColor(getResources().getColor(R.color.corReprovado));
                Toast.makeText(getApplicationContext(), getString(R.string.strMsgRp),Toast.LENGTH_SHORT).show();
                imgSit.setImageResource(R.drawable.emojireprovado);

            } else if (m < 7) {
                //Recuperação
                txtSit.setText(getString(R.string.strSitRc));
                //txtSit.setTextColor(Color.BLUE);
                txtSit.setTextColor(getResources().getColor(R.color.corRecuperacao));
                Toast.makeText(getApplicationContext(), getString(R.string.strMsgRc), Toast.LENGTH_SHORT).show();
                imgSit.setImageResource(R.drawable.emojirecuperacao);
            } else {
                //Aprovado
                txtSit.setText(getString(R.string.strAp));
                //txtSit.setTextColor(Color.GREEN);
                txtSit.setTextColor(getResources().getColor(R.color.corAprovado));
                Toast.makeText(getApplicationContext(), getString(R.string.strMsgAp), Toast.LENGTH_SHORT).show();
                imgSit.setImageResource(R.drawable.emojiaprovado);
            }
        }
    }
}