package gestiondocuments;

import modele.Client;
import modele.Sejour;


public interface GestionDocs {
    public void genereAttestationFacture(Client client, Sejour sejour);
}
