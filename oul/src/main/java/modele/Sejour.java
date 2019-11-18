package modele;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Sejour extends RecursiveTreeObject<Sejour> {


    public StringProperty id;
    public StringProperty duree;
    public  StringProperty date_debut;
    public StringProperty  date_fin;
    public StringProperty type;
    public StringProperty nom_centre;

    public Sejour(){
        super();
    }


    public Sejour(String id, String duree, String date_debut, String date_fin, String type, String nom_centre) {
        this.id =new SimpleStringProperty( id);
        this.duree = new SimpleStringProperty(duree);
        this.date_debut =new SimpleStringProperty( date_debut);
        this.date_fin = new SimpleStringProperty(date_fin);
        this.type =new SimpleStringProperty( type);
        this.nom_centre =new SimpleStringProperty( nom_centre);
    }



}
