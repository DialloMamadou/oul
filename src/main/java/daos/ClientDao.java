package daos;

import modele.Centre;
import modele.Client;

import java.util.List;

public interface ClientDao {


    int insererClient(Client client);


    int supprimerClient(String id);


    List<Client> listeClient();


    Client getClientParId(String id);

    Client getClientParNomPrenomAnneeNaissance(String text, String text1, String text2);

    List<Client> getClientsBySejour(String id);

    Centre getCentreBySejour(String id_sejour);
}
