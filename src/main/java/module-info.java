module ni.edu.uam.sistema_de_registro_de_colaboradores {
    requires javafx.controls;
    requires javafx.fxml;
    requires static lombok;


    opens ni.edu.uam.sistema_de_registro_de_colaboradores to javafx.fxml;
    exports ni.edu.uam.sistema_de_registro_de_colaboradores;
    exports ni.edu.uam.sistema_de_registro_de_colaboradores.controller;
    opens ni.edu.uam.sistema_de_registro_de_colaboradores.controller to javafx.fxml;
}