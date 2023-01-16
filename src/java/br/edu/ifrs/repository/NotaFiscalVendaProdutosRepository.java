/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifrs.repository;

import br.edu.ifrs.database.DatabaseConnection;
import br.edu.ifrs.entities.NotaFiscalVendaProduto;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author mathe
 */
public class NotaFiscalVendaProdutosRepository implements Repository {
    private DatabaseConnection databaseConnection;
    
    public NotaFiscalVendaProdutosRepository() {
        this.databaseConnection = DatabaseConnection.getConnection();                        
    }
    
    public NotaFiscalVendaProduto[] findByNumeroDaNota(long id) {
        return databaseConnection.select(
            "select p.*, nfvhp.unidades_vendida, nfvhp.id from notafiscalvenda_has_produtos nfvhp, produtos p where nfvhp.Produtos_codigo_identificador = p.codigo_identificador and nfvhp.NotaFiscalVenda_numero_da_nota = ?", 
            (bind) -> {
                bind.setLong(1, id);
            },
            (resultSet) -> new NotaFiscalVendaProduto(
                    resultSet.getLong("id"), 
                    id,
                    resultSet.getInt("unidades_vendida"),
                    resultSet.getLong("codigo_identificador"),
                    resultSet.getString("nome"),
                    resultSet.getString("descricao_completa"),
                    resultSet.getFloat("preco_unitario")
        )).toArray(new NotaFiscalVendaProduto[0]);
    }
    
    public void insertAll(List<NotaFiscalVendaProduto> notaFiscalVendaProdutos) {
        notaFiscalVendaProdutos.forEach((notaFiscalVendaProduto) -> insert(notaFiscalVendaProduto));
    }
    
    public long insert(NotaFiscalVendaProduto notaFiscalVendaProduto) {
        return databaseConnection.insert(
            "insert into notafiscalvenda_has_produtos (NotaFiscalVenda_numero_da_nota, Produtos_codigo_identificador, unidades_vendida) values (?, ?, ?)", 
            (bindPreparedStatement) -> {
                bindPreparedStatement.setLong(1, notaFiscalVendaProduto.getNumeroDaNota());
                bindPreparedStatement.setLong(2, notaFiscalVendaProduto.getCodigoIdentificador());
                bindPreparedStatement.setInt(3, notaFiscalVendaProduto.getUnidadesVendidas());                
        });
    }
    
    public int removeByNumeroDaNota(long id) {
        return databaseConnection.delete(
            "delete from notafiscalvenda_has_produtos where NotaFiscalVenda_numero_da_nota = ?",
            (bind) -> {
                bind.setLong(1, id);
        });
    }
    
}
