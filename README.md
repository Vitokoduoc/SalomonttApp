<p>
  <img src="https://www.duoc.cl/wp-content/uploads/2022/09/logo-0.png" width="300"/>
</p>

# SalmonttApp – EFT
## Interfaces, colecciones avanzadas, archivos de texto y GUI completa

---

## 📌 Descripción general

En la **Semana 9 (EFT)** se consolida y amplía el sistema desarrollado en las semanas anteriores (herencia, polimorfismo, interfaces y GUI).  
En esta etapa se incorporan:

- Interfaz común **`Registrable`** con dos operaciones:
    - `registrar()` (acción lógica/simbólica por defecto).
    - `mostrarDatos()` (salida formateada en tabla).
- Interfaz **`MetodoPago`** y sus implementaciones:
    - `Efectivo`, `Tarjeta`, `Transferencia`.
- Entidades de negocio adicionales:
    - `Persona` (abstracta), `Rut`, `Direccion`.
    - `Cliente`, `Colaborador`, `Proveedor`.
    - `Producto`, `OrdenDeCompra`.
- Uso combinado de **`ArrayList`** y **`HashMap`**:
    - Colección polimórfica `List<Registrable>` para todas las entidades.
    - Mapas de apoyo (por ejemplo, para buscar clientes por id).
- Lectura de datos desde un **archivo de texto plano** `datos.txt`.
- Uso de una clase utilitaria `LectorArchivo` para convertir líneas de texto en objetos.
- Ordenamiento de unidades operativas implementando **`Comparable<UnidadOperativa>`**.
- GUI completa con:
    - **Ventana de inicio** (bienvenida + botones para cargar datos y abrir la gestión).
    - Ventana principal con pestañas para **Colaboradores**, **Clientes**, **Proveedores**, **Productos**, **Compras** y **Resumen**.

Con esto se demuestra el uso integrado de:

- Herencia simple y jerarquías de clases.
- Interfaces y polimorfismo.
- Colecciones genéricas (`List`, `ArrayList`, `HashMap`).
- Lectura de archivos.
- Programación orientada a eventos con Swing.

---

## 🎯 Objetivos específicos de la evaluación

1. Definir interfaces reutilizables:
    - `Registrable` con `registrar()` y `mostrarDatos()`.
    - `MetodoPago` para encapsular distintos tipos de pago.
2. Hacer que múltiples clases implementen estas interfaces:
    - `UnidadOperativa`, `CentroCultivo`, `PlantaProceso`.
    - `Colaborador`, `Cliente`, `Proveedor`.
    - `Producto`, `OrdenDeCompra`.
3. Gestionar las entidades en una **colección polimórfica**:
    - `List<Registrable> entidades` dentro de `GestorEntidades`.
4. Utilizar estructuras de datos adicionales:
    - `HashMap<String, Cliente>` para búsquedas eficientes por id.
5. Implementar **ordenamiento automático** de unidades operativas:
    - `UnidadOperativa implements Comparable<UnidadOperativa>`.
    - Uso de `Collections.sort(...)` en el gestor.
6. Leer datos desde un **archivo .txt** y poblar las colecciones del sistema.
7. Implementar una **GUI completa** que permita:
    - Cargar los datos desde `datos.txt`.
    - Agregar nuevas entidades desde formularios simples.
    - Ver resúmenes en pantalla (tablas de texto monoespaciado).
    - Imprimir salidas formateadas en consola, incluyendo el uso de `instanceof`.

---

## 📦 Clases e interfaces principales del modelo (`model/`)

### 1. `model.interfaces.Registrable` (interfaz)

Define el comportamiento común para todas las entidades gestionables:

```java
public interface Registrable {

    default void registrar() {
        System.out.println("Entidad registrada correctamente.");
    }

    void mostrarDatos();
}
```

Cualquier clase que implemente `Registrable` puede:

- Ser almacenada en la colección `List<Registrable>`.
- Ser recorrida de forma polimórfica, llamando a `registrar()` y `mostrarDatos()` sin conocer su tipo concreto.

---

### 2. `model.interfaces.MetodoPago` (interfaz)

Abstrae la idea de un método de pago:

```java
public interface MetodoPago {
    String procesarPago(double monto);
}
```

Implementaciones:

- `model.pagos.Efectivo`
- `model.pagos.Tarjeta`
- `model.pagos.Transferencia`

