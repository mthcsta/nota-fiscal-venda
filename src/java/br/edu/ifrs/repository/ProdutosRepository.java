/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifrs.repository;

import br.edu.ifrs.database.DatabaseConnection;
import br.edu.ifrs.entities.Entity;
import br.edu.ifrs.entities.Produto;

/**
 *
 * @author mathe
 */
public class ProdutosRepository implements Repository {
    private DatabaseConnection databaseConnection;
    
    public ProdutosRepository() {
        this.databaseConnection = DatabaseConnection.getConnection();                        
    }

    public Produto findByCodigoIdentificador(long codigoIdentificador) {
        Produto[] produtos = databaseConnection.select(
            "select * from produtos where codigo_identificador = ?", 
            (bind) -> {
                bind.setLong(1, codigoIdentificador);
            },
            (resultSet) -> new Produto(
                    resultSet.getLong("codigo_identificador"),
                    resultSet.getString("nome"),
                    resultSet.getString("descricao_completa"),
                    resultSet.getFloat("preco_unitario")
        )).toArray(new Produto[0]);
        return produtos.length == 0 ? null : produtos[0];
    }

    public Produto[] findAll() {
        return databaseConnection.select(
            "select * from produtos", 
            (resultSet) -> new Produto(
                    resultSet.getLong("codigo_identificador"),
                    resultSet.getString("nome"),
                    resultSet.getString("descricao_completa"),
                    resultSet.getFloat("preco_unitario")
        )).toArray(new Produto[0]);
    }
    
    public Produto insert(Produto produto) {
        long id = databaseConnection.update(
            "insert into produtos (nome, descricao_completa, preco_unitario) values (?, ?, ?)",
            (bindPreparedStatement) -> {
                bindPreparedStatement.setString(1, produto.getNome());
                bindPreparedStatement.setString(2, produto.getDescricaoCompleta());
                bindPreparedStatement.setFloat(3, produto.getPrecoUnitario());
        });
        return findByCodigoIdentificador(id);
    }
    
    public int removeByCodigoIdentificador(long codigoIdentificador) {
        return databaseConnection.delete(
            "delete from produtos where codigo_identificador = ?",
            (bind) -> {
                bind.setLong(1, codigoIdentificador);
        });
    }
    
    public void update(Produto produto) {
        databaseConnection.update(
            "update produtos set nome = ?, descricao_completa = ?, preco_unitario = ? where codigo_identificador = ?",
            (bindPreparedStatement) -> {
                bindPreparedStatement.setString(1, produto.getNome());
                bindPreparedStatement.setString(2, produto.getDescricaoCompleta());
                bindPreparedStatement.setFloat(3, produto.getPrecoUnitario());
                bindPreparedStatement.setLong(4, produto.getCodigoIdentificador());
        });
    }
    
}
