package ni.edu.uam.sistema_de_registro_de_colaboradores.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class TiendaController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}
