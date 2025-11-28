<p>
  <img src="https://www.duoc.cl/wp-content/uploads/2022/09/logo-0.png" width="300"/>
</p>

# SalmonttApp – Semana 6  
## Creación de jerarquías con herencia simple

---

## 📌 Descripción de esta semana

El objetivo de esta semana es implementar una jerarquía de clases utilizando **herencia simple**, donde:

- Se define una **superclase** (`UnidadOperativa`) con atributos comunes.
- Se crean **subclases** (`CentroCultivo` y `PlantaProceso`) que extienden su funcionalidad.
- Se sobrescriben métodos (`toString()`).
- Se utiliza `super(...)` en los constructores.
- Se muestran instancias creadas desde la clase `Main`.

Esta estructura permitirá escalar el sistema de unidades operativas de la empresa Salmontt.

---
## 📦 Clases creadas

### **1. model/UnidadOperativa.java**
Superclase con los atributos:
- `nombre`
- `comuna`

### **2. model/CentroCultivo.java**
Subclase que agrega:
- `toneladasProduccion`

Sobrescribe `toString()`.

### **3. model/PlantaProceso.java**
Subclase que agrega:
- `capacidadProceso` (t por día)

Sobrescribe `toString()`.

### **4. data/GestorUnidades.java**
Genera instancias de prueba de las subclases:
- 2 Centros de Cultivo  
- 2 Plantas de Proceso  

Devuelve una lista con todas las unidades.

### **5. ui/Main.java**
Ejecuta el programa e imprime por consola las unidades creadas, utilizando los métodos `toString()` de cada subclase.

---

## ▶️ Instrucciones para ejecutar `Main`

1. Abrir el proyecto en IntelliJ IDEA.
2. Verificar la estructura de paquetes:
```
src/
├── model/
├── data/
└── ui/
```
3. Ejecutar la clase:
   ```
   ui.Main
   ```
5.  La consola mostrará una tabla con las unidades operativas creadas.

## 🖥 Salida del Programa (Formato Tabla)

```
================== Unidades Operativas ==================
Nombre             | Comuna     | Producción     |
==========================================================
Isla Huar          | Calbuco    | 1200 t         |
Chacao Norte       | Ancud      | 980 t          |
Planta Ancud       | Ancud      | 500 t por día  |
Planta Quellón     | Quellón    | 850 t por día  |
==========================================================

```

---

## ✔ Cumplimiento solicitado
Este README contiene exactamente lo requerido:
- Descripción del objetivo de la semana  
- Clases creadas  
- Instrucciones para ejecutar Main  

---

## 📂 Estructura del Proyecto – SalmonttApp
```
📦 SalmonttApp  
└── 📁 src  
    ├── 📁 model  
    │     ├── 📄 UnidadOperativa.java  
    │     ├── 📄 CentroCultivo.java  
    │     └── 📄 PlantaProceso.java  
    │
    ├── 📁 data  
    │     └── 📄 GestorUnidades.java  
    │
    └── 📁 ui  
          └── 📄 Main.java  

📄 README.md

```

---
## 📊 Diagrama UML
```
                  ┌──────────────────────────┐
                  │     UnidadOperativa      │
                  ├──────────────────────────┤
                  │ - nombre : String        │
                  │ - comuna : String        │
                  ├──────────────────────────┤
                  │ + UnidadOperativa( )     │
                  │ + getNombre() : String   │
                  │ + getComuna() : String   │
                  └───────────▲──────────────┘
                              │
              ┌───────────────┴────────────────┐
              │                                │
┌──────────────────────────┐     ┌──────────────────────────┐
│      CentroCultivo       │     │      PlantaProceso       │
├──────────────────────────┤     ├──────────────────────────┤
│ - toneladasProduccion: int│     │ - capacidadProceso: int  │
├──────────────────────────┤     ├──────────────────────────┤
│ + CentroCultivo( )       │     │ + PlantaProceso( )        │
│ + getProduccion() : int  │     │ + toString() : String     │
│ + toString() : String    │     └──────────────────────────┘
└──────────────────────────┘
```

## 👨‍💻 Autor

Víctor Valenzuela Concha  
Estudiante – analista Programador Computacional.  
Duoc UC – DOO


