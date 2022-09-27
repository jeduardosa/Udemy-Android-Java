package learn.exercicio.listafilmes

import android.graphics.Movie
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val listOfMovies = arrayListOf(
        Movie(
            id = 0,
            titulo = "Titanic",
            descricao = null,
            imagem = null,
            dataLancamento = null
        ),
        Movie(
            id = 0,
            titulo = "Central do Brasil",
            descricao = null,
            imagem = null,
            dataLancamento = null
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    private fun populateList() {
        moviesList.apply {
            hasFixedSize()
        adapter = MoviesAdapter(listOfMovies)
        }
    }
}
