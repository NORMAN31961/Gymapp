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
            binding.name.text = usuario.name
            binding.Categoria.text = usuario.category
            binding.email.text = usuario.email
            binding.password.text = usuario.password
            binding.membresia.text = usuario.membresia
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