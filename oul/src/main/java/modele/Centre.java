package modele;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Centre extends RecursiveTreeObject<Centre> {


    public StringProperty id;
    public StringProperty nom_centre;

    public Centre(){
        super();
    }

    public Centre(String id, String name) {
        this.id = new SimpleStringProperty(id);
        this.nom_centre = new SimpleStringProperty(name);

    }
}
