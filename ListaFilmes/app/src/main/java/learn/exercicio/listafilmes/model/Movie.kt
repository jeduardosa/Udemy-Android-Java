package learn.exercicio.listafilmes.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

class Movie {

    @Parcelize
    data class Movie(
        val id: Int,
        val titulo: String,
        val imagem: String,
        val descricao: String?,
        @SerializedName("data_lancamento")
        val dataLancamento: String?
    ): Parcelable
}