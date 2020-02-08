package controlleurvue.client;

import controlleurvue.Vue;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import principale.Controlleur;

import java.net.URL;
import java.util.ResourceBundle;

public class EditerClient implements Initializable, Vue {

    private Controlleur controlleur;
    public static int id;

    public TextField observation;
    public TextField portable;
    public TextField email;
    public TextField poste;
    public TextField adresse;
    public TextField groupecomplet;
    public ComboBox depart;
    public Label lnom;
    public Label lprenom;
    public Label ldate;

    public void back(MouseEvent mouseEvent) {
    }

    public void creer(MouseEvent mouseEvent) {
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    @Override
    public void setController(Controlleur controller) {
        this.controlleur=controller;
    }
}
