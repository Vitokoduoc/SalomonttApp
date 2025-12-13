<p>
  <img src="https://www.duoc.cl/wp-content/uploads/2022/09/logo-0.png" width="300"/>
</p>

# SalmonttApp – Semana 8
## Interfaces, polimorfismo avanzado y GUI

---

## 📌 Descripción general

En la **Semana 8** se extiende el sistema desarrollado en la Semana 7, incorporando:

- Una **interfaz** común: `Registrable`.
- Nuevas entidades del dominio:
    - `Persona` (base).
    - `Colaborador`.
    - `Proveedor`.
    - `Producto`.
- Uso de **colecciones genéricas** basadas en la interfaz:
    - `ArrayList<Registrable>`.
- Diferenciación de comportamientos utilizando **`instanceof`**.
- Una **interfaz gráfica (GUI)** con ventana y pestañas (`JTabbedPane`) para gestionar:
    - Colaboradores.
    - Proveedores.
    - Productos.
- Un gestor central de entidades: `GestorEntidades`.

Este trabajo consolida el uso de:

- Herencia simple.
- Interfaces.
- Polimorfismo.
- Colecciones genéricas.
- Programación orientada a eventos con Swing.

---

## 🎯 Objetivos específicos de la Semana 8

1. Definir una **interfaz funcional**:
    - `Registrable` con el método `void mostrarResumen()`.
2. Hacer que múltiples clases implementen esta interfaz:
    - `UnidadOperativa`, `CentroCultivo`, `PlantaProceso`, `Colaborador`, `Proveedor`, `Producto`.
3. Gestionar las entidades en una **única colección polimórfica**:
    - `ArrayList<Registrable> entidades`.
4. Usar **`instanceof`** para:
    - Diferenciar tipos concretos dentro de la misma colección.
    - Mostrar mensajes específicos por tipo.
5. Implementar una **GUI con pestañas** para:
    - Agregar colaboradores, proveedores y productos.
    - Ver resúmenes de entidades en una tabla textual.
    - Disparar una vista detallada en consola usando `instanceof`.

---

## 📦 Clases principales del modelo (`model/`)

### 1. `Registrable.java` (interfaz)

Define el contrato común para las entidades gestionables:

- Método:

```java
void mostrarResumen();
```

Cualquier clase que implemente `Registrable` puede ser agregada a la colección `ArrayList<Registrable>` y ser recorrida de forma polimórfica.

---

### 2. `UnidadOperativa.java`

Superclase que representa cualquier unidad operativa de la empresa (*no abstracta*).

**Atributos:**

- `nombre`
- `comuna`

**Implementa:**

- `Registrable`

**Métodos principales:**

- Constructor con parámetros (`nombre`, `comuna`).
- Getters: `getNombre()`, `getComuna()`.
- `void mostrarInformacion()` → versión base.
- `void mostrarResumen()` → implementación genérica de `Registrable`.
- `toString()` → representación textual simple.

---

### 3. `CentroCultivo.java`

Subclase de `UnidadOperativa` que representa un centro de cultivo.

**Extiende:**

- `UnidadOperativa`

**Atributos adicionales:**

- `toneladasProduccion` (producción anual)

**Métodos destacados:**

- Constructor con `super(...)`.
- `int getToneladasProduccion()`.
- Sobrescritura de:
    - `mostrarInformacion()`
    - `mostrarResumen()`
- `toString()` con formato tabular opcional.

---

### 4. `PlantaProceso.java`

Subclase de `UnidadOperativa` que representa una planta de proceso.

**Extiende:**

- `UnidadOperativa`

**Atributos adicionales:**

- `capacidadProceso` (toneladas por día)

**Métodos destacados:**

- Constructor con `super(...)`.
- `int getCapacidad()`.
- Sobrescritura de:
    - `mostrarInformacion()`
    - `mostrarResumen()`
- `toString()` con formato tabular opcional.

---

### 5. `Persona.java`

Clase base para representar personas dentro del sistema.

**Tipo:**

- `abstract`

**Atributos:**

- `nombre`
- `rut`
- `email`
- `direccion`

**Métodos:**

- Constructor con todos los atributos.
- Getters: `getNombre()`, `getRut()`, `getEmail()`, `getDireccion()`.

---

### 6. `Colaborador.java`

Representa un colaborador interno (trabajador de la empresa).

**Extiende:**

- `Persona`

**Implementa:**

- `Registrable`

**Atributos adicionales:**

- `UnidadOperativa unidadOperativa`
- `String area` (ej: "Alimentación", "Envasado")
- `String cargo` (ej: "Operador", "Supervisor")

**Métodos destacados:**

