package modele;

public class GroupeSejourClient {

    public String id;
    public String idGroupe;
    public String idSejour;
    public String idClient;

    public GroupeSejourClient(String id, String idGroupe, String idSejour, String idClient) {
        this.id = id;
        this.idGroupe = idGroupe;
        this.idSejour = idSejour;
        this.idClient = idClient;
    }

    public GroupeSejourClient( String idGroupe, String idSejour, String idClient) {
        this.id = id;
        this.idGroupe = idGroupe;
        this.idSejour = idSejour;
        this.idClient = idClient;
    }
}
