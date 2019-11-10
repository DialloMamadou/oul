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
    public StringProperty datenaissance;
    public StringProperty numero;
    public  StringProperty observation;
    public StringProperty email;
    public StringProperty adresse;
    public StringProperty codePostale;


    public Client(String id, String name,String prenom,String age,String groupe,String datenaissance,
                  String numero,String observation,String email,String adresse,String CodePostale) {
        this.id = new SimpleStringProperty(id);
        this.nom_client = new SimpleStringProperty(name);
        this.prenom_client = new SimpleStringProperty(prenom);
        this.age_client = new SimpleStringProperty(age);
        this.groupe = new SimpleStringProperty(groupe);

        this.datenaissance = new SimpleStringProperty(datenaissance);
        this.adresse = new SimpleStringProperty(adresse);
        this.numero = new SimpleStringProperty(numero);
        this.observation = new SimpleStringProperty(observation);
        this.email = new SimpleStringProperty(email);
        this.codePostale = new SimpleStringProperty(CodePostale);




    }

    public Client(String name,String prenom,String age,String groupe,String datenaissance,
                  String numero,String observation,String email,String adresse,String CodePostale) {
        this.nom_client = new SimpleStringProperty(name);
        this.prenom_client = new SimpleStringProperty(prenom);
        this.age_client = new SimpleStringProperty(age);
        this.groupe = new SimpleStringProperty(groupe);

        this.datenaissance = new SimpleStringProperty(datenaissance);
        this.adresse = new SimpleStringProperty(adresse);
        this.numero = new SimpleStringProperty(numero);
        this.observation = new SimpleStringProperty(observation);
        this.email = new SimpleStringProperty(email);
        this.codePostale = new SimpleStringProperty(CodePostale);




    }




    public Client(){
        super();
    }

    public Client(String id, String name,String prenom,String age,String groupe,String datenaissance) {
        this.id = new SimpleStringProperty(id);
        this.nom_client = new SimpleStringProperty(name);
        this.prenom_client = new SimpleStringProperty(prenom);
        this.age_client = new SimpleStringProperty(age);
        this.groupe = new SimpleStringProperty(groupe);
        this.datenaissance=new SimpleStringProperty(datenaissance);


    }


}
