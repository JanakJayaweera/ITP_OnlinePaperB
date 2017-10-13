
package DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionManager {
    private static String sourceURL;
    
    
    public static Connection connect(){
        Connection conn =null;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/itponlineb","root","");
            
        }
        catch(Exception e){
            System.out.println(e);
        }
    return conn;
    }
    
    /*
    MS SQL Connector------------------
    
    public ConnectionManager() {
        try {
                Class.forName("com.microsoft.sqlserver.jdbc.MSSQLDriver");
                sourceURL = new String("jdbc:sqlserver://<<ServerName>>;databaseName=<<dbName>>");
            } 
        catch (ClassNotFoundException classNotFoundException) {
                System.out.println(classNotFoundException + "-----------Unable to load database driver classes");
        }
}
    
    public Connection connect(){
        Connection dbConn = null;
        try {
            dbConn = DriverManager.getConnection(sourceURL,IntegratedSecurity=true);
        } 
        catch (Exception e) {
            System.out.println(e + "-----------DBconnection failure");
        }
        return dbConn;
}
*/

    
}
