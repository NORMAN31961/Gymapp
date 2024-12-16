import com.example.gym.model.EjercicioModel
import com.example.gym.model.UsuariosModel
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface conexiondb {
    companion object{
        //Url Jean
//        val  url : String = "http://192.168.0.13:4000"
        //Url Chucho
        val  url : String = "http://192.168.80.56:4000"
    }
    @GET("/consultarUsuario")
    suspend fun Consultausuarios(): retrofit2.Response<List<UsuariosModel>>

    @POST("/insertarUsuario")
    suspend fun InstarUsuarios(@Body mUsua : UsuariosModel): retrofit2.Response<UsuariosModel>

    @GET("/consultarEjercicio")
    suspend fun ConsultarEjercicio(): retrofit2.Response<List<EjercicioModel>>

    @DELETE("/elimnarUsuario/{Id}")
    suspend fun EliminarUsuario(@Path("Id") Id:Int): retrofit2.Response<Any>

}