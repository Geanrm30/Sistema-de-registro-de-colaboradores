package ni.edu.uam.sistema_de_registro_de_colaboradores.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Colaboradores {
    private String nombres;
    private String apellidos;
    private String usuario;
    private String contrasena;
    private String cargo;
    private String area;
    private LocalDate fechaContratacion;
    private String tipoContrato;
    private List<String> beneficios;

    public String getNombreCompleto() {
        return nombres + " " + apellidos;
    }
}
