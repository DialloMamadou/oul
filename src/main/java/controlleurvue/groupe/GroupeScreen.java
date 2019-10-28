package controlleurvue.groupe;

import controlleurvue.Vue;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import principale.Controlleur;

public class GroupeScreen implements Vue {


    public Pane pane_3;

    public void souris_dessus_1(MouseEvent mouseEvent) {
        pane_1.setStyle("-fx-background-color: #377ce5; -fx-background-radius: 6px;");
    }

    public void souris_sort_1(MouseEvent mouseEvent) {
        pane_1.setStyle("-fx-background-color: white; -fx-background-radius: 6px;");

    }

    public void souris_dessus_3(MouseEvent mouseEvent) {
        pane_3.setStyle("-fx-background-color: #377ce5; -fx-background-radius: 6px;");
    }

    public void souris_sort_3(MouseEvent mouseEvent) {
        pane_3.setStyle("-fx-background-color: white; -fx-background-radius: 6px;");

    }

    public void souris_dessus_2(MouseEvent mouseEvent) {
        pane_2.setStyle("-fx-background-color: #377ce5; -fx-background-radius: 6px;");

    }

    public void souris_sort_2(MouseEvent mouseEvent) {
        pane_2.setStyle("-fx-background-color: white; -fx-background-radius: 6px;");

    }

    public StackPane stack;
    public Pane pane_1;
    public Pane pane_2;




    public void goback(MouseEvent mouseEvent) {
        this.controlleur.lancerPageAccueil();
    }


    public void consultergroupe(MouseEvent mouseEvent) {
        this.controlleur.consulterGroupe();
    }
    private Controlleur controlleur;

    @Override
    public void setController(Controlleur controller) {
        this.controlleur=controller;

    }

    public void creerGroupe(MouseEvent mouseEvent) {
        System.out.println("irci");
        if(controlleur==null){
            System.out.println("controlleur null");
        }
        this.controlleur.creerVueGroupeCreation();
    }
}
