/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifrs.entities;

/**
 *
 * @author mathe
 */
public class NotaFiscalVendaProduto extends Produto {
    protected long id;
    protected long numeroDaNota;
    protected long produtoCodigoIdentificador;
    protected int unidadesVendidas;

    public NotaFiscalVendaProduto(long id, long numeroDaNota, int unidadesVendidas, long codigoIdentificador, String nome, String descricaoCompleta, float precoUnitario) {
        super(codigoIdentificador, nome, descricaoCompleta, precoUnitario);
        this.id = id;
        this.numeroDaNota = numeroDaNota;
        this.produtoCodigoIdentificador = codigoIdentificador;
        this.unidadesVendidas = unidadesVendidas;
    }

    /*
    * Usado para insert
    */    
    public NotaFiscalVendaProduto(long numeroDaNota, int unidadesVendidas, long codigoIdentificador) {
        super(codigoIdentificador, null, null, 0.0f);
        this.id = 0;
        this.numeroDaNota = numeroDaNota;
        this.produtoCodigoIdentificador = codigoIdentificador;
        this.unidadesVendidas = unidadesVendidas;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getNumeroDaNota() {
        return numeroDaNota;
    }

    public void setNumeroDaNota(long numeroDaNota) {
        this.numeroDaNota = numeroDaNota;
    }

    public long getProdutoCodigoIdentificador() {
        return produtoCodigoIdentificador;
    }

    public void setProdutoCodigoIdentificador(long produtoCodigoIdentificador) {
        this.produtoCodigoIdentificador = produtoCodigoIdentificador;
    }

    public int getUnidadesVendidas() {
        return unidadesVendidas;
    }

    public void setUnidadesVendidas(int unidadesVendidas) {
        this.unidadesVendidas = unidadesVendidas;
    }
    
    public double getPrecoTotal() {
        return unidadesVendidas * precoUnitario;
    }
    
}
