package ni.edu.uam.sistema_de_registro_de_colaboradores;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistroApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RegistroApplication.class.getResource("/ni/edu/uam/sistema_de_registro_de_colaboradores/registro-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1050, 780);
        stage.setTitle("Distribuidora El Güegüense - Registro de Colaboradores");
        stage.setScene(scene);
        stage.show();
    }
}
