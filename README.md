# Sistema de Registro de Colaboradores

Proyecto académico para el curso **Programación de Aplicaciones de Escritorio** — UAM.
Laboratorio "Construcción de interfaz gráfica funcional" — **Caso 2: Distribuidora El Güegüense**.

Aplicación de escritorio en JavaFX que permite registrar, mostrar, actualizar y eliminar la información básica de los colaboradores de la distribuidora.

## Integrantes

- Geanfranco Mendieta
- Anthony González

## Estructura del proyecto

```
src/main/java/ni/edu/uam/sistema_de_registro_de_colaboradores/
├── controller/
│   └── RegistroController.java
├── dao/
│   └── ColaboradorDAO.java
├── interfaces/
│   └── CRUD.java
├── models/
│   └── Colaboradores.java
├── utils/
│   └── Validacion.java
├── Launcher.java
└── RegistroApplication.java

src/main/resources/ni/edu/uam/sistema_de_registro_de_colaboradores/
└── registro-view.fxml
```

## Controles implementados

| Información | Control JavaFX |
|---|---|
| Nombres, apellidos y usuario | `TextField` |
| Contraseña temporal | `PasswordField` |
| Cargo | `ComboBox` |
| Área de trabajo | `ListView` |
| Fecha de contratación | `DatePicker` |
| Tipo de contrato | `RadioButton` |
| Beneficios | `CheckBox` |
| Imagen institucional | `ImageView` |
| Listado de colaboradores | `TableView` |

## Validaciones

- Ningún campo puede quedar vacío.
- El usuario debe tener al menos 5 caracteres.
- La contraseña debe tener al menos 8 caracteres.
- La fecha de contratación no puede ser posterior a la fecha actual.
- Se debe seleccionar un cargo, un área, un tipo de contrato y al menos un beneficio.
- Los errores se muestran mediante `Alert`.

## Eventos

- **ActionEvent** — botones Guardar, Actualizar, Limpiar y Eliminar (menú, `ToolBar` y formulario).
- **MouseEvent** — doble clic sobre una fila del `TableView` para cargar los datos en el formulario.
- **KeyEvent** — `ENTER` guarda el registro, `ESCAPE` limpia el formulario.

## Menús

- **MenuBar:** Archivo (Nuevo, Salir), Ayuda (Acerca de).
- **ToolBar:** Guardar, Limpiar, Eliminar.
- **ContextMenu:** Editar y Eliminar sobre el `TableView`.

## Arquitectura

El proyecto sigue el patrón DAO sobre una interfaz genérica `CRUD<T>`:

- **`Colaboradores`** — modelo de datos, con Lombok para getters/setters y constructores.
- **`CRUD<T>`** — contrato genérico (`agregar`, `obtenerRegistros`).
- **`ColaboradorDAO`** — implementa `CRUD<Colaboradores>`; almacena los registros en una lista en memoria (`ObservableList`, sin base de datos).
- **`Validacion`** — clase de utilidades con métodos estáticos reutilizables para las validaciones del formulario.
- **`RegistroController`** — conecta la vista (`registro-view.fxml`) con el DAO y las validaciones.

## Tecnologías

- Java 21
- JavaFX 21 (Controls, FXML)
- Maven
- Lombok

## Ejecución

```bash
mvn clean javafx:run
```
