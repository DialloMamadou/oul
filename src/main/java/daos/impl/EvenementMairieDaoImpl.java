package daos.impl;

import com.mysql.jdbc.PreparedStatement;
import controlleurvue.centre.CreerCentre;
import daos.EvenementDao;
import daos.EvenementMairieDao;
import modele.Annulation;
import modele.Evenement;
import modele.Evenement_Mairie;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EvenementMairieDaoImpl extends Dao<Evenement_Mairie> implements EvenementMairieDao {



    public EvenementMairieDaoImpl(Connection conn) {
        super(conn);
    }

    @Override
    public int insererEvenement(Evenement_Mairie evenement) {
        if(evenement==null){
            System.out.println("nul nul nul");
        }
        int res=0;
        String sql="INSERT INTO evenement_mairie (groupe,sejour,evenement,somme,date,methode)VALUES (?,?,?,?,?,?)";
        System.out.println("evenemnt "+sql);

        try {
            PreparedStatement ps=(PreparedStatement)connect.prepareStatement(sql);
            ps.setString(1, evenement.code_groupe);
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