- Constructor completo, que llama a `super(...)` para inicializar los datos de `Persona`.
- Getters específicos (`getUnidadOperativa()`, `getArea()`, `getCargo()`).
- `mostrarResumen()` → imprime un resumen legible con nombre, cargo, área, unidad y dirección.
- `toString()` → representación detallada para depuración o listados.

---

### 7. `Proveedor.java`

Representa un proveedor externo.

**Implementa:**

- `Registrable`

**Atributos:**

- `nombreEmpresa`
- `rubro`
- `contactoPrincipal`
- `telefonoContacto`

**Métodos:**

- Constructor con todos los atributos.
- Getters específicos.
- `mostrarResumen()` → imprime un resumen de proveedor.
- `toString()` → texto detallado.

---

### 8. `Producto.java`

Representa un producto asociado a una unidad operativa.

**Extiende:**

- `UnidadOperativa`

**Implementa:**

- `Registrable`

**Atributos adicionales:**

- `String tipoProducto`
- `double cantidad`
- `UnidadOperativa unidadOrigen` (centro o planta donde se encuentra)

**Métodos:**

- Constructor con `super(nombre, comuna)` + atributos propios.
- `getTipoProducto()`, `getCantidad()`, `getUnidadOrigen()`.
- Sobrescritura de:
    - `mostrarInformacion()` → detalle completo del producto.
    - `mostrarResumen()` → resumen breve.
- `toString()` → representación textual amigable.

---

## 🗂 Capa de datos (`data/`)

### `GestorEntidades.java`

Gestiona una colección polimórfica de entidades.

**Atributo principal:**

```java
private ArrayList<Registrable> entidades;
```

**Responsabilidades:**

- Inicializar datos de ejemplo en `cargarDatosIniciales()`:
    - Unidades operativas (`CentroCultivo`, `PlantaProceso`).
    - Un `Colaborador`.
    - Un `Proveedor`.
    - Un `Producto`.
- Entregar acceso a la colección:
  ```java
  public ArrayList<Registrable> getEntidades()
  ```
- Mostrar información diferenciada usando `instanceof`:
  ```java
  public void mostrarDetallesConInstanceof()
  ```
  Recorre la lista y para cada tipo (`CentroCultivo`, `PlantaProceso`, `Colaborador`, `Proveedor`, `Producto`) muestra un encabezado distinto y luego llama a `mostrarResumen()`.

---

## 🖥 Capa de interfaz gráfica (`ui/`)

### `Main.java` (ventana principal con pestañas)

La clase `ui.Main` ahora extiende `JFrame` y se comporta como la ventana principal de la aplicación.

**Características:**

- Usa `SwingUtilities.invokeLater` para lanzar la GUI:
  ```java
  public static void main(String[] args) {
      SwingUtilities.invokeLater(() -> {
          Main ventana = new Main();
          ventana.setVisible(true);
      });
  }
  ```
- Configura una ventana con:
    - Título: `"SalmonttApp - Semana 8"`.
    - `JTabbedPane` con pestañas:
        - **Colaboradores**
        - **Proveedores**
        - **Productos**
        - **Resumen / Consola**

**En cada pestaña:**

- Botones para **agregar** nuevas entidades mediante `JOptionPane`:
    - `Agregar Colaborador`
    - `Agregar Proveedor`
    - `Agregar Producto`
- Botones para **ver resúmenes**:
    - Muestran una “tabla” textual en un `JTextArea` con fuente monoespaciada dentro de un `JScrollPane`.

**Pestaña “Resumen / Consola”:**

- Botón para ver todas las entidades en una tabla GUI.
- Botón:
  ```java
  "Mostrar detalle en consola (instanceof)"
  ```
  que llama a:
  ```java
  gestor.mostrarDetallesConInstanceof();
  ```
  y muestra el detalle por tipo en la consola, reutilizando la lógica de Week 7.

---

## ▶️ Instrucciones para ejecutar la aplicación

1. Abrir el proyecto en **IntelliJ IDEA** (o IDE equivalente).
2. Verificar la estructura de paquetes:

```text
src/
├── model/
│   ├── UnidadOperativa.java
│   ├── CentroCultivo.java
│   ├── PlantaProceso.java
│   ├── Registrable.java
│   ├── Persona.java
│   ├── Colaborador.java
│   ├── Proveedor.java
│   └── Producto.java
│
├── data/
│   ├── GestorEntidades.java
│   └── GestorUnidades.java
│
└── ui/
    └── Main.java

README.md
```

