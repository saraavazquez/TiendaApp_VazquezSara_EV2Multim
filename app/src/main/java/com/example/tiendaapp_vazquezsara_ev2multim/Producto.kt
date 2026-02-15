package com.example.tiendaapp_vazquezsara_ev2multim

import java.io.Serializable

// Clase modelo que representa un producto de la tienda
data class Producto(
    val nombre: String,
    val precio: Double,
    val imagen: String
) : Serializable
