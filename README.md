# Sistema de Registro y Consulta de Solicitudes de Clientes

Aplicación de escritorio en JavaFX para el registro de clientes y gestión de solicitudes, diseñada con **Scene Builder** e implementación de navegación multi-ventana con paso de datos.

## Integrantes
* Nora Obregón
* Kellys Bellanger
* Raul Valverde
* Steven Flores

---

## 1. Descripción del Proyecto
El sistema permite registrar la información básica de los clientes y generar solicitudes asociadas. La aplicación utiliza una estructura organizada en arquitectura MVC, navegación fluida entre formularios y transferencia dinámica de datos entre pantallas.

---

## 2. Ventanas y Requerimientos

### Ventana 1: Inicio de Sesión
* **Componentes:** `Label`, `TextField` (usuario), `PasswordField` (contraseña), `Button` (Iniciar sesión / Salir).
* **Validaciones:**
  * Verificación de campos obligatorios mediante `Alert`.
  * Confirmación con `Alert` antes de cerrar la aplicación al presionar **Salir**.

### Ventana 2: Ventana Principal
* **Menús de navegación:** `MenuBar` y `ToolBar` para navegar a las pantallas de Registro y Consulta.
* **Componentes extra:** Botones de acceso directo y menú contextual (`ContextMenu`) vinculado a los elementos en pantalla.

### Ventana 3: Registro de Cliente
* **Formulario:**
  * Nombres y Apellidos (`TextField`)
  * Tipo de cliente y Ciudad (`ComboBox`)
  * Fecha de nacimiento (`DatePicker`)
  * Tipo de solicitud (`RadioButton` organizados en `ToggleGroup`)
  * Servicios de interés (`CheckBox`)
  * Fotografía (`ImageView` y botón mediante `FileChooser`)
  * Botones de acción: **Guardar**, **Limpiar** y **Cancelar**.

### Ventana 4: Consulta de Clientes
* **Tabla de datos (`TableView`):** Muestra Nombre completo, Tipo de cliente, Ciudad, Fecha de nacimiento y Tipo de solicitud.
* **Interacción:** Apertura de ventana de detalle mediante evento de ratón (`MouseEvent`).

---

## 3. Flujos de Navegación y Paso de Datos

**Flujos principales:**
1. `Inicio de sesión` → `Ventana principal` → `Registro de cliente`
2. `Ventana principal` → `Consulta de clientes` → `Detalle del cliente`

**Mecanismo de datos:**
1. Registro de cliente → Los datos se transfieren a la lista del `TableView`.
2. Selección en `TableView` → La información del cliente seleccionado se envía a la ventana de detalle para su visualización/edición.

---

## 4. Eventos e Interacciones

| Tipo de Evento | Implementación |
| :--- | :--- |
| **`ActionEvent`** | Guardar, limpiar, iniciar sesión, cambiar de ventana, cerrar aplicación y ejecutar opciones del menú. |
| **`MouseEvent`** | Doble clic en filas del `TableView` e interacciones directas con elementos gráficos. |
| **`KeyEvent`** | Tecla `ENTER` para confirmar, `ESC` para cerrar ventanas y filtro de caracteres en campos. |

---

## 5. Diálogos y Archivos

* **Alertas:** `Alert` informativo, de advertencia/error y de confirmación.
* **Ventanas modales:** Componente `Dialog` para acciones específicas.
* **Cargadores de archivos:** `FileChooser` para imágenes de perfil y `DirectoryChooser` para la selección de carpetas.

---

## 6. Requisitos Técnicos

* **Entorno de desarrollo:** IntelliJ IDEA
* **Tecnologías:** Java, JavaFX, FXML (`.fxml`)
* **Diseño gráfico:** Scene Builder
* **Patrón:** Separación estricta entre Vistas y Controladores (MVC)
