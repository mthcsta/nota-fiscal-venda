/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifrs.database;

import br.edu.ifrs.entities.Entity;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Predicate;

/**
 *
 * @author mathe
 */
public class DatabaseConnection {
    private Connection connection = null;
    private static DatabaseConnection instancia = null;
    
    private DatabaseConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/biblioteca_universitaria", "root", "root");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public static DatabaseConnection getConnection() {
        if (instancia == null) {
            instancia = new DatabaseConnection();
        }
        return instancia;
    }
    
    public List<Entity> select(String sql, BindPreparedStatement bindPreparedStatement, IterateResultSet iterateResultSet) {
        List<Entity> list = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            bindPreparedStatement.run(preparedStatement);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                list.add(iterateResultSet.run(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public List<Entity> select(String sql, IterateResultSet iterateResultSet) {
        List<Entity> list = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                list.add(iterateResultSet.run(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public long insert(String sql, BindPreparedStatement bindPreparedStatement) {
        long id = -1;
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            bindPreparedStatement.run(preparedStatement);
            preparedStatement.execute();
            ResultSet resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                id = resultSet.getLong(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }
    
    public int delete(String sql, BindPreparedStatement bindPreparedStatement) {
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            bindPreparedStatement.run(preparedStatement);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    public int update(String sql, BindPreparedStatement bindPreparedStatement) {
        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            bindPreparedStatement.run(preparedStatement);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;        
    }

}
