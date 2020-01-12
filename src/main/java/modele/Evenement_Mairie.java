package modele;

public class Evenement_Mairie {

    public String id;
    public String code_groupe;
    public String codesejour;
    public String event;
    public String somme;
    public String dateEvent;
    public String methode;


    public Evenement_Mairie(String id, String code_groupe, String codesejour, String event, String somme, String date
            , String methode) {
        this.id = id;
        this.code_groupe = code_groupe;
        this.codesejour = codesejour;
        this.event = event;
        this.somme = somme;
        this.dateEvent = date;
        this.methode=methode;
    }


    public Evenement_Mairie(String code_groupe, String codesejour, String event, String somme, String date
            , String methode) {
        this.code_groupe = code_groupe;
        this.codesejour = codesejour;
        this.event = event;
        this.somme = somme;
        this.dateEvent = date;
        this.methode=methode;
    }


    public Evenement_Mairie() {
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode_groupe() {
        return code_groupe;
    }

    public void setCode_groupe(String code_groupe) {
        this.code_groupe = code_groupe;
    }

    public String getCodesejour() {
        return codesejour;
    }

    public void setCodesejour(String codesejour) {
        this.codesejour = codesejour;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getSomme() {
        return somme;
    }

    public void setSomme(String somme) {
        this.somme = somme;
    }

    public String getDateEvent() {
        return dateEvent;
    }

    public void setDateEvent(String dateEvent) {
        this.dateEvent = dateEvent;
    }

    public String getMethode() {
        return methode;
    }

    public void setMethode(String methode) {
        this.methode = methode;
    }

}
