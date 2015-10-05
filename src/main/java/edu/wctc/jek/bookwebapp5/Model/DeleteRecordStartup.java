
package edu.wctc.jek.bookwebapp5.Model;

import java.sql.Connection;

/**
 *
 * @author kowal_000
 */
public class DeleteRecordStartup {
   

	public static void main(String[] args) {

                DeleteRecordService delete = new DeleteRecordService();
                try{
                delete.openConnection("com.mysql.jdbc.Driver", "jdbc:mysql://bit.glassfish.wctc.edu:3306/sakila", "advjava", "advjava");
                
                
                    delete.deleteARecordByKey("12345","Id", "Actor");
                    
                   delete.closeConnection();
                   
                }catch(Exception ex){
                     System.out.println(ex.getMessage());
                }
                
        }  
                
}
