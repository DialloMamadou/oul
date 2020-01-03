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

    Client getClientParNomEtPrenom(String arg, String arg1);
}
