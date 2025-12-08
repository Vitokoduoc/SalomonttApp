<p>
  <img src="https://www.duoc.cl/wp-content/uploads/2022/09/logo-0.png" width="300"/>
</p>

# SalmonttApp – Semana 7
## Herencia simple, polimorfismo y colecciones genéricas

---

## 📌 Descripción de esta semana

El objetivo de esta semana es consolidar la jerarquía de clases utilizando **herencia simple y polimorfismo**, donde:

- Se define una **superclase** (`UnidadOperativa`) con atributos y comportamiento común.
- Se crean **subclases** (`CentroCultivo` y `PlantaProceso`) que extienden su funcionalidad.
- Se **sobrescribe** el método `mostrarInformacion()` en cada subclase.
- Se utiliza una **colección genérica** `List<UnidadOperativa>` para almacenar distintos tipos de unidades.
- Se recorre la colección desde la clase `Main` usando **referencias del tipo `UnidadOperativa`** e invocando `mostrarInformacion()` de forma polimórfica.

Esta estructura permite escalar el sistema de unidades operativas de la empresa Salmontt y demostrar el uso práctico del polimorfismo.

---

## 📦 Clases creadas

### **1. model/UnidadOperativa.java**

Superclase con los atributos:

- `nombre`
- `comuna`

Métodos principales:

- Constructor con parámetros (`nombre`, `comuna`).
- `getNombre()` y `getComuna()`.
- `public void mostrarInformacion()`  
  Método base que puede ser sobrescrito por las subclases para mostrar información específica.

---

### **2. model/CentroCultivo.java**

Subclase que extiende `UnidadOperativa` y agrega:

- `toneladasProduccion` (producción anual en toneladas).

Características:

- Usa `super(...)` en el constructor para inicializar los atributos heredados.
- Sobrescribe el método:
    - `public void mostrarInformacion()`  
      Muestra nombre, comuna y producción en toneladas.
- Mantiene un `toString()` formateado para representación tabular si se requiere.

---

### **3. model/PlantaProceso.java**

Subclase que extiende `UnidadOperativa` y agrega:

- `capacidadProceso` (t por día).

Características:

- Usa `super(...)` en el constructor para inicializar los atributos heredados.
- Sobrescribe el método:
    - `public void mostrarInformacion()`  
      Muestra nombre, comuna y capacidad diaria en toneladas.
- Mantiene un `toString()` formateado para representación tabular si se requiere.

---

### **4. data/GestorUnidades.java**

Clase encargada de generar datos de prueba.

Responsabilidades:

- Implementa el método:
    - `public List<UnidadOperativa> crearUnidades()`
- Crea una **lista polimórfica** `List<UnidadOperativa>` que contiene:
    - Al menos **tres** instancias de `CentroCultivo`.
    - Al menos **dos** instancias de `PlantaProceso`.
- Retorna la lista completa de unidades operativas para ser utilizada desde `Main`.

---

### **5. ui/Main.java**

Clase principal que ejecuta el programa.

Responsabilidades:

- Crea una instancia de `GestorUnidades`.
- Obtiene la lista de unidades operativas:
- Recorre la colección de manera polimórfica:
  ```
  java
  List<UnidadOperativa> unidades = gestor.crearUnidades();
  for (UnidadOperativa unidad : unidades) {
    unidad.mostrarInformacion();
    }
    ```
- La salida mostrada en consola depende del tipo concreto de cada objeto (CentroCultivo o PlantaProceso), gracias a la sobrescritura de mostrarInformacion().
---
## ▶️ Instrucciones para ejecutar Main 
1. Abrir el proyecto en IntelliJ IDEA (o IDE equivalente). 
2. Verificar la estructura de paquetes:
```
src/
├── model/
├── data/
└── ui/
```
3. Compilar el proyecto (el IDE lo hace automáticamente al ejecutar).
4. Ejecutar la clase:
```
ui.Main
```
5. La consola mostrará la información de las unidades operativas usando el método mostrarInformacion() de cada subclase.

---
## 🖥 Salida del Programa (Ejemplo)

Nota: El contenido exacto depende de los datos definidos en GestorUnidades, pero el formato es similar al siguiente:

```
============= Unidades Operativas ==============
Centro de Cultivo: Calbuco Norte, Comuna: Calbuco, Producción: 1200 toneladas
Centro de Cultivo: Isla Huar, Comuna: Calbuco, Producción: 1100 toneladas
Centro de Cultivo: Chacao Norte, Comuna: Ancud, Producción: 980 toneladas
Planta de Proceso: Planta Ancud, Comuna: Ancud, Capacidad: 500 t por día
Planta de Proceso: Planta Quellón, Comuna: Quellón, Capacidad: 850 t por día
=================================================
```
Esta salida demuestra:
- El uso de polimorfismo (todas las referencias son UnidadOperativa).
- La sobrescritura de mostrarInformacion() en cada subclase.

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
                      │ + UnidadOperativa(...)   │
                      │ + getNombre() : String   │
                      │ + getComuna() : String   │
                      │ + mostrarInformacion()   │
                      └───────────▲──────────────┘
                                  │
              ┌───────────────────┴────────────────┐
              │                                    │
┌───────────────────────────┐         ┌──────────────────────────┐
│       CentroCultivo       │         │      PlantaProceso       │
├───────────────────────────┤         ├──────────────────────────┤
│ - toneladasProduccion:int │         │ - capacidadProceso:int   │
├───────────────────────────┤         ├──────────────────────────┤
│ + CentroCultivo(...)      │         │ + PlantaProceso(...)     │
│ + getToneladasProduccion():int      │ + getCapacidad():int     │
│ + mostrarInformacion()    │         │ + mostrarInformacion()   │
│ + toString() : String     │         │ + toString() : String    │
└───────────────────────────┘         └──────────────────────────┘
```
---
##👨‍💻 Autor

Víctor Valenzuela Concha  
Estudiante – Analista Programador Computacional  
Duoc UC – Desarrollo Orientado a Objetos I
