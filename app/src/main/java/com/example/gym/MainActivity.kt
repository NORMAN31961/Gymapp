package com.example.gym

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gym.adapter.UsuarioAdapter
import com.example.gym.databinding.ActivityMainBinding


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


    }

    private fun initRecyler() {
        binding.recycler.layoutManager = LinearLayoutManager(this)
       // usuarioAdapter = UsuarioAdapter()
        binding.recycler.adapter = usuarioAdapter
    }

    fun replaceFragment(frag: Fragment) {
        val fgTransaction = supportFragmentManager.beginTransaction()
        fgTransaction.replace(R.id.main, frag)
        fgTransaction.addToBackStack(null)
        fgTransaction.commit()
    }


}
