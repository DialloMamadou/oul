package daos.impl;

import com.mysql.jdbc.PreparedStatement;
import controlleurvue.centre.CreerCentre;
import daos.EvenementDao;
import modele.Annulation;
import modele.Centre;
import modele.Evenement;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EvenementDaoImpl extends Dao<Annulation> implements EvenementDao {
    public EvenementDaoImpl(Connection conn) {
        super(conn);
    }


    @Override
    public int insererEvenement(Evenement evenement) {

        int res=0;
        String sql="INSERT INTO evenement (codeclient,codesejour,evenementa,somme,dateevenement,methode)VALUES (?,?,?,?,?,?)";
        System.out.println("evenemnt "+sql);

        try {

            PreparedStatement ps=(PreparedStatement)connect.prepareStatement(sql);
            ps.setString(1, evenement.codeclient);
            ps.setString(2,evenement.codesejour);
            System.out.println("evenement "+evenement.event);
            ps.setString(3,evenement.event);
            ps.setString(4,evenement.somme);
            System.out.println("somme "+evenement.somme);
            ps.setString(5,evenement.dateEvent);
            ps.setString(6,evenement.methode);


            res=ps.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("echec echec echec");
            Logger.getLogger(CreerCentre.class.getName()).log(Level.SEVERE, null, ex);
        }

        return res;
    }
}
