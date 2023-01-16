package br.edu.ifrs.repository;

import br.edu.ifrs.database.DatabaseConnection;
import br.edu.ifrs.entities.Cliente;
import br.edu.ifrs.entities.Entity;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.stream.Collectors;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author mathe
 */
public class ClientesRepository implements Repository {
    
    private DatabaseConnection databaseConnection;
    
    public ClientesRepository() {
        this.databaseConnection = DatabaseConnection.getConnection();                        
    }
    
    public Cliente findById(long id) {
        Cliente[] clientes = databaseConnection.select(
            "select * from clientes where id = ?", 
            (bind) -> {
                bind.setLong(1, id);
            },
            (resultSet) -> new Cliente(
                    resultSet.getLong("id"), 
                    resultSet.getString("cpf"), 
                    resultSet.getString("nome")
        )).toArray(new Cliente[0]);
        return clientes.length == 0 ? null : (Cliente) clientes[0];
    }
    
    public Cliente[] findAll() {
        return (Cliente[]) databaseConnection.select(
            "select * from clientes",
            (resultSet) -> new Cliente(
                resultSet.getLong("id"),
                resultSet.getString("cpf"),
                resultSet.getString("nome")
        )).toArray(new Cliente[0]);
    }
    
    public Cliente insert(Cliente cliente) {
        long id = databaseConnection.insert(
            "insert into clientes (cpf, nome) values (?, ?)",
            (bind) -> {
                bind.setString(1, cliente.getCpf());
                bind.setString(2, cliente.getNome());
        });
        return findById(id);
    }
    
    public int removeById(long id) {
        return databaseConnection.delete(
            "delete from clientes where id = ?",
            (bind) -> {
                bind.setLong(1, id);
        });
    }
    
    public void update(Cliente cliente) {
        databaseConnection.update(
            "update clientes set nome = ?, cpf = ? where id = ?",
            (bindPreparedStatement) -> {
                bindPreparedStatement.setString(1, cliente.getNome());
                bindPreparedStatement.setString(2, cliente.getCpf());
                bindPreparedStatement.setLong(3, cliente.getId());
        });
    }
    
}
