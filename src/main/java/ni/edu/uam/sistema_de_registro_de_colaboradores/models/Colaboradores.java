package ni.edu.uam.sistema_de_registro_de_colaboradores.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Colaboradores {
    private String nombre;
    private String apellido;
    private String usuario;
    private String passwordTemp;
    private String cargo;
    private String areaTrabajo;
    private LocalDate fechaContratacion;
    private String tipoContrato;
    private String beneficios;
}
