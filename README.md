<p>
  <img src="https://www.duoc.cl/wp-content/uploads/2022/09/logo-0.png" width="300"/>
</p>

📘 README – Proyecto Semana 4 (PRY2202)
Gestión de Centros de Cultivo con Colecciones

---

📌 Descripción del Proyecto

Este proyecto corresponde a la Experiencia de Aprendizaje 2 – Semana 4 del módulo Desarrollo Orientado a Objetos de Duoc UC.
El objetivo es aplicar conceptos fundamentales de la Programación Orientada a Objetos como:

- Encapsulamiento
- Composición
- Lectura de archivos desde recursos
- Uso de colecciones (ArrayList)
- Separación por paquetes
- Estructura limpia y mantenible
- Manejo básico de excepciones
- validaciones 

La aplicación carga información de centros de cultivo desde un archivo ubicado en la carpeta resources, crea los objetos
correspondientes y los organiza en una colección para posteriormente mostrarlos en formato tabular y aplicar filtros.

---



📁 Estructura del Proyecto
```
📦 src
│
├── 📁 model
│     ├── 📄 CentroCultivo.java
│     ├── 📄 Producto.java
│     └── 📄 Tour.java
│
├── 📁 data
│     └── 📄 GestorDatos.java
│
└── 📁 ui
      └── 📄 Main.java
│
📁 resources
│     └── 📄 datosCentros.txt
│
📄 README.md

```


🏗 Descripción de las Clases

📄 model.CentroCultivo
Representa cada centro de cultivo.

* Incluye:
    - nombreCentro
    - comuna
    - produccion
    - producto (composición obligatoria)
  * Cuenta con:
    - Validaciones
    - Constructores completos
    - Getters/setters documentados
    - toString
    - Documentación Javadoc 

📄 model.Producto
Describe el producto asociado a cada centro.

* Incluye:
    - nombreProducto
    - tipoProducto
    - precioProducto
    - validaciones
    - documentación Javadoc.

📄 data.GestorDatos

Clase encargada de:

Leer el archivo datosCentros.txt

Validar formato (6 columnas)

Crear objetos Producto y CentroCultivo

Almacenarlos en un ArrayList

Manejo de excepciones claras

Evitar NPE mediante validaciones

📄 ui.Main

Punto de ejecución del sistema.

Incluye:

Manejo de excepciones en carga de datos

Validación contra null y listas vacías

Impresión en formato tabla, limpia y ordenada

Filtro usando streams

Mensajes profesionales y claros
