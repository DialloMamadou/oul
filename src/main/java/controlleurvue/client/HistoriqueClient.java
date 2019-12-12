package controlleurvue.client;

import basededonnee.DBconnexion;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTreeTableView;
import controlleurvue.Vue;
import daos.ClientDao;
import daos.InscriptionDao;
import daos.ReservationDao;
import daos.impl.ClientDaoImpl;
import daos.impl.InscriptionDaoImpl;
import daos.impl.ReservationDaoImpl;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import modele.Client;
import modele.Inscription;
import modele.Reservation;
import principale.Controlleur;

import java.net.URL;
import java.util.ResourceBundle;

public class HistoriqueClient implements Initializable, Vue {

    public JFXTreeTableView annulations;
    public JFXTreeTableView <Reservation>reservations;
    public JFXTreeTableView <Inscription>inscriptions;
    public JFXRadioButton btnannulation;
    public JFXRadioButton btnreservation;
    public JFXRadioButton btninscription;
    private Controlleur controlleur;
    public  static  int id;
    public Label lnom;
    public Label lprenom;
    public Label ldatenaissance;
    public Label lgroupe;
    public Label lnumero;
    public Label lobservation;
    public Label lemail;
    public Label ladresse;
    public Label lcodepostale;
    private ClientDao clientDao;

    private ReservationDao reservationDao;
    private InscriptionDao inscriptionDao;


    @Override
    public void setController(Controlleur controller) {
        this.controlleur=controller;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        clientDao=new ClientDaoImpl(DBconnexion.getConnection());
        inscriptionDao=new InscriptionDaoImpl(DBconnexion.getConnection());
        reservationDao=new ReservationDaoImpl(DBconnexion.getConnection());

        System.out.println("id = "+id);
        Client client=clientDao.getClientParId(String.valueOf(id));
        this.lnom.setText(client.nom_client.get());
        this.lprenom.setText(client.prenom_client.get());
        this.ladresse.setText(client.adresse.get());
        this.lcodepostale.setText(client.codePostale.get());
        this.ldatenaissance.setText(client.datenaissance.get());
        this.lnumero.setText(client.numero.get());
        this.lgroupe.setText(client.groupe.get());
        this.lobservation.setText(client.observation.get());
        this.lemail.setText(client.email.get());


        this.btninscription.setSelected(true);
        this.btnannulation.setSelected(false);
        this.btnreservation.setSelected(false);


    }

    public void retourmenuclient(MouseEvent mouseEvent) {
        controlleur.consulterClient();
    }

    public void visualiserinscription(MouseEvent mouseEvent) {
    }

    public void visualiserReservation(MouseEvent mouseEvent) {
    }

    public void visualiserannulation(MouseEvent mouseEvent) {
    }
}
