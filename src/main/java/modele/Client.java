package modele;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Client extends RecursiveTreeObject<Client> {


    public StringProperty id;
    public StringProperty nom_client;
    public  StringProperty prenom_client;
    public  StringProperty age_client;
    public  StringProperty groupe;


    public Client(){
        super();
    }

    public Client(String id, String name,String prenom,String age,String groupe) {
        this.id = new SimpleStringProperty(id);
        this.nom_client = new SimpleStringProperty(name);
        this.prenom_client = new SimpleStringProperty(prenom);
        this.age_client = new SimpleStringProperty(age);
        this.groupe = new SimpleStringProperty(groupe);


    }
}
