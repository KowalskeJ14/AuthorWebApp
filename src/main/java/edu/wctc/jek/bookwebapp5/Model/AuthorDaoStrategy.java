package edu.wctc.jek.bookwebapp5.Model;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author kowal_000
 */
public interface AuthorDaoStrategy {

    List<Author> getAllAuthors() throws Exception;
    
    int insertAuthors(String tableName, List columNames, List columnValues) throws Exception;
    
    int deleteAuthor(String tableName, String primaryKeyFieldName, Object primaryKeyValue) throws Exception;
    
     public int updateRecords(String tableName, String fieldName, Object fieldValue, List colNames, List values) throws Exception;
}