3. Asegurarse de que el proyecto compila sin errores.
4. Ejecutar la clase:
```text
ui.Main
```
5. Se abrirá una **ventana con pestañas**:
    - Desde allí se pueden:
        - Agregar entidades.
        - Ver resúmenes en GUI.
        - Enviar detalle a la **consola** usando `instanceof`.

---

## 🖨 Ejemplo de salida en consola (detalle con `instanceof`)

Ejemplo de salida obtenida desde `GestorEntidades.mostrarDetallesConInstanceof()`:

```text
=== Detalle de entidades ===
[Centro de Cultivo detectado]
[Resumen Centro de Cultivo] Calbuco Norte | Producción: 1200 t
[Centro de Cultivo detectado]
[Resumen Centro de Cultivo] Isla Huar | Producción: 1100 t
[Planta de Proceso detectada]
[Resumen Planta] Planta Ancud | Capacidad: 500 t/día
[Colaborador detectado]
Colaborador: Juan Pérez | Cargo: Operador | Área: Alimentación | Unidad: Calbuco Norte | Dirección: Av. Los Ríos 123
[Proveedor detectado]
Proveedor: Maersk Logistics | Rubro: Transporte Marítimo | Contacto: Carlos Soto | Tel: +56 9 7711 3344
[Producto detectado]
Producto: Salmón Atlántico (Pez vivo), Origen: Isla Huar
```

Esta salida demuestra:

- Uso de la **interfaz Registrable**.
- Uso de **`instanceof`** para diferenciar tipos concretos.
- Polimorfismo sobre una **colección genérica** `ArrayList<Registrable>`.

---

## 📊 Diagrama UML – Semana 8

```text
                      ┌──────────────────────────┐
                      │      «interface»         │
                      │       Registrable        │
                      ├──────────────────────────┤
                      │ + mostrarResumen()       │
                      └───────────▲──────────────┘
                                  │
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
                      │ + mostrarResumen()       │
                      └───────▲─────────▲────────┘
                              │         │
              ┌───────────────┘         └────────────────┐
              │                                          │
┌───────────────────────────┐             ┌──────────────────────────┐
│       CentroCultivo       │             │      PlantaProceso       │
├───────────────────────────┤             ├──────────────────────────┤
│ - toneladasProduccion:int │             │ - capacidadProceso:int   │
├───────────────────────────┤             ├──────────────────────────┤
│ + CentroCultivo(...)      │             │ + PlantaProceso(...)     │
│ + getToneladasProduccion()│             │ + getCapacidad()         │
│ + mostrarInformacion()    │             │ + mostrarInformacion()   │
│ + mostrarResumen()        │             │ + mostrarResumen()       │
└───────────────────────────┘             └──────────────────────────┘

                ┌──────────────────────────┐
                │         Persona          │
                ├──────────────────────────┤
                │ # nombre : String        │
                │ # rut : String           │
                │ # email : String         │
                │ # direccion : String     │
                ├──────────────────────────┤
                │ + Persona(...)           │
                │ + getters...             │
                └───────────▲──────────────┘
                            │
            ┌───────────────┴───────────────────┐
            │                                   │
┌───────────────────────────┐        ┌──────────────────────────┐
│        Colaborador        │        │       Proveedor          │
├───────────────────────────┤        ├──────────────────────────┤
│ - unidadOperativa:        │        │ - nombreEmpresa:String   │
│   UnidadOperativa         │        │ - rubro:String           │
│ - area:String             │        │ - contactoPrincipal:String│
│ - cargo:String            │        │ - telefonoContacto:String│
├───────────────────────────┤        ├──────────────────────────┤
│ + Colaborador(...)        │        │ + Proveedor(...)         │
│ + getUnidadOperativa()    │        │ + getters...             │
│ + getArea()               │        │ + mostrarResumen()       │
│ + getCargo()              │        │ + toString()             │
│ + mostrarResumen()        │        └──────────────────────────┘
│ + toString()              │
└───────────▲───────────────┘
            │
            │ implements Registrable
            │

┌───────────────────────────┐
│          Producto         │
├───────────────────────────┤
│ - tipoProducto:String     │
│ - cantidad:double         │
│ - unidadOrigen:           │
│   UnidadOperativa         │
├───────────────────────────┤
│ + Producto(...)           │
│ + getTipoProducto()       │
│ + getCantidad()           │
│ + getUnidadOrigen()       │
│ + mostrarInformacion()    │
│ + mostrarResumen()        │
│ + toString()              │
└───────────────────────────┘
       ▲
       │  extends UnidadOperativa
       │  implements Registrable
```

---

## 👨‍💻 Autor

**Víctor Valenzuela Concha**  
Estudiante – Analista Programador Computacional  
Duoc UC – Desarrollo Orientado a Objetos I 
