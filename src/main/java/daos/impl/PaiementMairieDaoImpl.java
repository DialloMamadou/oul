package daos.impl;

import com.mysql.jdbc.PreparedStatement;
import controlleurvue.centre.CreerCentre;
import daos.InscriptionDao;
import daos.PaiementMairieDao;
import modele.Inscription;
import modele.PaiementMarie;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PaiementMairieDaoImpl extends Dao<Dao> implements PaiementMairieDao {


    public PaiementMairieDaoImpl(Connection conn) {
        super(conn);
    }

    @Override
    public int inserrerPaiement(PaiementMarie paiementMarie) {
        int res=0;
        String sql="INSERT INTO paiement_mairie (groupe,paiement,sejour) VALUES (?,?,?)";
        try {
            PreparedStatement ps=(PreparedStatement)connect.prepareStatement(sql);
            ps.setString(1, paiementMarie.groupe.get());
            ps.setString(1, paiementMarie.paiement.get());
            ps.setString(1, paiementMarie.sejour.get());



            res=ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CreerCentre.class.getName()).log(Level.SEVERE, null, ex);
        }

        return res;
    }
}
