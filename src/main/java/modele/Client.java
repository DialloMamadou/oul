package modele;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Client extends RecursiveTreeObject<Client> {


    public StringProperty id;
    public StringProperty nom_client;
    public  StringProperty prenom_client;
    public  StringProperty groupe;
    public StringProperty datenaissance;
    public StringProperty numero;
    public  StringProperty observation;
    public StringProperty email;
    public StringProperty adresse;
    public StringProperty codePostale;



    public Client(){
        super();
    }

    public Client(String id, String name,String prenom,String groupe,String datenaissance) {
        this.id = new SimpleStringProperty(id);
        this.nom_client = new SimpleStringProperty(name);
        this.prenom_client = new SimpleStringProperty(prenom);
        this.groupe = new SimpleStringProperty(groupe);
        this.datenaissance=new SimpleStringProperty(datenaissance);


    }

//nom,prenom,groupe,datenaissance,numero,observation,email,adresse,codepostale
    public Client(String nom_client, String prenom_client, String groupe, String datenaissance,
                  String numero, String observation, String email, String adresse, String codePostale) {
        this.nom_client = new SimpleStringProperty(nom_client);
        this.prenom_client = new SimpleStringProperty(prenom_client);
        this.groupe = new SimpleStringProperty(groupe);
        this.datenaissance = new SimpleStringProperty(datenaissance);
        this.numero =new SimpleStringProperty( numero);
        this.observation =new SimpleStringProperty( observation);
        this.email = new SimpleStringProperty(email);
        this.adresse =new SimpleStringProperty( adresse);
        this.codePostale = new SimpleStringProperty(codePostale);
    }

    public Client(String id,String nom_client, String prenom_client, String groupe, String datenaissance, String numero,
                  String observation, String email, String adresse, String codePostale) {

        this.nom_client = new SimpleStringProperty(nom_client);
        this.prenom_client = new SimpleStringProperty(prenom_client);
        this.groupe = new SimpleStringProperty(groupe);
        this.datenaissance = new SimpleStringProperty(datenaissance);
        this.numero =new SimpleStringProperty( numero);
        this.observation =new SimpleStringProperty( observation);
        this.email = new SimpleStringProperty(email);
        this.adresse =new SimpleStringProperty( adresse);
        this.codePostale = new SimpleStringProperty(codePostale);

    }
}
