package principale;

import fabrique.FabriqueVue;
import javafx.stage.Stage;

public class Controlleur {

    private FabriqueVue fabriqueVue;

    public Controlleur(Stage stage){

        this. fabriqueVue= new FabriqueVue(stage, this);

        this.fabriqueVue.creerLoginVue();
    }

    public void lancerPageAccueil() {
        this.fabriqueVue.creerPageAcceuil();
    }

    public void lancerPageCentre() {
        this.fabriqueVue.creerEcranVue();
    }


    public void creerCentre() {
        this.fabriqueVue.creerCentreVue();
    }

    public void consulterCentre() {
        this.fabriqueVue.creerConsulterCentreVue();
    }


    public void lancerPageGroupe() {
        this.fabriqueVue.creerVueGroupe();
    }

    public void creerGroupe() {
        this.fabriqueVue.creerVueCreeGroupe(this);
    }

    public void creerVueGroupeCreation() {
        this.fabriqueVue.creervuecreationgroupe();
    }

    public void consulterGroupe() {
        this.fabriqueVue.creerConsulterGroupeVue();
    }

    public void lancerPageSejour() {
        this.fabriqueVue.creerVueSejour();
    }

    public void creerSejour() {
        this.fabriqueVue.creerVuecreerSejour();
    }

    public void consulterSejour() {
        this.fabriqueVue.creerConsulterSejourVue();
    }

    public void lancerPageClient() {
        this.fabriqueVue.creerVueClient();
    }

    public void creerInscription() {
        this.fabriqueVue.creerVueCreerClient();
    }

    public void consulterClient() {
        this.fabriqueVue.creerConsulterClientVue();
    }

    public void lancerPageInscription() {
        this.fabriqueVue.creerVueInscription();
    }

    public void creerInscriptionSejourVue() {
        this.fabriqueVue.creerVueInscriptionSejour();
    }

    public void consulterInscription() {
        this.fabriqueVue.creerConsulterInscriptionVue();

    }
}
