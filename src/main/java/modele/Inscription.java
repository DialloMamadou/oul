package modele;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Inscription extends RecursiveTreeObject<Inscription> {


    public StringProperty id;
    public StringProperty paiement;
    public StringProperty  dateinscription;
    public StringProperty code_client;
    public StringProperty id_sejour;
    public  StringProperty depart;


    public Inscription(){
        super();
    }


    public Inscription(String id, String paiement, String dateinscription, String code_client, String id_sejour, String depart) {
        this.id = new SimpleStringProperty(id);
        this.paiement = new SimpleStringProperty(paiement);
        this.depart =new SimpleStringProperty(depart);
        this.dateinscription = new SimpleStringProperty(dateinscription);
        this.code_client = new SimpleStringProperty(code_client);
        this.id_sejour = new SimpleStringProperty(id_sejour);
    }


    public Inscription( String paiement, String dateinscription, String code_client, String id_sejour, String depart) {
        this.paiement = new SimpleStringProperty(paiement);
        this.depart =new SimpleStringProperty(depart);
        this.dateinscription = new SimpleStringProperty(dateinscription);
        this.code_client = new SimpleStringProperty(code_client);
        this.id_sejour = new SimpleStringProperty(id_sejour);
    }
}
