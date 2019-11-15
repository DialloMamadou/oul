package daos;

import javafx.beans.property.StringProperty;
import modele.Groupe;
import modele.Sejour;

import java.util.List;

public interface SejourDao {

    int insererSejour(Sejour sejour);



    List<Sejour> listeSejour();


    Sejour getSejourParId(String id_sejour);

    int supprimerSejourParid(String toString);
}

