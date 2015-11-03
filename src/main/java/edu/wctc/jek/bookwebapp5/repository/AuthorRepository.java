/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.jek.bookwebapp5.repository;

import com.fasterxml.jackson.databind.util.JSONPObject;
import edu.wctc.jek.bookwebapp5.Entity.Author;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 *
 * @author kowal_000
 */
public interface AuthorRepository extends JpaRepository<Author ,Integer>, Serializable {
//    @Query("SELECT a FROM Author a JOING FETCH a.bookSet WHERE .authorId = (:id)")
//    public Author finaByIDANdFetchBooksEagerly(@Param("id") Integer id);
//    
//    @Query("SELECT a.authorName FROM Author a")
//    public JSONPObject[] findAllWithNameOnly();
}
