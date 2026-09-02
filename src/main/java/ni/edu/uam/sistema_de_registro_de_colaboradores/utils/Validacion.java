package ni.edu.uam.sistema_de_registro_de_colaboradores.utils;

import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.util.List;

public class Validacion {

    public static String validarTexto(TextField campo, String mensaje, int minCaracteres) {
        String valor = campo.getText() == null ? "" : campo.getText().trim();
        if (valor.length() < minCaracteres) {
            throw new IllegalArgumentException(mensaje);
        }
        return valor;
    }

    public static String validarContrasena(PasswordField campo, String mensaje, int minCaracteres) {
        String valor = campo.getText() == null ? "" : campo.getText();
        if (valor.length() < minCaracteres) {
            throw new IllegalArgumentException(mensaje);
        }
        return valor;
    }

    public static <T> T validarSeleccion(T valor, String mensaje) {
        if (valor == null) {
            throw new IllegalArgumentException(mensaje);
        }
        return valor;
    }

    public static void validarFechaNoFutura(LocalDate fecha, String mensaje) {
        if (fecha == null || fecha.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException(mensaje);
        }
    }

    public static void validarAlMenosUno(List<String> seleccionados, String mensaje) {
        if (seleccionados == null || seleccionados.isEmpty()) {
            throw new IllegalArgumentException(mensaje);
        }
    }
}