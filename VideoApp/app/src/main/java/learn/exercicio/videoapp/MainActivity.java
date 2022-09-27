package learn.exercicio.videoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickEquipe(View view) {
        Intent intent = new Intent(this, Equipe.class);
        startActivity(intent);
    }

    public void clickCursos(View view) {
        Intent intent = new Intent(this, Cursos.class);
        startActivity(intent);
    }

    public void clickMidia(View view) {
        Intent intent = new Intent(this, Midia.class);
        startActivity(intent);
    }
}