import com.example.gym.model.EjercicioModel
import com.example.gym.model.UsuariosModel
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface conexiondb {
    companion object{
        val  url : String = "http://192.168.110.181:4000"
    }
    @GET("/consultarUsuario")
    suspend fun Consultausuarios(): retrofit2.Response<List<UsuariosModel>>

    @POST("/insertarUsuario")
    suspend fun InstarUsuarios(@Body mUsua : UsuariosModel): retrofit2.Response<UsuariosModel>

    @GET("/consultarEjercicio")
    suspend fun ConsultarEjercicio(): retrofit2.Response<List<EjercicioModel>>
}