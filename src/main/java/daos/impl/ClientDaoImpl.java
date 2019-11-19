package daos.impl;

import basededonnee.DBconnexion;
import com.mysql.jdbc.PreparedStatement;
import controlleurvue.sejour.CreerSejour;
import daos.CentreDao;
import daos.ClientDao;
import daos.GroupeDao;
import daos.SejourDao;
import modele.Centre;
import modele.Client;
import modele.Groupe;
import modele.Sejour;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClientDaoImpl extends Dao<Client> implements ClientDao {



    public ClientDaoImpl(Connection conn) {
        super(conn);
    }

    @Override
    public int insererClient(Client client) {
        String sql="INSERT INTO client (nom_client,prenom_client,groupe_client,numero,observation,email,adresse,code_postale,datenaissance) VALUES (?,?,?,?,?,?,?,?,?)";

        int res=0;
        try {


            PreparedStatement ps=(PreparedStatement)connect.prepareStatement(sql);
            System.out.println("prenom_client :"+client.prenom_client.get());
            System.out.println("cote postale :"+client.codePostale.get());

            ps.setString(1, client.prenom_client.get());
            ps.setString(2, client.nom_client.get());
            ps.setString(3, client.groupe.get());
            ps.setString(4,client.numero.get());
            ps.setString(5,client.observation.get());
            ps.setString(6,client.email.get());
            ps.setString(7,client.adresse.get());
            ps.setString(8,client.codePostale.get());
            ps.setString(9,client.datenaissance.get());





            res=ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CreerSejour.class.getName()).log(Level.SEVERE, null, ex);
        }

        return res;

    }

    @Override
    public int supprimerClient(String id) {
        return 0;
    }

    @Override
    public List<Client> listeClient() {
        System.out.println("xxxx");

        GroupeDao groupeDao=new GroupeDaoImpl(connect);


        String sql="SELECT * FROM client ";
        List<Client>liste=new ArrayList<>();

        try{
            PreparedStatement ps=(PreparedStatement)connect.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();

            while(rs.next()){

                System.out.println("xxxx1");
                Groupe groupe=groupeDao.getGroupeParId(rs.getString(4));
                liste.add(new Client(rs.getString(1),rs.getString(2),rs.getString(3),
                        groupe.nom_groupe.get(),rs.getString(5),
                        rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),
                        rs.getString(10)));
            }


        }catch (Exception e){

        }

        return liste;
    }

    @Override
    public Client getClientParId(String id){
    String sql="SELECT * FROM Client WHERE id ='"+id+"'";
    Client client=null;
        try{
        PreparedStatement ps=(PreparedStatement)connect.prepareStatement(sql);
        ResultSet rs=ps.executeQuery();

        while(rs.next()){

            client=new Client(rs.getString(1),
                    rs.getString(2),
                    rs.getString(3),
                    rs.getString(4),
                    rs.getString(5),
                    rs.getString(6),
                    rs.getString(7),
                    rs.getString(8),
                    rs.getString(9),
                    rs.getString(10));

        }


    }catch (Exception e){

    }

        return client;
}

    @Override
    public Client getClientParNomPrenomAnneeNaissance(String text, String text1, String text2) {
        Client Client=null;

        String sql="SELECT * FROM client where nom_client ='"+text+"' AND prenom_client='"+text1+
                "' AND datenaissance='"+text2;

        System.out.println("sql "+sql);

        try {

            PreparedStatement ps = (PreparedStatement) DBconnexion.getConnection().prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            System.out.println("requete execute");

            while (rs.next()) {
                Client=new Client(rs.getString(1),rs.getString(2),rs.getString(3),
                        rs.getString(4),rs.getString(5),rs.getString(6),
                        rs.getString(7),rs.getString(8),rs.getString(9)
                        ,rs.getString(10));

            }
        }catch (Exception e){

        }
        return Client;

    }

    @Override
    public List<Client> getClientsBySejour(String id) {

        Centre centre = this.getCentreBySejour(id);

        String sql="SELECT * FROM client WHERE id_sejour="+Integer.parseInt(id);
        List<Client> liste=new ArrayList<>();

        GroupeDao groupeDao=new GroupeDaoImpl(connect);


        try{
            PreparedStatement ps=(PreparedStatement)connect.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();

            while(rs.next()){

                Groupe groupe=groupeDao.getGroupeParId(rs.getString(4));
                System.out.println("est cente = "+centre.nom_centre.get());

                liste.add(new Client(rs.getString(1),rs.getString(2),rs.getString(3),
                        groupe.nom_groupe.get(),rs.getString(5),
                        centre.nom_centre.get(),rs.getString(7),rs.getString(8),rs.getString(9),
                        rs.getString(10)));
            }


        }catch (Exception e){

        }

        return liste;
    }

    @Override
    public Centre getCentreBySejour(String id_sejour) {
        String sqlCentre="SELECT * FROM centre INNER JOIN sejour ON centre.id_centre=sejour.id_sejour WHERE id_sejour="+Integer.parseInt(id_sejour);
        Centre centre = null;

        try {


            PreparedStatement psCentre = (PreparedStatement) connect.prepareStatement(sqlCentre);
            ResultSet rsCentre = psCentre.executeQuery();

            if (rsCentre.next()) {
                System.out.println("Nom Centre:  " + rsCentre.getString(2));

                centre= new Centre(
                        rsCentre.getString(1),
                        rsCentre.getString(2),
                        rsCentre.getString(3)
                        );
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } ;
            return centre;

    }
}
