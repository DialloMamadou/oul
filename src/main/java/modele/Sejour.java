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
    public StringProperty ageMax;
    public StringProperty ageMin;
    public StringProperty capacite;
    public StringProperty prix;

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

    public Sejour( String duree, String date_debut, String date_fin, String type, String nom_centre) {
        this.duree = new SimpleStringProperty(duree);
        this.date_debut =new SimpleStringProperty( date_debut);
        this.date_fin = new SimpleStringProperty(date_fin);
        this.type =new SimpleStringProperty( type);
        this.nom_centre =new SimpleStringProperty( nom_centre);
    }


    public Sejour(String duree, String date_debut, String date_fin, String type, String nom_centre, String max, String min, String capacite) {
        this.duree = new SimpleStringProperty(duree);
        this.date_debut =new SimpleStringProperty( date_debut);
        this.date_fin = new SimpleStringProperty(date_fin);
        this.type =new SimpleStringProperty( type);
        this.nom_centre =new SimpleStringProperty( nom_centre);
        this.ageMax=new SimpleStringProperty(max);
        this.ageMin=new SimpleStringProperty(min);
        this.capacite=new SimpleStringProperty(capacite);
    }

    public Sejour(String duree, String datedebut, String datefin, String type, String id, String max, String min, String capacite, String prix) {

        this.duree = new SimpleStringProperty(duree);
        this.date_debut =new SimpleStringProperty( datedebut);
        this.date_fin = new SimpleStringProperty(datefin);
        this.type =new SimpleStringProperty( type);
        this.nom_centre =new SimpleStringProperty( id);
        this.ageMax=new SimpleStringProperty(max);
        this.ageMin=new SimpleStringProperty(min);
        this.capacite=new SimpleStringProperty(capacite);
        this.prix=new SimpleStringProperty(prix);

    }
}
