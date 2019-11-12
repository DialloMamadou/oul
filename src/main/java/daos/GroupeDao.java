package daos;

import modele.Centre;
import modele.Groupe;

import java.util.List;

public interface GroupeDao {



    int inserrerGroupe(String nom_groupe);



    int supprimerGroupe(String id);



    List<Groupe> listeGroupes();

    Groupe trouverGroupeParNomGroupe(String nom_groupe);

    Groupe getGroupeParId(String id);

}
