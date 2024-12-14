package com.example.gym.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.motion.widget.MotionScene.Transition.TransitionOnClick
import androidx.recyclerview.widget.RecyclerView
import com.example.gym.R
import com.example.gym.databinding.ItemExcerciseBinding
import com.example.gym.model.EjercicioModel


class EjercicioAdapter (var ejerciciosList : List<EjercicioModel>,
                        private val onViewClick: (EjercicioModel, Int) -> Unit
                        ): RecyclerView.Adapter<EjercicioAdapter.EjercicioViewHolder>() {

    class EjercicioViewHolder(view : View): RecyclerView.ViewHolder(view) {
        val binding = ItemExcerciseBinding.bind(view)

        fun inizializar(ejercicio: EjercicioModel,
                        onViewClick: (EjercicioModel, Int) -> Unit){
            binding.itemTvExcercise.text = ejercicio.Nombre
            ejercicio.Descripcion
            ejercicio.Url_Gif
            itemView.setOnClickListener{
                onViewClick(ejercicio, getLayoutPosition())
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EjercicioViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_excercise,parent,false)
        return EjercicioViewHolder(view)
    }

    override fun getItemCount() = ejerciciosList.size

    override fun onBindViewHolder(holder: EjercicioViewHolder, position: Int) {
        holder.inizializar(
            ejerciciosList[position],
            onViewClick
        )
    }
}