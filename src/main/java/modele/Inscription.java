package modele;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;


public class Inscription extends RecursiveTreeObject<Inscription> {


    public StringProperty id;
    public StringProperty paiement;
    public  StringProperty observation;
    public StringProperty  dateinscription;
    public StringProperty code_client;
    public StringProperty id_sejour;

    public Inscription(){
        super();
    }


    public Inscription(String id, String paiement, String observation, String dateinscription, String code_client, String id_sejour) {
        this.id = new SimpleStringProperty(id);
        this.paiement = new SimpleStringProperty(paiement);
        this.observation =new SimpleStringProperty( observation);
        this.dateinscription = new SimpleStringProperty(dateinscription);
        this.code_client = new SimpleStringProperty(code_client);
        this.id_sejour = new SimpleStringProperty(id_sejour);
    }
}
