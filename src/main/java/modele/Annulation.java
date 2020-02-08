package modele;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Annulation extends RecursiveTreeObject<Annulation> {



    public StringProperty id;
    public StringProperty motif;
    public StringProperty  idsejour;
    public StringProperty idclient;


    private  String triche="";
    private String triche2="";





    public Annulation(String id, String motif, String id_sejour, String id_client) {
        this.id=new SimpleStringProperty(id);
        this.motif=new SimpleStringProperty(motif);
        this.idsejour=new SimpleStringProperty(id_sejour);
        this.idclient=new SimpleStringProperty(id_client);

    }

    public String getTriche() {
        return triche;
    }

    public void setTriche(String triche) {
        this.triche = triche;
    }

    public String getTriche2() {
        return triche2;
    }

    public void setTriche2(String triche2) {
        this.triche2 = triche2;
    }
}
