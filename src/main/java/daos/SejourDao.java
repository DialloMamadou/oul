package daos;

import modele.Groupe;
import modele.Sejour;

import java.util.List;

public interface SejourDao {

    int insererSejour(Sejour sejour);



    List<Sejour> listeSejour();


}

