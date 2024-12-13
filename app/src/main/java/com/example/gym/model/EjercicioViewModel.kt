package com.example.gym.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.gym.model.EjercicioModel

class EjercicioViewModel : ViewModel() {
    private val _datalistEjercicio: MutableLiveData<MutableList<EjercicioModel>> =
        MutableLiveData(mutableListOf())

    val datalistEjercicio: MutableLiveData<MutableList<EjercicioModel>>
        get() = _datalistEjercicio


    fun addEjercicioList(mEje: MutableList<EjercicioModel>) {
        val currentList = datalistEjercicio.value ?: mutableListOf()
        currentList.addAll(mEje)
        datalistEjercicio.postValue(currentList)
    }

    fun addEjercicio(mEje: EjercicioModel) {
        val currentList = datalistEjercicio.value ?: mutableListOf()
        currentList.add(mEje)
        datalistEjercicio.postValue(currentList)
    }

    fun updateEjercicio(position: Int, mEje: EjercicioModel) {
        val currentList = datalistEjercicio.value ?: mutableListOf()
        currentList.set(position, mEje)
        datalistEjercicio.postValue(currentList)
    }

    fun removeEjercicio(position: Int) {
        val currentList = datalistEjercicio.value ?: mutableListOf()

    }
}