package daos.impl;

import com.mysql.jdbc.PreparedStatement;
import controlleurvue.sejour.CreerSejour;
import daos.AssociationGroupeSejourDao;
import daos.GroupeSejourClientDao;
import modele.Associationgroupesejour;
import modele.GroupeSejourClient;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GroupeSejourClientDaoImpl extends Dao<Associationgroupesejour> implements GroupeSejourClientDao {

    public GroupeSejourClientDaoImpl(Connection conn) {
        super(conn);
    }

    @Override
    public int insererGroupeSejourClient(GroupeSejourClient groupeSejourClient) {
        int []tab=new int[2];
        String sql="INSERT INTO groupe_sejour_client (id_groupe,id_sejour,id_client) VALUES (?,?,?)";
        ResultSet ress=null;
        int res=0;
        try {


            PreparedStatement ps=(PreparedStatement)connect.prepareStatement(sql);

            ps.setString(1, groupeSejourClient.idGroupe);
            ps.setString(2, groupeSejourClient.idSejour);
            ps.setString(3, groupeSejourClient.idClient);





            res=ps.executeUpdate();
             ress =ps.getGeneratedKeys();

        } catch (SQLException ex) {
            Logger.getLogger(CreerSejour.class.getName()).log(Level.SEVERE, null, ex);
        }


        return res;
    }
}
