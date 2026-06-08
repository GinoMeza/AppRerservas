package com.example.sistemainventario.model

import java.io.Serializable

data class User(
    val id: Int,
    val nombre: String,
    val email: String,
    val telefono: String
) : Serializable
