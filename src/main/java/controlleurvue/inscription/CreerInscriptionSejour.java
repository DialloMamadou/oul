package controlleurvue.inscription;

import basededonnee.DBconnexion;
import com.mysql.jdbc.PreparedStatement;
import controlleurvue.Vue;
import controlleurvue.centre.ConsulterCentre;
import controlleurvue.sejour.CreerSejour;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
import org.controlsfx.control.Notifications;
import principale.Controlleur;

import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreerInscriptionSejour implements Initializable, Vue {


    public StackPane stack;
    public TextField paiement;
    public ComboBox client;
    public TextField observation;
    public DatePicker dateInscription;
    public ComboBox sejour;

    private Controlleur controlleur;
    @Override
    public void setController(Controlleur controller) {
        this.controlleur=controller;

        Connection connection= DBconnexion.getConnection();
        try {
            String sql="SELECT * FROM client";
            PreparedStatement ps=(PreparedStatement)connection.prepareStatement(sql);

            ResultSet rs=ps.executeQuery();

            while(rs.next()){

                System.out.println("client :"+rs.getString(2)+" "+rs.getString(3));
                this.client.getItems().add(rs.getString(2)+" "+rs.getString(3));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsulterCentre.class.getName()).log(Level.SEVERE, null, ex);
        }


        try {
            String sql="SELECT * FROM sejour";
            PreparedStatement ps=(PreparedStatement)connection.prepareStatement(sql);

            ResultSet rs=ps.executeQuery();

            while(rs.next()){

                this.sejour.getItems().add(rs.getString(5));

            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsulterCentre.class.getName()).log(Level.SEVERE, null, ex);
        }





    }

    public void back(MouseEvent mouseEvent) {
        this.controlleur.lancerPageInscription();
    }

    public void close(MouseEvent mouseEvent) {
    }




    public void book(MouseEvent mouseEvent) {

        String sql3="INSERT INTO inscription (paiement,observation,date_inscription,code_client,id_sejour) VALUES (?,?,?,?,?)";



        String sql2 = "SELECT * FROM sejour where type_sejour ='" +this.sejour.getValue().toString()+"'";



        String sentence = this.client.getValue().toString();
        String[] words = sentence.split(" ");


        String sql = "SELECT * FROM client where nom_client ='" + words[0] + "' AND prenom_client ='"+words[1]+"'";

        Connection connection = DBconnexion.getConnection();

        int res=0;



        try {

            PreparedStatement preparedStatement = (PreparedStatement) connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            int client=0;
            while(resultSet.next()){
                client=resultSet.getInt(1);
            }




            PreparedStatement preparedStatement1 = (PreparedStatement) connection.prepareStatement(sql2);
            ResultSet resultSet1 = preparedStatement1.executeQuery();
            int sejour=0;
            while(resultSet1.next()){
                sejour=resultSet1.getInt(1);
            }






            PreparedStatement ps=(PreparedStatement)connection.prepareStatement(sql3);
            ps.setString(1,this.paiement.getText().toString());
            ps.setString(2, this.observation.getText().toString());
            ps.setString(3, this.dateInscription.getValue().toString());
            ps.setInt(4, client);
            ps.setInt(5, sejour);


            res=ps.executeUpdate();




        } catch (SQLException ex) {
            Logger.getLogger(CreerSejour.class.getName()).log(Level.SEVERE, null, ex);
        }


        if(res>0){
            Image image=new Image("img/mooo.png");
            Notifications notification=Notifications.create()
                    .title("Done")
                    .text("INSCRIPTION creer avec succes")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_LEFT)
                    .graphic(new ImageView(image));
            notification.darkStyle();
            notification.show();
            //   updateStatus();
        }else{
            Image image=new Image("img/delete.png");
            Notifications notification=Notifications.create()
                    .title("Error")
                    .text("echec dans la reservation")
                    .hideAfter(Duration.seconds(3))
                    .position(Pos.BOTTOM_LEFT)
                    .graphic(new ImageView(image));
            notification.darkStyle();
            notification.show();
        }







    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
