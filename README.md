<p>
  <img src="https://www.duoc.cl/wp-content/uploads/2022/09/logo-0.png" width="300"/>
</p>

 # 📘 Proyecto Semana 4 SalmonttApp
Gestión de Centros de Cultivo con Colecciones

---

## 📌 Descripción del Proyecto

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



## 📁 Estructura del Proyecto
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


## 🏗 Descripción de las Clases

📄 model.CentroCultivo  
Representa un centro de producción acuícola.  
Atributos:
* nombreCentro
* comuna
* produccion
* producto asociado (composición)
Incluye:
* Validacion de datos
* Constructores seguros
* Getters/setters
* Representación en texto mediante toString(
* documentados Javadoc
  
***

📄 model.Producto  
Representa el producto generado por un centro.  
Atributos:
* nombreProducto
* tipoProducto
* precioProducto
Incluye:
* Validación de datos
* Documentación Javadoc
* Constructores
* Representación en texto mediante toString(
  
***

📄 model.Tour  
Clase independiente que representa un recorrido o actividad planificada.  
Atributos principales:
* nomRuta: Nombre de la ruta o sector del tour
* responsable: Persona encargada
* fecha: Fecha del tour
Características:
* Constructor vacío y constructor con parámetros
* Getters y setters con validaciones mínimas
* Manejo coherente de posibles datos inválidos
* Documentación Javadoc
* Representación en texto mediante toString()
  
***

📄 data.GestorDatos  
Clase encargada de:
* Leer el archivo datosCentros.txt
* Validar estructura (6 columnas por línea)
* Crear objetos Producto y CentroCultivo
* Manejar excepciones en la carga de datos
* Devolver una lista con los registros válidos

***

📄 ui.Main – Punto de Entrada  
Incluye:
* Manejo de excepciones al cargar datos
* Validación contra valores nulos
* Impresión profesional en formato tabla
* Filtro con streams
* Separación de responsabilidades en métodos auxiliares
* Mensajes claros y estilo profesional

***

## 📄 Formato del Archivo datosCentros.txt  
El archivo en /resources debe contener 6 columnas:

```
nombreCentro;comuna;produccion;nombreProducto;tipoProducto;precioProducto

```
Ejemplo:

```
Isla Huar;Calbuco;1200;Salmón Atlántico Premium;Ahumado;8500 
Chacao Norte;Ancud;980;Trucha Arcoíris;Fresco;6900
```


***
## 🖥 Salida del Programa (Formato Tabla)

```
====================================================================================================
                                   LISTA COMPLETA DE CENTROS DE CULTIVO
====================================================================================================
CENTRO                | COMUNA        | PRODUCCIÓN | PRODUCTO                    | TIPO       | PRECIO
----------------------------------------------------------------------------------------------------
Isla Huar             | Calbuco       | 1200       | Salmón Atlántico Premium    | Ahumado    | 8500.0
Chacao Norte          | Ancud         | 980        | Trucha Arcoíris             | Fresco     | 6900.0
...

```
---
## 📊 Diagrama UML

classDiagram
    direction LR

    class Producto {
        - String nombreProducto
        - String tipoProducto
        - double precioProducto
        + Producto()
        + Producto(nombreProducto, tipoProducto, precioProducto)
        + getNombreProducto() String
        + setNombreProducto(nombreProducto String) void
        + getTipoProducto() String
        + setTipoProducto(tipoProducto String) void
        + getPrecioProducto() double
        + setPrecioProducto(precioProducto double) void
    }

    class CentroCultivo {
        - String nombreCentro
        - String comuna
        - int produccion
        - Producto producto
        + CentroCultivo()
        + CentroCultivo(nombreCentro String, comuna String, produccion int, producto Producto)
        + getNombreCentro() String
        + setNombreCentro(nombreCentro String) void
        + getComuna() String
        + setComuna(comuna String) void
        + getProduccion() int
        + setProduccion(produccion int) void
        + getProducto() Producto
        + setProducto(producto Producto) void
    }

    class Tour {
        - String nomRuta
        - String responsable
        - String fecha
        + Tour()
        + Tour(nomRuta String, responsable String, fecha String)
        + getNomRuta() String
        + setNomRuta(nomRuta String) void
        + getResponsable() String
        + setResponsable(responsable String) void
        + getFecha() String
        + setFecha(fecha String) void
    }

    class GestorDatos {
        + cargarDatos(rutaArchivo String) List~CentroCultivo~
    }

    class Main {
        + main(args String[]) void
    }

    %% Relaciones
    CentroCultivo *-- Producto : composición
    GestorDatos --> CentroCultivo : crea
    GestorDatos --> Producto : crea
    Main --> GestorDatos : usa
    Main --> CentroCultivo : procesa
    %% Tour queda independiente por ahora


## 👨‍💻 Autor

Víctor Valenzuela Concha  
Estudiante – analista Programador Computacional.  
Duoc UC – DOO


