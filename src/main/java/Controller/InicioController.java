package Controller;

import Cola.ColaPrioritaria;
import Modelo.CentroAsistenciaMedica;
import Modelo.Persona;
import Modelo.Prioridad;
import Principal.Principal;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.shape.SVGPath;

public class InicioController {
    @FXML
    private Button btnAgregar;

    @FXML
    private SVGPath discapacitado;

    @FXML
    private TextField nombreCliente;

    @FXML
    private SVGPath cabezaEmbarazada;

    @FXML
    private SVGPath cuerpoEmbarazada;

    @FXML
    private VBox embarazada;

    @FXML
    private VBox filaGeneral;

    @FXML
    private VBox filaPremium;

    @FXML
    private SVGPath planGeneral;

    @FXML
    private SVGPath planPremium;

    private boolean esEmbarazada=false;
    private boolean esDiscapacitado=false;
    private boolean esPremium=false;
    private boolean esNormal=false;



    public Parent cargarUsuarios(Persona persona) throws Exception{

        FXMLLoader loader = new FXMLLoader( Principal.class.getResource("/view/ComponenteCola.fxml") );
        Parent parent = loader.load();

        ComponenteColaController controller = loader.getController();
        controller.cargarDatos(persona);

        return parent;

    }
    public void setDiscapacitado(){
        reiniciarEstados();
        discapacitado.setOpacity(0.2);
        esDiscapacitado=true;
    }
    public void setEmbarazada(){
        reiniciarEstados();
        cuerpoEmbarazada.setOpacity(0.2);
        cabezaEmbarazada.setOpacity(0.2);
        esEmbarazada=true;
    }
    public void setGeneral(){
        reiniciarEstados();
        planGeneral.setOpacity(0.2);
        esNormal=true;
    }
    public void setPremium(){
        reiniciarEstados();
        planPremium.setOpacity(0.2);
        esPremium=true;
    }

    private void reiniciarEstados(){
        esPremium = false;
        planPremium.setOpacity(1);

        esEmbarazada = false;
        cabezaEmbarazada.setOpacity(1);
        cuerpoEmbarazada.setOpacity(1);

        esDiscapacitado = false;
        discapacitado.setOpacity(1);

        esNormal = false;
        planGeneral.setOpacity(1);
    }

    public void agregarPersona(){

        if(esDiscapacitado || esEmbarazada){
            Persona persona=new Persona(nombreCliente.getText(), Prioridad.PRIORITARIA);
            CentroAsistenciaMedica.getInstance().colaNormalPrioritaria.encolar(persona);
        }else if(esPremium){
            Persona persona=new Persona(nombreCliente.getText(), Prioridad.PREMIUM);;
            CentroAsistenciaMedica.getInstance().colaPremium.encolar(persona);
        }else{
            Persona persona=new Persona(nombreCliente.getText(), Prioridad.NORMAL);
            CentroAsistenciaMedica.getInstance().colaNormalPrioritaria.encolar(persona);
        }

        anadirAFilaGeneral();
        anadirAFilaPremium();
    }
    public void anadirAFilaGeneral(){
        filaGeneral.getChildren().clear();
        ColaPrioritaria<Persona> colaP=CentroAsistenciaMedica.getInstance().getColaNormalPrioritaria();
        //System.out.println(colaP.toString());
        try {
            for (int i = 0; i < colaP.getTamano(); i++) {
                filaGeneral.getChildren().add(cargarUsuarios((Persona)colaP.obtener(i)));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void anadirAFilaPremium(){
        filaPremium.getChildren().clear();
        ColaPrioritaria<Persona> colaPremium=CentroAsistenciaMedica.getInstance().getColaPremium();
        //System.out.println(colaPremium.toString());
        try {
            for (int i = 0; i < colaPremium.getTamano(); i++) {
                filaPremium.getChildren().add(cargarUsuarios( (Persona)colaPremium.obtener(i)));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void atender(){

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    ColaPrioritaria<Persona> colaP = CentroAsistenciaMedica.getInstance().getColaNormalPrioritaria();

                    while( !colaP.estaVacia() ) {
                        colaP.desencolar();
                        Platform.runLater(() -> {
                            anadirAFilaGeneral();
                        });
                        Thread.sleep(2000);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                ColaPrioritaria<Persona> colaPremium=CentroAsistenciaMedica.getInstance().getColaPremium();
                while( !colaPremium.estaVacia() ) {
                    colaPremium.desencolar();
                    Platform.runLater(() -> {
                        anadirAFilaPremium();
                    });
                    Thread.sleep(2000);
                }
            }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();

    }



}
