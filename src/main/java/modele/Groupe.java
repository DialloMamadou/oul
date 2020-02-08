package modele;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Groupe  extends RecursiveTreeObject<Groupe> {



    public StringProperty id;
    public StringProperty nom_groupe;
    public StringProperty code_tiers;

    public Groupe(){
        super();
    }

/*
    public Groupe(String id, String name) {
        this.id = new SimpleStringProperty(id);
        this.nom_groupe = new SimpleStringProperty(name);


    }

*/



    public Groupe(String id, String name,String codeTiers) {
        this.id = new SimpleStringProperty(id);
        this.nom_groupe = new SimpleStringProperty(name);
        this.code_tiers=new SimpleStringProperty(codeTiers);


    }
}