Cada una entrega su propia implementación de `procesarPago(double monto)` y un `toString()` descriptivo.

---

### 3. `model.unidades.UnidadOperativa`

Superclase que representa cualquier unidad operativa de la empresa (centros de cultivo o plantas de proceso).

**Implementa:**

- `Registrable`
- `Comparable<UnidadOperativa>`

**Atributos:**

- `String nombre`
- `String comuna`

**Métodos principales:**

- Constructor con validación:
    - `UnidadOperativa(String nombre, String comuna)`
- Getters:
    - `getNombre()`, `getComuna()`
- `mostrarInformacion()` → imprime información básica.
- `mostrarDatos()` → salida formateada en tabla (usada en los listados por consola).
- `compareTo(UnidadOperativa otra)` → ordena alfabéticamente por `nombre` (requisito del profesor).
- `toString()` → representación base, reutilizada por las subclases.

---

### 4. `model.unidades.CentroCultivo`

Subclase de `UnidadOperativa` que representa un centro de cultivo.

**Atributo adicional:**

- `int toneladasProduccion`

**Métodos destacados:**

- Constructor:
    - Valida que las toneladas sean mayores a cero.
- Getters/Setters (`getToneladasProduccion()`).
- `mostrarInformacion()` y `mostrarDatos()` → imprimen nombre, comuna y producción en toneladas.
- `toString()` → fila formateada (nombre, comuna, producción).

---

### 5. `model.unidades.PlantaProceso`

Subclase de `UnidadOperativa` que representa una planta de proceso.

**Atributo adicional:**

- `int capacidadProceso` (t/día)

**Métodos destacados:**

- Constructor con validación de capacidad.
- `getCapacidadProceso()` y `setCapacidadProceso(...)`.
- `mostrarInformacion()` / `mostrarDatos()`.
- `toString()` → fila formateada (nombre, comuna, capacidad).

---

### 6. `model.persona.Persona` (abstracta)

Clase base para representar personas del sistema.

**Atributos:**

- `String nombre`
- `Rut rut`
- `String email`
- `Direccion direccion`

Se declara como **abstracta** porque no se instancian “personas genéricas”, sino subtipos concretos como `Cliente` o `Colaborador`.

---

### 7. `model.persona.Rut` y `model.persona.Direccion`

Objetos de valor:

- `Rut`:
    - Encapsula el RUT chileno como `String`.
    - Aplica normalización básica y validación de no vacío.
- `Direccion`:
    - `calle`, `numero`, `comuna`, `region`.
    - Incluye validaciones mínimas (ej. comuna no vacía).
    - `toString()` devuelve una representación legible.

---

### 8. `model.persona.Colaborador`

Representa a un colaborador interno.

**Extiende:**

- `Persona`

**Implementa:**

- `Registrable`

**Atributos adicionales:**

- `UnidadOperativa unidadOperativa`
- `String area`
- `String cargo`

**Métodos:**

- Constructor completo con validaciones y llamada a `super(...)`.
- Getters específicos.
- `mostrarDatos()` → imprime una fila formateada con nombre, RUT, cargo, área y unidad.
- `toString()` → también en formato tabular para reutilizar en listados.

---

### 9. `model.persona.Cliente`

Representa un cliente de la empresa.

**Extiende:**

- `Persona`

**Implementa:**

- `Registrable`

**Atributos adicionales:**

- `String idCliente`
- `MetodoPago metodoPago`

**Métodos:**

- Constructor completo con `idCliente`, `Rut`, `Direccion` y `MetodoPago`.
- `getIdCliente()` y validaciones asociadas.
- `mostrarDatos()` → imprime ID, nombre, RUT, email y tipo de método de pago.
- `toString()` → usado en listados y depuración.

---

### 10. `model.persona.Proveedor`

Implementa `Registrable` y representa una empresa proveedora.

**Atributos:**

- `nombreEmpresa`
- `rubro`
- `contactoPrincipal`
- `telefonoContacto`

**Métodos:**

- Constructor completo + validaciones.
- Getters.
- `mostrarDatos()` y `toString()` con formato tabular.

---

### 11. `model.ordenes.Producto`

Entidad de producto asociada a una unidad operativa.

**Implementa:**

- `Registrable`

**Atributos:**

