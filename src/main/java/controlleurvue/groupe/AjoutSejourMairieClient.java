package controlleurvue.groupe;

import controlleurvue.Vue;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import principale.Controlleur;

import java.net.URL;
import java.util.ResourceBundle;

public class AjoutSejourMairieClient implements Vue, Initializable {
    public Label lgrouped;
    public Label sejour;
    public Label date;
    public Label centre;
    private Controlleur controlleur;

    public void back(MouseEvent mouseEvent) {
        this.controlleur.lancerPageConsulterGroupeSejour();
    }

    @Override
    public void setController(Controlleur controller) {
        this.controlleur=controller;

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void book(MouseEvent mouseEvent) {
    }
}
