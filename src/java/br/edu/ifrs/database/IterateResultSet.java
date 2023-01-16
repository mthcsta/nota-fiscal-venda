/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifrs.database;

import br.edu.ifrs.entities.Entity;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author mathe
 */
@FunctionalInterface
public interface IterateResultSet {
    public abstract Entity run(ResultSet rs) throws SQLException;    
}