- `String nombre`
- `String comuna`
- `String tipoProducto`
- `double cantidad`
- `UnidadOperativa unidadOrigen`

**Métodos:**

- Constructor completo con validaciones.
- Getters específicos.
- `mostrarDatos()` / `toString()` → fila con nombre, comuna, tipo, cantidad y unidad de origen.

---

### 12. `model.ordenes.OrdenDeCompra`

Representa una compra simple.

**Implementa:**

- `Registrable`

**Atributos típicos:**

- `String idOrden`
- `Cliente cliente`
- `Producto producto`
- `int cantidad`
- `MetodoPago metodoPago`
- `LocalDate fecha`

**Responsabilidades:**

- Calcular el total simbólico de la compra (por ejemplo, `cantidad` como unidades).
- `mostrarDatos()` → muestra la orden en una sola línea formateada (ID, cliente, producto, fecha y medio de pago).

---

### 13. Implementaciones de `MetodoPago`

Todas se ubican en `model.pagos/` y **implementan `MetodoPago`**:

- `Efectivo`
- `Tarjeta`
- `Transferencia`

Cada clase implementa:

```java
@Override
public String procesarPago(double monto) {
    return "Pago ... por $" + monto + " procesado correctamente.";
}
```

y redefine `toString()` para que la GUI y los listados muestren un texto claro (`"Efectivo"`, `"Tarjeta"`, `"Transferencia"`).

---

## 🗂 Capa de datos (`data/`) y utilidades (`util/`)

### `util.LectorArchivo`

Clase utilitaria para leer archivos `.txt`:

- Método estático:
  ```java
  public static List<String> leerArchivo(String ruta)
  ```
- Usa `BufferedReader` y `FileReader`.
- Ignora líneas en blanco y maneja excepciones mostrando un mensaje de error legible.

### `data.GestorEntidades`

Es el **gestor central** del sistema.

**Estructuras principales:**

```java
private List<Registrable> entidades;
private Map<String, Cliente> indiceClientes; // ejemplo de uso de HashMap
```

**Responsabilidades:**

1. **Cargar datos desde `datos.txt`:**
    - Interpreta cada línea según un prefijo o tipo (por ejemplo, `UNIDAD;...`, `CLIENTE;...`, etc.).
    - Construye objetos (`CentroCultivo`, `PlantaProceso`, `Cliente`, `Colaborador`, `Proveedor`, `Producto`, `OrdenDeCompra`).
    - Población de la lista `entidades` y del índice de clientes.

2. **Agregar entidades en tiempo de ejecución:**
    - Método `agregarEntidad(Registrable r)` que añade a la lista y, si corresponde, actualiza el `HashMap`.

3. **Ordenar las unidades operativas:**
    - Cuando corresponde, usa `Collections.sort(...)` sobre una lista de `UnidadOperativa`, aprovechando `Comparable`.

4. **Mostrar detalles usando `instanceof`:**
    - Método `mostrarDetallesConInstanceof()` recorre `entidades` e imprime encabezados distintos para:
        - `CentroCultivo`, `PlantaProceso`, `Colaborador`, `Cliente`, `Proveedor`, `Producto`, `OrdenDeCompra`.

---

## 📁 Archivo de datos `datos.txt`

Ubicado en:

```text
src/data/datos.txt
```

Contiene líneas de ejemplo que representan entidades del sistema.  
Al iniciar la aplicación, el botón **“Cargar datos desde archivo”** de la ventana de inicio invoca `GestorEntidades.cargarDesdeArchivo()` para poblar todas las colecciones a partir de este archivo.

> Nota: la ruta usada en el código es relativa al proyecto.  
> Asegurarse de que el archivo exista en `src/data/datos.txt` para que la lectura sea exitosa.

---

## 🖥 Interfaz gráfica (`ui/Main.java`)

La GUI está compuesta por **dos niveles de ventanas**:

### 1. Ventana de inicio (bienvenida)

Se muestra primero e incluye:

- Título y mensaje de bienvenida.
- Botón **“Cargar datos desde archivo”**:
    - Invoca el método del gestor que lee `datos.txt` y carga las entidades.
- Botón **“Agregar datos manualmente”**:
    - Abre la ventana principal con pestañas para trabajar con la información.

Esta ventana permite al docente probar rápidamente el sistema cargando los datos de ejemplo y, luego, seguir agregando entidades desde la GUI.

