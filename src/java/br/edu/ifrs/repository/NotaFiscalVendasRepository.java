/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifrs.repository;

import br.edu.ifrs.database.DatabaseConnection;
import br.edu.ifrs.entities.Cliente;
import br.edu.ifrs.entities.Entity;
import br.edu.ifrs.entities.NotaFiscalVenda;
import java.util.Arrays;

/**
 *
 * @author mathe
 */
public class NotaFiscalVendasRepository implements Repository {
    
    private DatabaseConnection databaseConnection;
    private NotaFiscalVendaProdutosRepository notaFiscalVendaProdutosRepository;
    
    public NotaFiscalVendasRepository() {
        this.databaseConnection = DatabaseConnection.getConnection();
        this.notaFiscalVendaProdutosRepository = new NotaFiscalVendaProdutosRepository();
    }
    
    public NotaFiscalVenda findByNumeroDaNota(long numeroDaNota) {
        NotaFiscalVenda[] notaFiscalVenda = databaseConnection.select(
            "select nfv.*, c.id cliente_id, c.cpf cliente_cpf, c.nome cliente_nome from notafiscalvenda nfv, clientes c where nfv.Clientes_id = c.id and numero_da_nota = ?",
            (bind) -> {
                bind.setLong(1, numeroDaNota);
            },
            (resultSet) -> new NotaFiscalVenda(
                    //     public NotaFiscalVenda(long numeroDaNota, String emissao, String expedicao, Cliente cliente, List<NotaFiscalVendaProduto> produtos) {
                    resultSet.getLong("numero_da_nota"),
                    resultSet.getString("emissao"),
                    resultSet.getString("expedicao"),
                    new Cliente(resultSet.getLong("cliente_id"), resultSet.getString("cliente_cpf"), resultSet.getString("cliente_nome")),
                    Arrays.asList(notaFiscalVendaProdutosRepository.findByNumeroDaNota(resultSet.getLong("numero_da_nota")))
        )).toArray(new NotaFiscalVenda[0]);
        return notaFiscalVenda.length == 0 ? null : notaFiscalVenda[0];
    }
    
    public NotaFiscalVenda[] findAll() {
        return databaseConnection.select(
            "select nfv.*, c.id cliente_id, c.cpf cliente_cpf, c.nome cliente_nome from notafiscalvenda nfv, clientes c where nfv.Clientes_id = c.id",
            (resultSet) -> new NotaFiscalVenda(
                    //     public NotaFiscalVenda(long numeroDaNota, String emissao, String expedicao, Cliente cliente, List<NotaFiscalVendaProduto> produtos) {
                    resultSet.getLong("numero_da_nota"),
                    resultSet.getString("emissao"),
                    resultSet.getString("expedicao"),
                    new Cliente(resultSet.getLong("cliente_id"), resultSet.getString("cliente_cpf"), resultSet.getString("cliente_nome")),
                    Arrays.asList(notaFiscalVendaProdutosRepository.findByNumeroDaNota(resultSet.getLong("numero_da_nota")))
        )).toArray(new NotaFiscalVenda[0]);
    }
    
    public NotaFiscalVenda insert(NotaFiscalVenda notaFiscalVenda) {
        long id = databaseConnection.insert(
            "insert into notafiscalvenda (emissao, expedicao, Clientes_id) values (?, ?, ?)",
            (bind) -> {
                bind.setString(1, notaFiscalVenda.getEmissao());
                bind.setString(2, notaFiscalVenda.getExpedicao());
                bind.setLong(3, notaFiscalVenda.getClienteId());
        });
        return findByNumeroDaNota(id);
    }
    
    public int removeByNumeroDaNota(long numeroDaNota) {
        return databaseConnection.delete(
            "delete from notafiscalvenda where numero_da_nota = ?",
            (bind) -> {
                bind.setLong(1, numeroDaNota);
        });
    }
    
    public void update(NotaFiscalVenda notaFiscalVenda) {
        databaseConnection.update(
            "update notafiscalvenda set emissao = ?, expedicao = ?, Clientes_id = ? where numero_da_nota = ?",
            (bindPreparedStatement) -> {
                bindPreparedStatement.setString(1, notaFiscalVenda.getEmissao());
                bindPreparedStatement.setString(2, notaFiscalVenda.getExpedicao());
                bindPreparedStatement.setLong(3, notaFiscalVenda.getClienteId());
                bindPreparedStatement.setLong(4, notaFiscalVenda.getNumeroDaNota());                
        });
    }
    
}
