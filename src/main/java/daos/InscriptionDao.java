package daos;

import modele.Inscription;

import java.util.List;

public interface InscriptionDao {


    List<Inscription> getInscriptions();

    int insererInscription(Inscription inscription2);

    int supperimerParId(String toString);
}