### 2. Ventana principal con pestañas

Extiende `JFrame` y contiene un `JTabbedPane` con las siguientes pestañas:

1. **Colaboradores**
2. **Clientes**
3. **Proveedores**
4. **Productos**
5. **Compras**
6. **Resumen**

Cada pestaña ofrece botones para:

- **Agregar** nuevas entidades (usando `JOptionPane` para pedir los datos).
- **Ver** un resumen en formato tabla textual, presentada en un `JTextArea` monoespaciado dentro de un `JScrollPane`.

La pestaña **Resumen** incluye además botones para:

- Ver todas las entidades en la GUI.
- Imprimir en consola un informe completo en **formato tabla**, usando `mostrarDatos()` de cada clase.
- Mostrar un detalle en consola utilizando `instanceof`, para evidenciar el polimorfismo.

---

## ▶️ Instrucciones para ejecutar SalmonttApp

1. Abrir el proyecto en **IntelliJ IDEA**.
2. Verificar la estructura de paquetes principal:

```text
src/
├── app/                     (opcional, punto de entrada alternativo)
├── data/
│   ├── GestorEntidades.java
│   ├── GestorUnidades.java  (heredado de semanas anteriores)
│   └── datos.txt
├── model/
│   ├── interfaces/
│   │   ├── MetodoPago.java
│   │   └── Registrable.java
│   ├── ordenes/
│   │   ├── OrdenDeCompra.java
│   │   └── Producto.java
│   ├── pagos/
│   │   ├── Efectivo.java
│   │   ├── Tarjeta.java
│   │   └── Transferencia.java
│   ├── persona/
│   │   ├── Cliente.java
│   │   ├── Colaborador.java
│   │   ├── Direccion.java
│   │   ├── Persona.java
│   │   ├── Proveedor.java
│   │   └── Rut.java
│   └── unidades/
│       ├── CentroCultivo.java
│       ├── PlantaProceso.java
│       └── UnidadOperativa.java
├── ui/
│   └── Main.java
└── util/
    └── LectorArchivo.java

README.md
```

3. Confirmar que `src/data/datos.txt` existe y contiene los datos de prueba.
4. Compilar el proyecto (el IDE lo hace automáticamente al ejecutar).
5. Ejecutar la clase:

```text
ui.Main
```

6. En la **ventana de inicio**:
    - Pulsar **“Cargar datos desde archivo”** para poblar el sistema con el contenido de `datos.txt`.
    - Luego pulsar **“Abrir gestión de entidades”** para trabajar en la ventana con pestañas.
7. Desde la ventana principal se pueden:
    - Agregar colaboradores, clientes, proveedores y productos.
    - Visualizar resúmenes filtrados por tipo.
    - Ver el listado completo en la GUI.
    - Imprimir tablas en consola y mostrar el recorrido polimórfico usando `instanceof`.

---

## 📊 Diagrama UML

