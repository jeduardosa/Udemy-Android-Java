package learn.exercicio.monitorizze.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.heinrichreimersoftware.materialintro.app.IntroActivity;
import com.heinrichreimersoftware.materialintro.slide.FragmentSlide;

import learn.exercicio.monitorizze.R;
import learn.exercicio.monitorizze.activity.CadastroActivity;
import learn.exercicio.monitorizze.activity.LoginActivity;
import learn.exercicio.monitorizze.config.ConfiguracaoFirebase;

public class MainActivity extends IntroActivity {
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setButtonBackVisible(false);
        setButtonNextVisible(false);

        addSlide(new FragmentSlide.Builder()
                .background(R.color.white)
                .fragment(R.layout.intro_um)
                .build());

        addSlide(new FragmentSlide.Builder()
                .background(R.color.white)
                .fragment(R.layout.intro_dois)
                .build());

        addSlide(new FragmentSlide.Builder()
                .background(R.color.white)
                .fragment(R.layout.intro_tres)
                .build());

        addSlide(new FragmentSlide.Builder()
                .background(R.color.white)
                .fragment(R.layout.intro_quatro)
                .build());

        addSlide(new FragmentSlide.Builder()
                .background(R.color.white)
                .fragment(R.layout.intro_cadastro)
                .canGoForward(false)
                .build());
    }

    @Override
    protected void onStart() {
        super.onStart();
        verificarUsuarioLogado();
    }

    public void btEntrar(View view){
        startActivity(new Intent(this, LoginActivity.class));
    }

    public void btCadastrar(View view){
        startActivity( new Intent(this, CadastroActivity.class));
    }
    public void verificarUsuarioLogado(){
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        //autenticacao.signOut();

        if (autenticacao.getCurrentUser() != null){
            abrirTelaPrincipal();
        }
    }
    public void abrirTelaPrincipal(){
        startActivity(new Intent(this, PrincipalActivity.class));
        }

}