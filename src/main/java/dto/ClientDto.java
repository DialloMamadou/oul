package dto;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ClientDto  extends RecursiveTreeObject<ClientDto> {


public StringProperty nom_client;
public  StringProperty prenom_client;
public  StringProperty age_client;
public StringProperty groupe;


public ClientDto(){
        super();
        }

public ClientDto(String name,String prenom,String age,String groupe) {
        this.nom_client = new SimpleStringProperty(name);
        this.prenom_client = new SimpleStringProperty(prenom);
        this.age_client = new SimpleStringProperty(age);
        this.groupe = new SimpleStringProperty(groupe);


        }
}
