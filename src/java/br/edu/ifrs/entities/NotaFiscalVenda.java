/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifrs.entities;

import br.edu.ifrs.repository.ClientesRepository;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author mathe
 */
public class NotaFiscalVenda implements Entity, Serializable {
    
    protected long numeroDaNota;
    protected String emissao;
    protected String expedicao;
    protected Cliente cliente;
    protected long clienteId;
    protected List<NotaFiscalVendaProduto> produtos;
    
    public NotaFiscalVenda(long numeroDaNota, String emissao, String expedicao, Cliente cliente, List<NotaFiscalVendaProduto> produtos) {
        this.numeroDaNota = numeroDaNota;
        this.emissao = emissao;
        this.expedicao = expedicao;
        this.cliente = cliente;
        this.produtos = produtos;        
    }
    
    /*
    * Usado para insert
    */
    public NotaFiscalVenda(String emissao, String expedicao, long clienteId) {
        this.numeroDaNota = 0;
        this.emissao = emissao;
        this.expedicao = expedicao;
        this.clienteId = clienteId;
        this.cliente = null;
        this.produtos = null;        
    }
 
    public long getNumeroDaNota() {
        return numeroDaNota;
    }

    public void setNumeroDaNota(long numeroDaNota) {
        this.numeroDaNota = numeroDaNota;
    }

    public String getEmissao() {
        return emissao;
    }

    public void setEmissao(String emissao) {
        this.emissao = emissao;
    }

    public String getExpedicao() {
        return expedicao;
    }

    public void setExpedicao(String expedicao) {
        this.expedicao = expedicao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public long getClienteId() {
        if (cliente != null) {
            return cliente.getId();
        }
        return clienteId;
    }
    
    public void setClienteId(long clienteId) {
        this.clienteId = clienteId;
        this.cliente = null;
    }

    public List<NotaFiscalVendaProduto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<NotaFiscalVendaProduto> produtos) {
        this.produtos = produtos;
    }
    
    public String getClienteIdentificacao() {
        return String.format("%s (%s)", cliente.getNome(), cliente.getCpf()); 
    }   
    
    public double getPrecoTotal() {
        return produtos
                .stream()
                .map((notaFiscalVendaProduto) -> notaFiscalVendaProduto.getPrecoTotal())
                .reduce(0.0, Double::sum);
    }
    
    public String getProdutosString() {
        return produtos
                .stream()
                .map((notaFiscalVendaProduto) -> String.format("%s (%d)", notaFiscalVendaProduto.getNome(), notaFiscalVendaProduto.getUnidadesVendidas()))
                .collect(Collectors.joining(", "));
    }
}
