/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.jek.bookwebapp5.repository;

import edu.wctc.jek.bookwebapp5.Entity.Author;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author kowal_000
 */
public interface AuthorRepository extends JpaRepository<Author ,Integer>, Serializable {
    
}
