/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifrs.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author mathe
 */
@FunctionalInterface
public interface BindPreparedStatement {
    public abstract void run(PreparedStatement ps) throws SQLException;
}
