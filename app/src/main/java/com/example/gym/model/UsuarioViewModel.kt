package com.example.gym.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ViewmodelUsuarios: ViewModel() {
    private val _datalistUsuario: MutableLiveData<MutableList<UsuariosModel>> =
        MutableLiveData(mutableListOf())

    val datalistUsuario: MutableLiveData<MutableList<UsuariosModel>>
        get() = _datalistUsuario


    fun addUsuarioList(mUsua: MutableList<UsuariosModel>) {
        val currentList = datalistUsuario.value ?: mutableListOf()
        currentList.addAll(mUsua)
        datalistUsuario.postValue(currentList)
    }

    fun addUsuario(mUsua: UsuariosModel) {
        val currentList = datalistUsuario.value ?: mutableListOf()
        currentList.add(mUsua)
        datalistUsuario.postValue(currentList)
    }

    fun updateUsuario(position: Int, mUsua: UsuariosModel) {
        val currentList = datalistUsuario.value ?: mutableListOf()
        currentList.set(position, mUsua)
        datalistUsuario.postValue(currentList)
    }

    fun removeUsuario(position: Int) {
        val currentList = datalistUsuario.value ?: mutableListOf()

    }
}