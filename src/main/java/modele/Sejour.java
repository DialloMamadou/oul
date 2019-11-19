package modele;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Sejour extends RecursiveTreeObject<Sejour> {






    public StringProperty id_sejour;
    public StringProperty duree;
    public  StringProperty date_debut;
    public StringProperty  date_fin;
    public StringProperty type;
    public StringProperty id_centre;
    public StringProperty prix;

    public StringProperty ageMin;

    public StringProperty ageMax;
    public StringProperty capacite;



    public Sejour(){
        super();
    }




    /*public Sejour(String duree, String datedebut, String datefin, String type,
                  String id, String prix, String min, String max, String capacite) {

        this.nom_centre =new SimpleStringProperty( id);
        this.duree = new SimpleStringProperty(duree);
        this.date_debut =new SimpleStringProperty( datedebut);
        this.date_fin = new SimpleStringProperty(datefin);
        this.type =new SimpleStringProperty( type);
        this.prix=new SimpleStringProperty(prix);
        this.ageMax=new SimpleStringProperty(max);
        this.ageMin=new SimpleStringProperty(min);
        this.capacite=new SimpleStringProperty(capacite);

    }*/

   public Sejour(String id_sejour, String duree, String date_debut, String date_fin, String type,
                 String id_centre, String prix, String ageMax, String ageMin, String capacite) {

        this.id_sejour = new SimpleStringProperty(id_sejour);
        this.duree = new SimpleStringProperty(duree);
        this.date_debut =new SimpleStringProperty( date_debut);
        this.date_fin = new SimpleStringProperty( date_fin);
        this.type = new SimpleStringProperty(type);
        this.id_centre =new SimpleStringProperty( id_centre);
       this.prix = new SimpleStringProperty(prix);
        this.ageMax =new SimpleStringProperty( ageMax);
        this.ageMin =new SimpleStringProperty( ageMin);
        this.capacite =new SimpleStringProperty( capacite);
    }
}
