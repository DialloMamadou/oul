package daos;

import modele.Centre;

import java.util.List;

public interface CentreDao {


    int inserrerCentre(String nom_centre,String capacite);


    int supprimerCentre(String id);


    List<Centre> listeCentres();


    Centre getCentreParId(String id);
}
