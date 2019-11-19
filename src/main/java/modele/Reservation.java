package modele;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Reservation {

    public StringProperty id;
    public StringProperty  dateinscription;
    public StringProperty code_client;
    public StringProperty id_sejour;
    public  StringProperty depart;





    public Reservation(String id, String dateinscription, String code_client, String id_sejour, String depart) {
        this.id = new SimpleStringProperty(id);
        this.depart =new SimpleStringProperty(depart);
        this.dateinscription = new SimpleStringProperty(dateinscription);
        this.code_client = new SimpleStringProperty(code_client);
        this.id_sejour = new SimpleStringProperty(id_sejour);
    }


    public Reservation(  String dateinscription, String code_client, String id_sejour, String depart) {
        this.depart =new SimpleStringProperty(depart);
        this.dateinscription = new SimpleStringProperty(dateinscription);
        this.code_client = new SimpleStringProperty(code_client);
        this.id_sejour = new SimpleStringProperty(id_sejour);
    }


}
