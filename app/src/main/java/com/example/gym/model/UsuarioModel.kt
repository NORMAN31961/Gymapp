package com.example.gym.model

class UsuariosModel(
    var name : String,
    var email : String,
    var password : String,
    var category:String
){
}

object UsuarioListModel{
    val ususarios: List<UsuariosModel> = listOf(
        UsuariosModel("Jean","asd","d112","Free")
    )
}