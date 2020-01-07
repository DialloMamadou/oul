package daos.impl;

import com.mysql.jdbc.PreparedStatement;
import controlleurvue.centre.CreerCentre;
import controlleurvue.groupe.AssocierGroupeSejour;
import daos.AssociationGroupeSejourDao;
import modele.Associationgroupesejour;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AssociationGroupeSejourImpl extends Dao<Associationgroupesejour> implements AssociationGroupeSejourDao {

    public AssociationGroupeSejourImpl(Connection conn) {
        super(conn);
    }

    @Override
    public int inserrerAssociation(Associationgroupesejour associerGroupeSejour) {
        int res=0;
        String sql="INSERT INTO associationgroupesejour (prix_unitaire,groupe,sejour) VALUES (?,?,?)";
        try {
            PreparedStatement ps=(PreparedStatement)connect.prepareStatement(sql);
            ps.setString(1,associerGroupeSejour.prix_unitaire.get());
            ps.setString(2,associerGroupeSejour.groupe.get());
            ps.setString(3,associerGroupeSejour.sejour.get());


            res=ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(CreerCentre.class.getName()).log(Level.SEVERE, null, ex);
        }

        return res;

    }
}
