
package javareservationsystem;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author CP Razer Gaming
 */
public class DBConnection {
    //this class is made for establishing connection with mysql database
    //using mysql connectorj and xamp
    public Connection createConnection()
    {
    Connection connection = null;
    MysqlDataSource mds = new MysqlDataSource();
    mds.setServerName("localhost");
    mds.setPortNumber(3306);
    mds.setUser("root");
    mds.setPassword("");
    mds.setDatabaseName("javareservation_db");
    
        try {
            connection = mds.getConnection();
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
       return connection;
    }
}