package modele;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Associationgroupesejour extends RecursiveTreeObject<Centre> {

    public StringProperty id;
    public StringProperty prix_unitaire;
    public StringProperty groupe;
    public StringProperty sejour;

    public Associationgroupesejour(String prix, String groupe, String sejour) {
        this.prix_unitaire = new SimpleStringProperty(prix);
        this.groupe = new SimpleStringProperty(groupe);
        this.sejour = new SimpleStringProperty(sejour);

    }
}