```text                                
                «interface» Registrable                     «interface» MetodoPago
             ┌──────────────────────────┐               ┌──────────────────────────┐
             │ + registrar(): void      │               │ + procesarPago(m:double) │
             │ + mostrarDatos(): void   │               └──────────────▲───────────┘
             └──────────────▲───────────┘                              │
                            │                                          │
          ┌─────────────────┼───────────────────────────────┐    ┌─────┴───────────────┐
          │                 │                               │    │                     │
  UnidadOperativa     Persona «abstract»               Proveedor           Efectivo   Tarjeta   Transferencia
(implements Registrable,   (implements Registrable)  (implements Reg.)  (implements MetodoPago)
 Comparable<UnidadOperativa>)

  ┌──────────────────────────────┐         ┌──────────────────────────────┐
  │ - nombre: String             │         │ - nombre: String             │
  │ - comuna: String             │         │ - rut: Rut                   │
  │ + mostrarInformacion()       │         │ - email: String              │
  │ + mostrarDatos()             │         │ - direccion: Direccion       │
  │ + compareTo(UnidadOperativa) │         │ + mostrarDatos()             │
  └───────────────▲──────────────┘         └───────────────▲──────────────┘
                  │                                      │
        ┌─────────┴───────────┐                 ┌────────┴───────────────┐
        │                     │                 │                        │
 CentroCultivo          PlantaProceso       Cliente                 Colaborador
(extends UnidadOperativa) (extends UnidadOperativa) (extends Persona)   (extends Persona)
(implements Registrable)    (implements Registrable) (implements Reg.)   (implements Reg.)

 ┌──────────────────────────────┐   ┌──────────────────────────────┐
 │ - toneladasProduccion: int   │   │ - capacidadProceso: int      │
 │ + mostrarInformacion()       │   │ + mostrarInformacion()       │
 │ + mostrarDatos()             │   │ + mostrarDatos()             │
 └──────────────────────────────┘   └──────────────────────────────┘

 Cliente
 ┌────────────────────────────────────┐
 │ - idCliente: String                │
 │ - metodoPago: MetodoPago           │◄─────────────┐ asociación
 │ + mostrarDatos()                   │              │
 └────────────────────────────────────┘              │
                                                    │
 Colaborador                                         │
 ┌────────────────────────────────────┐             │
 │ - unidadOperativa: UnidadOperativa │─────────────┘ asociación
 │ - area: String                     │
 │ - cargo: String                    │
 │ + mostrarDatos()                   │
 └────────────────────────────────────┘

 Proveedor
 ┌────────────────────────────────────┐
 │ - nombreEmpresa: String            │
 │ - rubro: String                    │
 │ - contactoPrincipal: String        │
 │ - telefonoContacto: String         │
 │ + mostrarDatos()                   │
 └────────────────────────────────────┘


 Producto (implements Registrable)
 ┌────────────────────────────────────┐
 │ - nombre: String                   │
 │ - comuna: String                   │
 │ - tipoProducto: String             │
 │ - cantidad: double                 │
 │ - unidadOrigen: UnidadOperativa ───┘ asociación
 │ + mostrarDatos()                   │
 └────────────────────────────────────┘


 OrdenDeCompra (implements Registrable)
 ┌─────────────────────────────────────────────┐
 │ - idOrden: String                          │
 │ - cliente: Cliente ────────────────┐       │ asociación
 │ - producto: Producto ──────────────┼───────┘ asociación
 │ - cantidad: int                    │
 │ - metodoPago: MetodoPago ──────────┘ asociación
 │ - fecha: LocalDate                 │
 │ + mostrarDatos()                   │
 └────────────────────────────────────┘


 Rut (Value Object)               Direccion (Value Object)
 ┌─────────────────────────┐      ┌────────────────────────────┐
 │ - rut: String           │      │ - calle: String            │
 │ + toString()            │      │ - numero: String           │
 └─────────────────────────┘      │ - comuna: String           │
                                  │ - region: String           │
                                  │ + toString()               │
                                  └────────────────────────────┘


 GestorEntidades
 ┌──────────────────────────────────────────────────────┐
 │ - entidades: List<Registrable>                       │
 │ + cargarDesdeArchivo(ruta: String): void             │
 │ + agregarEntidad(e: Registrable): void               │
 │        └─ llama e.registrar() (uso de interface)     │
 │ + getEntidades(): List<Registrable>                  │
 │ + getUnidadesOrdenadas(): List<UnidadOperativa>      │
 │        └─ usa Collections.sort(List<UnidadOperativa>)│ 
 │           (orden natural por nombre, compareTo)      │
 │ + mostrarDetallesConInstanceof(): void               │
 └──────────────────────────────────────────────────────┘


 Main (UI Swing)
 ┌────────────────────────────────────────────────────┐
 │ - gestor: GestorEntidades                          │
 │ - cardLayout: CardLayout                           │
 │ - panelPrincipal: JPanel                           │
 │ + main(String[])                                   │
 │ + crearPanelInicio()                               │ 
 │ + crearPanelPestanas()                             │
 │ + agregarColaborador(), agregarCliente(), ...      │
 │ + mostrarResumenGeneralGUI()                       │
 │ + mostrarUnidadesOrdenadasGUI()   (usa gestor.     │
 │                            getUnidadesOrdenadas()) │
 │ + imprimirConsolaEnFormatoTabla() (usa mostrar...  │
 └────────────────────────────────────────────────────┘

GestorEntidades → usa LectorArchivo para leer data/datos.txt  
Main → usa GestorEntidades para cargar datos y mostrar las colecciones.

```
---

## 👨‍💻 Autor

**Víctor Valenzuela Concha**  
Estudiante – Analista Programador Computacional  
Duoc UC – Desarrollo Orientado a Objetos I
