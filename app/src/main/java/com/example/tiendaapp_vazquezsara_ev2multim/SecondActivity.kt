package com.example.tiendaapp_vazquezsara_ev2multim

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.appbar.MaterialToolbar

class SecondActivity : AppCompatActivity() {

    // RecyclerView del carrito
    private lateinit var recycler: RecyclerView

    // TextView del total
    private lateinit var txtTotal: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        // Configuración de la toolbar
        val toolbar: MaterialToolbar = findViewById(R.id.toolbarSecond)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        recycler = findViewById(R.id.recyclerCarrito)
        txtTotal = findViewById(R.id.txtTotal)

        // Lista vertical
        recycler.layoutManager = LinearLayoutManager(this)

        // Adaptador con los productos del carrito
        recycler.adapter = CarritoAdapter(Carrito.productos)

        // Calculamos el total
        calcularTotal()
    }

    // Botón de volver atrás
    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }

    // Menú superior del carrito
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_second, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {

            // Confirmar compra
            R.id.menuConfirmar -> {
                var total = 0.0
                for (p in Carrito.productos) {
                    total += p.precio
                }

                Snackbar.make(
                    findViewById(android.R.id.content),
                    "Enhorabuena, compra por valor de $total € realizada",
                    Snackbar.LENGTH_LONG
                ).show()
            }

            // Vaciar carrito
            R.id.menuVaciar -> {
                Carrito.productos.clear()
                recycler.adapter?.notifyDataSetChanged()
                txtTotal.text = "Total: 0 €"

                Snackbar.make(
                    findViewById(android.R.id.content),
                    "Carrito vaciado",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    // Cálculo del precio total
    private fun calcularTotal() {
        var total = 0.0
        for (p in Carrito.productos) {
            total += p.precio
        }
        txtTotal.text = "Total: %.2f €".format(total)
    }
}
