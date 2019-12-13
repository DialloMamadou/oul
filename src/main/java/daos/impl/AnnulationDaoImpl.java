package daos.impl;

import com.mysql.jdbc.PreparedStatement;
import controlleurvue.centre.CreerCentre;
import daos.AnnulationDao;
import daos.CentreDao;
import modele.Annulation;
import modele.Centre;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AnnulationDaoImpl extends Dao<Annulation> implements AnnulationDao {

    public AnnulationDaoImpl(Connection conn) {
        super(conn);
    }


    @Override
    public int insererAnnulation(Annulation annulation) {
        int res=0;
        String sql="INSERT INTO annulation (motif,sejourconcerne,clientannule) VALUES (?,?,?)";
        try {
            PreparedStatement ps=(PreparedStatement)connect.prepareStatement(sql);
            ps.setString(1, annulation.motif.get());
            ps.setString(2,annulation.idsejour.get());
            ps.setString(3,annulation.idclient.get());


            res=ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CreerCentre.class.getName()).log(Level.SEVERE, null, ex);
        }

        return res;

    }
}
