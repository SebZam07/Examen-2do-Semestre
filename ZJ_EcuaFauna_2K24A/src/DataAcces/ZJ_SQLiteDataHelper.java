package DataAcces;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class ZJ_SQLiteDataHelper {
    private static String zjDBPathConnection = "jdbc:sqlite:DataBase//ZJ_EcuaFauna.sqlite"; 
    private static Connection zjConn = null;
    // protected SQLiteDataHelper(){}
    
    protected static synchronized Connection zjOpenConnection() throws Exception{
        try {
            if(zjConn == null)
                zjConn = DriverManager.getConnection(zjDBPathConnection);
        } catch (SQLException e) {
             throw e;   //new Exception(e,"SQLiteDataHelper","Fallo la coneccion a la base de datos");
        } 
        return zjConn;
    }

    protected static void ZJcloseConnection() throws Exception{
        try {
            if (zjConn != null)
                zjConn.close();
        } catch (Exception e) {
            throw e;    //new Exception(e,"SQLiteDataHelper", "Fallo la conección con la base de datos");
        }
    }

}
