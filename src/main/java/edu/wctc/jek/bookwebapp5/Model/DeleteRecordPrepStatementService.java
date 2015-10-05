
package edu.wctc.jek.bookwebapp5.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 *
 * @author kowal_000
 */
public class DeleteRecordPrepStatementService {
      private Connection conn;
    private final String deleteString = "DELETE FROM ? WHERE ? = ?";
    private PreparedStatement delete;
    private final int tableIndex = 1;
    private final int keyNameIndex = 2;
    private final int keyValIndex = 3;
    
    
    public void openConnection(String driverClass, String url, String userName, String password) throws Exception
    {
        Class.forName (driverClass);
			  conn = DriverManager.getConnection(url, userName, password);
    }
    
    public void closeConnection() throws Exception
    {
        conn.close();
    }
    public void deleteARecordByKey(String keyName, String keyVal, String tableName)throws Exception{
        boolean success = false;
        
       this.delete = conn.prepareStatement(this.deleteString);
       //this won't work cant pass in tbale name
       
        delete.setObject(this.tableIndex, tableName);
           delete.setObject(this.keyNameIndex, keyName);

          delete.setObject(this.keyValIndex, keyVal);

         delete.execute();
         conn.commit();
        
       
    }
}
