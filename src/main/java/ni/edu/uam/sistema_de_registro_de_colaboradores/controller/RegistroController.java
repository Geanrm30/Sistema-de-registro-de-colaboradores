package ni.edu.uam.sistema_de_registro_de_colaboradores.controller;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import ni.edu.uam.sistema_de_registro_de_colaboradores.dao.ColaboradorDAO;
import ni.edu.uam.sistema_de_registro_de_colaboradores.models.Colaboradores;
import ni.edu.uam.sistema_de_registro_de_colaboradores.utils.Validacion;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class RegistroController {

    @FXML private TextField txtNombres;
    @FXML private TextField txtApellidos;
    @FXML private TextField txtUsuario;
    @FXML private PasswordField txtContrasena;
    @FXML private ComboBox<String> cmbCargo;
    @FXML private ListView<String> lstArea;
    @FXML private DatePicker dpFechaContratacion;
    @FXML private ToggleGroup grupoContrato;
    @FXML private RadioButton rbPermanente;
    @FXML private RadioButton rbTemporal;
    @FXML private CheckBox chkSeguro;
    @FXML private CheckBox chkAlimentacion;
    @FXML private CheckBox chkTransporte;

    @FXML private TableView<Colaboradores> tablaColaboradores;
    @FXML private TableColumn<Colaboradores, String> colNombre;
    @FXML private TableColumn<Colaboradores, String> colCargo;
    @FXML private TableColumn<Colaboradores, String> colArea;
    @FXML private TableColumn<Colaboradores, String> colFecha;
    @FXML private TableColumn<Colaboradores, String> colContrato;
    @FXML private TableColumn<Colaboradores, String> colBeneficios;

    private final ColaboradorDAO listado = new ColaboradorDAO();
    private final ObservableList<Colaboradores> datosTabla = FXCollections.observableArrayList();
    private Colaboradores colaboradorSeleccionado;

    @FXML
    public void initialize() {
        cmbCargo.setItems(FXCollections.observableArrayList(
                "Vendedor", "Cajero", "Bodeguero", "Supervisor", "Gerente"
        ));

        lstArea.setItems(FXCollections.observableArrayList(
                "Ventas", "Bodega", "Administración", "Logística", "Atención al cliente"
        ));

        prepararTabla();
    }

    private void prepararTabla() {
        colNombre.setCellValueFactory(fila -> new SimpleStringProperty(fila.getValue().getNombreCompleto()));
        colCargo.setCellValueFactory(fila -> new SimpleStringProperty(fila.getValue().getCargo()));
        colArea.setCellValueFactory(fila -> new SimpleStringProperty(fila.getValue().getArea()));
        colFecha.setCellValueFactory(fila -> new SimpleStringProperty(
                fila.getValue().getFechaContratacion().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))));
        colContrato.setCellValueFactory(fila -> new SimpleStringProperty(fila.getValue().getTipoContrato()));
        colBeneficios.setCellValueFactory(fila -> new SimpleStringProperty(
                String.join(", ", fila.getValue().getBeneficios())));

        tablaColaboradores.setItems(datosTabla);
    }


    @FXML
    protected void nuevoRegistro() {
        limpiarFormulario();
    }

    @FXML
    protected void salir() {
        Platform.exit();
    }

    @FXML
    protected void mostrarAcercaDe() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Acerca de");
        alert.setHeaderText("Distribuidora El Güegüense");
        alert.setContentText("Sistema de registro de colaboradores.\nProyecto académico - UAM.");
        alert.showAndWait();
    }


    @FXML
    protected void guardarColaborador() {
        try {
            Colaboradores nuevo = leerFormulario();
            listado.agregar(nuevo);
            datosTabla.setAll(listado.obtenerRegistros());
            limpiarFormulario();
        } catch (IllegalArgumentException e) {
            mostrarError(e.getMessage());
        }
    }

    @FXML
    protected void actualizarColaborador() {
        if (colaboradorSeleccionado == null) {
            mostrarError("Seleccione un colaborador de la tabla para actualizar");
            return;
        }
        try {
            Colaboradores datos = leerFormulario();

            colaboradorSeleccionado.setNombres(datos.getNombres());
            colaboradorSeleccionado.setApellidos(datos.getApellidos());
            colaboradorSeleccionado.setUsuario(datos.getUsuario());
            colaboradorSeleccionado.setContrasena(datos.getContrasena());
            colaboradorSeleccionado.setCargo(datos.getCargo());
            colaboradorSeleccionado.setArea(datos.getArea());
            colaboradorSeleccionado.setFechaContratacion(datos.getFechaContratacion());
            colaboradorSeleccionado.setTipoContrato(datos.getTipoContrato());
            colaboradorSeleccionado.setBeneficios(datos.getBeneficios());

            tablaColaboradores.refresh();
            limpiarFormulario();

        } catch (IllegalArgumentException e) {
            mostrarError(e.getMessage());
        }
    }

    @FXML
    protected void eliminarColaborador() {
        Colaboradores seleccionado = tablaColaboradores.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarError("Seleccione un colaborador de la tabla para eliminar");
            return;
        }
        listado.eliminar(seleccionado);
        datosTabla.setAll(listado.obtenerRegistros());
        if (seleccionado == colaboradorSeleccionado) {
            limpiarFormulario();
        }
    }

    @FXML
    protected void limpiarFormulario() {
        txtNombres.clear();
        txtApellidos.clear();
        txtUsuario.clear();
        txtContrasena.clear();
        cmbCargo.getSelectionModel().clearSelection();
        lstArea.getSelectionModel().clearSelection();
        dpFechaContratacion.setValue(null);
        grupoContrato.selectToggle(null);
        chkSeguro.setSelected(false);
        chkAlimentacion.setSelected(false);
        chkTransporte.setSelected(false);
        colaboradorSeleccionado = null;
    }


    @FXML
    protected void cargarFilaSeleccionada(MouseEvent event) {
        if (event.getClickCount() != 2) {
            return;
        }
        Colaboradores seleccionado = tablaColaboradores.getSelectionModel().getSelectedItem();
        if (seleccionado != null) {
            cargarEnFormulario(seleccionado);
        }
    }

    @FXML
    protected void editarDesdeMenu() {
        Colaboradores seleccionado = tablaColaboradores.getSelectionModel().getSelectedItem();
        if (seleccionado == null) {
            mostrarError("Seleccione un colaborador para editar");
            return;
        }
        cargarEnFormulario(seleccionado);
    }

    @FXML
    protected void manejarTeclado(KeyEvent event) {
        if (event.getCode() == KeyCode.ENTER) {
            guardarColaborador();
        } else if (event.getCode() == KeyCode.ESCAPE) {
            limpiarFormulario();
        }
    }


    private void cargarEnFormulario(Colaboradores c) {
        colaboradorSeleccionado = c;
        txtNombres.setText(c.getNombres());
        txtApellidos.setText(c.getApellidos());
        txtUsuario.setText(c.getUsuario());
        txtContrasena.setText(c.getContrasena());
        cmbCargo.setValue(c.getCargo());
        lstArea.getSelectionModel().select(c.getArea());
        dpFechaContratacion.setValue(c.getFechaContratacion());

        if ("Permanente".equals(c.getTipoContrato())) {
            rbPermanente.setSelected(true);
        } else {
            rbTemporal.setSelected(true);
        }

        chkSeguro.setSelected(c.getBeneficios().contains("Seguro médico"));
        chkAlimentacion.setSelected(c.getBeneficios().contains("Alimentación"));
        chkTransporte.setSelected(c.getBeneficios().contains("Transporte"));
    }

    private Colaboradores leerFormulario() {
        String nombres = Validacion.validarTexto(txtNombres, "Ingrese los nombres", 1);
        String apellidos = Validacion.validarTexto(txtApellidos, "Ingrese los apellidos", 1);
        String usuario = Validacion.validarTexto(txtUsuario, "El usuario debe tener al menos 5 caracteres", 5);
        String contrasena = Validacion.validarContrasena(txtContrasena, "La contraseña debe tener al menos 8 caracteres", 8);
        String cargo = Validacion.validarSeleccion(cmbCargo.getValue(), "Seleccione un cargo");
        String area = Validacion.validarSeleccion(lstArea.getSelectionModel().getSelectedItem(), "Seleccione un área de trabajo");

        LocalDate fecha = dpFechaContratacion.getValue();
        Validacion.validarFechaNoFutura(fecha, "La fecha de contratación no puede ser posterior a hoy");

        RadioButton contratoSeleccionado = (RadioButton) grupoContrato.getSelectedToggle();
        if (contratoSeleccionado == null) {
            throw new IllegalArgumentException("Seleccione el tipo de contrato");
        }
        String tipoContrato = contratoSeleccionado.getText();

        List<String> beneficios = new ArrayList<>();
        if (chkSeguro.isSelected()) beneficios.add("Seguro médico");
        if (chkAlimentacion.isSelected()) beneficios.add("Alimentación");
        if (chkTransporte.isSelected()) beneficios.add("Transporte");
        Validacion.validarAlMenosUno(beneficios, "Seleccione al menos un beneficio");

        return new Colaboradores(nombres, apellidos, usuario, contrasena, cargo, area, fecha, tipoContrato, beneficios);
    }

    private void mostrarError(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}