package modele;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Groupe  extends RecursiveTreeObject<Groupe> {


    public StringProperty id;
    public StringProperty nom_centre;

    public Groupe(){
        super();
    }

    public Groupe(String id, String name) {
        this.id = new SimpleStringProperty(id);
        this.nom_centre = new SimpleStringProperty(name);

    }
}
