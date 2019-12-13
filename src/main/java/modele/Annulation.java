package modele;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Annulation extends RecursiveTreeObject<Annulation> {


    public StringProperty id;
    public StringProperty motif;
    public StringProperty  idsejour;
    public StringProperty idclient;

    public Annulation(StringProperty id, StringProperty motif, StringProperty idsejour, StringProperty idclient) {
        this.id = id;
        this.motif = motif;
        this.idsejour = idsejour;
        this.idclient = idclient;
    }

    public Annulation(String s, String text, String lidclientText) {
        this.id=new SimpleStringProperty(s);
        this.motif=new SimpleStringProperty(s);
        this.idsejour=new SimpleStringProperty(text);
        this.idclient=new SimpleStringProperty(lidclientText);


    }


}
