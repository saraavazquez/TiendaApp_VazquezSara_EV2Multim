package com.example.tiendaapp_vazquezsara_ev2multim

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.RequestQueue
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import org.json.JSONArray
import com.google.android.material.appbar.MaterialToolbar

class MainActivity : AppCompatActivity() {

    // Spinner para las categorías
    private lateinit var spinnerCategorias: Spinner

    // RecyclerView para los productos
    private lateinit var recyclerProductos: RecyclerView

    // Cola de peticiones de Volley
    private lateinit var queue: RequestQueue

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Configuración de la barra superior
        val toolbar: MaterialToolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Referencias a los elementos del layout
        spinnerCategorias = findViewById(R.id.spinnerCategorias)
        recyclerProductos = findViewById(R.id.recyclerProductos)

        // Layout vertical para la lista de productos
        recyclerProductos.layoutManager = LinearLayoutManager(this)

        // Inicializamos Volley
        queue = Volley.newRequestQueue(this)

        // Cargamos las categorías al iniciar la app
        cargarCategorias()
    }

    // Inflamos el menú superior
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    // Acción al pulsar el menú
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menuCarrito) {
            // Abrimos la pantalla del carrito
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
        return super.onOptionsItemSelected(item)
    }

    // Método para cargar las categorías desde el JSON
    private fun cargarCategorias() {
        val url = "https://dummyjson.com/products/categories"

        val request = JsonArrayRequest(url,
            { response ->
                val categorias = ArrayList<String>()

                // Recorremos el JSON y guardamos los slugs
                for (i in 0 until response.length()) {
                    val obj = response.getJSONObject(i)
                    categorias.add(obj.getString("slug"))
                }

                // Adaptador del spinner
                val adapter = ArrayAdapter(
                    this,
                    android.R.layout.simple_spinner_item,
                    categorias
                )
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                spinnerCategorias.adapter = adapter

                // Detectamos cambio de categoría
                spinnerCategorias.onItemSelectedListener =
                    object : android.widget.AdapterView.OnItemSelectedListener {
                        override fun onItemSelected(
                            parent: android.widget.AdapterView<*>,
                            view: android.view.View?,
                            position: Int,
                            id: Long
                        ) {
                            val categoria = categorias[position]
                            // Cargamos productos de esa categoría
                            cargarProductosPorCategoria(categoria)
                        }

                        override fun onNothingSelected(parent: android.widget.AdapterView<*>) {
                        }
                    }
            },
            { error -> error.printStackTrace() }
        )

        queue.add(request)
    }

    // Carga los productos según la categoría seleccionada
    private fun cargarProductosPorCategoria(categoria: String) {
        val url = "https://dummyjson.com/products/category/$categoria"

        val request = JsonObjectRequest(url, null,
            { response ->
                val array: JSONArray = response.getJSONArray("products")
                val lista = ArrayList<Producto>()

                // Convertimos cada objeto JSON en un objeto Producto
                for (i in 0 until array.length()) {
                    val obj = array.getJSONObject(i)

                    val nombre = obj.getString("title")
                    val precio = obj.getDouble("price")
                    val imagen = obj.getString("thumbnail")

                    lista.add(Producto(nombre, precio, imagen))
                }

                // Asignamos el adaptador al RecyclerView
                val adapter = ProductoAdapter(lista)
                recyclerProductos.adapter = adapter
            },
            { error -> error.printStackTrace() }
        )

        queue.add(request)
    }
}

