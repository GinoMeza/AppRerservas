package com.example.sistemainventario.model

data class Product(
    val id: Int = 0,
    val nombre: String,
    val cantidad: Int,
    val precio: Double
)
