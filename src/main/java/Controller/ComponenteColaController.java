package Controller;

import Modelo.Persona;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class ComponenteColaController {

    @FXML
    private Label nombreUsuario;


    public void cargarDatos(Persona persona){
        nombreUsuario.setText(persona.getNombre());
    }
}
