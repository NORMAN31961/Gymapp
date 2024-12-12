package com.example.gym.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gym.R
import com.example.gym.databinding.ActivityItemsUsuariosBinding
import com.example.gym.model.UsuariosModel


class UsuarioAdapter(var usuario : List<UsuariosModel>): RecyclerView.Adapter<UsuarioAdapter.UsuarioViewHolder>() {
    class UsuarioViewHolder(view : View): RecyclerView.ViewHolder(view) {
        val binding = ActivityItemsUsuariosBinding.bind(view)
        fun inizializar(usuario: UsuariosModel){
            binding.name.text = usuario.Nombre
            binding.Categoria.text = usuario.Categoria
            binding.email.text = usuario.Corre
            binding.password.text = usuario.Contrasena
            binding.membresia.text = usuario.Membresia
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsuarioViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.activity_items_usuarios,parent,false)
        return UsuarioViewHolder(view)
    }

    override fun getItemCount() = usuario.size


    override fun onBindViewHolder(holder: UsuarioViewHolder, position: Int) {
        holder.inizializar(usuario[position])
    }

}