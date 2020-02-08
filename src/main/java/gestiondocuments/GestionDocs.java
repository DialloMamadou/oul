package gestiondocuments;

import modele.Centre;
import modele.Client;
import modele.Sejour;

import java.util.List;


public interface GestionDocs {
    public void genereAttestationFacture(Client client, Sejour sejour);
    public void genereConfirmationInscription(Client client, Sejour sejour);
    public void genereListeInscrit(List<Client> clients, Sejour sejour, Centre centre);


}
