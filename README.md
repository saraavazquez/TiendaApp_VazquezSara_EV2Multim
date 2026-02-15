# TiendaApp – EV2 Programación Multimedia

Aplicación Android desarrollada en Kotlin que simula el funcionamiento de una tienda de productos.  
Permite visualizar productos por categorías, añadirlos a un carrito y confirmar la compra.



## Autor
**Sara Vázquez**  
2º DAM – Programación Multimedia y Dispositivos Móviles



## Tecnologías utilizadas
- Kotlin
- Android Studio
- RecyclerView
- Volley (peticiones a internet)
- Glide (carga de imágenes)
- Material Design



## Estructura del proyecto

### MainActivity
Pantalla principal de la aplicación.

Funciones principales:
- Muestra un spinner con las categorías.
- Carga los productos desde la API.
- Muestra los productos en un RecyclerView.
- Permite añadir productos al carrito.
- Menú superior con acceso al carrito.



### SecondActivity
Pantalla del carrito.

Funciones:
- Muestra los productos añadidos.
- Calcula el precio total.
- Menú con:
  - Confirmar compra.
  - Vaciar carrito.
- Botón para volver a la pantalla principal.



### Producto
Clase modelo que representa cada producto.

Atributos:
- nombre
- precio
- imagen



### Carrito
Objeto global que almacena los productos añadidos.

Permite compartir los productos entre activities.



### ProductoAdapter
Adaptador del RecyclerView principal.

Funciones:
- Muestra imagen, nombre y precio.
- Permite añadir productos al carrito.



### CarritoAdapter
Adaptador del RecyclerView del carrito.

Funciones:
- Muestra nombre y precio de los productos añadidos.



## Funcionamiento de la aplicación

1. Se cargan las categorías desde la API.
2. El usuario selecciona una categoría.
3. Se muestran los productos correspondientes.
4. El usuario puede añadir productos al carrito.
5. Desde el menú superior se accede al carrito.
6. En el carrito se puede:
   - Confirmar la compra.
   - Vaciar el carrito.



## API utilizada
Datos obtenidos de: https://dummyjson.com/products


