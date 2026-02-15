package com.example.tiendaapp_vazquezsara_ev2multim

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Adaptador del RecyclerView del carrito
// Se encarga de mostrar los productos añadidos
class CarritoAdapter(private val lista: ArrayList<Producto>) :
    RecyclerView.Adapter<CarritoAdapter.ViewHolder>() {

    // ViewHolder con las referencias a los TextView del layout
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nombre: TextView = view.findViewById(android.R.id.text1)
        val precio: TextView = view.findViewById(android.R.id.text2)
    }

    // Se ejecuta cuando se crea una nueva fila del carrito
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(android.R.layout.simple_list_item_2, parent, false)
        return ViewHolder(view)
    }

    // Número total de productos en el carrito
    override fun getItemCount() = lista.size

    // Asigna los datos de cada producto a la fila correspondiente
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val producto = lista[position]
        holder.nombre.text = producto.nombre
        holder.precio.text = "${producto.precio} €"
    }
}

