package edu.wctc.jek.bookwebapp5.Model;


import java.sql.SQLException;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author kowal_000
 */
public class AuthorDao implements AuthorDaoStrategy {
    private DBStrategy db;
    private String driverClass;
    private String url;
    private String userName;
    private String password;
    
    public AuthorDao(DBStrategy db, String driverClass, String url, String userName, String password) {
        this.db = db;
        this.driverClass = driverClass;
        this.url = url;
        this.userName = userName;
        this.password = password;
    }
    
    @Override
    public List<Author> getAllAuthors() throws Exception {
        db.openConnection(driverClass, url, userName, password);
        List<Author> records = new ArrayList<>();
        
        List<Map<String,Object>> rawData = db.findAllRecords("author");
        for(Map rawRec : rawData) {
            Author author = new Author();
            Object obj = rawRec.get("author_id");
            author.setAuthorId(Integer.parseInt(obj.toString()));
            String name = rawRec.get("author_name") == null ? "" : rawRec.get("author_name").toString();
            author.setAuthorName(name);
            obj = rawRec.get("date_added");
            Date dateAdded = (obj == null) ? new Date() : (Date)rawRec.get("date_added");
            author.setDateAdded(dateAdded);
            records.add(author);
        }
        
        db.closeConnection();
        
        return records;
        
    }
    
    public static void main(String[] args) throws Exception {
        AuthorDao dao = new AuthorDao(new MySqlDbStrategy(),"com.mysql.jdbc.Driver", "jdbc:mysql://localhost:3306/book2", "root", "admin");
    
        List<Author> authors = dao.getAllAuthors();
        for(Author a : authors) {
            System.out.println(a);
        }
    }

    @Override
    public int insertAuthors(String tableName, List columNames, List columnValues) throws Exception {
        db.openConnection(driverClass, url, userName, password);
        int recordsAffected =  db.insertRecords(tableName, columNames, columnValues);
        db.closeConnection();
        return recordsAffected;
        
    }

    @Override
    public int deleteAuthor(String tableName, String primaryKeyFieldName, Object primaryKeyValue) throws Exception {
        db.openConnection(driverClass, url, userName, password);
       int recordsAffected = db.deleteById(tableName, primaryKeyFieldName, primaryKeyValue);
         db.closeConnection();
         return recordsAffected;
    }

    @Override
    public int updateRecords(String tableName, String fieldName, Object fieldValue, List colNames, List values) throws Exception {
        db.openConnection(driverClass, url, userName, password);
        int recordsAffected = db.updateRecords(tableName, fieldName, fieldValue, colNames, values);
        db.closeConnection();
         return recordsAffected;
    }
}
