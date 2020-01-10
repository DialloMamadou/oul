package modele;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class PaiementMarie  extends RecursiveTreeObject<PaiementMarie> {

    public StringProperty id;
    public StringProperty paiement;
    public StringProperty groupe;
    public StringProperty sejour;

    public PaiementMarie(String prix, String groupe, String sejour,String nbPlace) {
        this.groupe = new SimpleStringProperty(groupe);
        this.sejour = new SimpleStringProperty(sejour);
        this.paiement=new SimpleStringProperty(prix);

    }

}
