package com.example.tiendaapp_vazquezsara_ev2multim

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import android.widget.Toast

// Adaptador del RecyclerView de la pantalla principal
// Se encarga de mostrar cada producto en la lista
class ProductoAdapter(private val lista: ArrayList<Producto>) :
    RecyclerView.Adapter<ProductoAdapter.ViewHolder>() {

    // ViewHolder que contiene las referencias a los elementos del layout
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val img: ImageView = view.findViewById(R.id.imgProducto)
        val nombre: TextView = view.findViewById(R.id.txtNombre)
        val precio: TextView = view.findViewById(R.id.txtPrecio)
        val boton: Button = view.findViewById(R.id.btnAgregar)
    }

    // Se ejecuta cuando se crea una nueva fila del RecyclerView
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_producto, parent, false)
        return ViewHolder(view)
    }

    // Número total de productos en la lista
    override fun getItemCount() = lista.size

    // Se ejecuta para mostrar los datos de cada producto
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val producto = lista[position]

        // Mostramos nombre y precio
        holder.nombre.text = producto.nombre
        holder.precio.text = "${producto.precio} €"

        // Cargamos la imagen con Glide
        Glide.with(holder.itemView.context)
            .load(producto.imagen)
            .into(holder.img)

        // Botón para añadir el producto al carrito
        holder.boton.setOnClickListener {
            Carrito.productos.add(producto)

            // Mensaje para confirmar que se ha añadido
            Toast.makeText(
                holder.itemView.context,
                "Producto añadido",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
