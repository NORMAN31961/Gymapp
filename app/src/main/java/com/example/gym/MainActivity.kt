package com.example.gym

import android.database.Observable
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gym.adapter.UsuarioAdapter
import com.example.gym.databinding.ActivityMainBinding
import com.example.gym.models.ViewModelUsuario
import conexiondb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.Dispatcher
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var usuarioAdapter: UsuarioAdapter
    val viewModelUsuario: ViewModelUsuario by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        if (savedInstanceState == null) {
            replaceFragment(HomeCustomerFragment())
        }
        ListarUsuarios()
        observerViemodel()
        initRecyler()

    }

    private fun ListarUsuarios() {
        CoroutineScope(Dispatchers.IO).launch{
            try {
                val call = getRetrofit().create(conexiondb::class.java).Consultausuarios()
                if (call.isSuccessful && call.body()!= null){
                    withContext(Dispatchers.Main){
                        viewModelUsuario.addUsuarioList(call.body()!!.toMutableList())
                    }
                }else{
                    Log.e("dap","Error no se encontro la informacion")
                }
            }
            catch (e : Exception){
                Log.e("dap","No se pudo conectar a la base de datos",e)
            }

        }

    }
    fun getRetrofit():Retrofit{
        return Retrofit.Builder().baseUrl(conexiondb.url)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }

    private fun initRecyler() {
        binding.recycler.layoutManager = LinearLayoutManager(this)
        usuarioAdapter = UsuarioAdapter(viewModelUsuario.datalistUsuario.value?: mutableListOf())
        binding.recycler.adapter = usuarioAdapter
    }
    private fun observerViemodel(){
        viewModelUsuario.datalistUsuario.observe(this, Observer{usuarios ->
            usuarioAdapter.usuario
            usuarioAdapter.notifyDataSetChanged()
        })
    }

    fun replaceFragment(frag: Fragment) {
        val fgTransaction = supportFragmentManager.beginTransaction()
        fgTransaction.replace(R.id.main, frag)
        fgTransaction.addToBackStack(null)
        fgTransaction.commit()
    }


}
