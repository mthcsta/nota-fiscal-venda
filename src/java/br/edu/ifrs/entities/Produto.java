/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.edu.ifrs.entities;

import java.io.Serializable;

/**
 *
 * @author mathe
 */
public class Produto implements Entity, Serializable {
    protected long codigoIdentificador;
    protected String nome;
    protected String descricaoCompleta;
    protected float precoUnitario;

    public Produto(long codigoIdentificador, String nome, String descricaoCompleta, float precoUnitario) {
        this.codigoIdentificador = codigoIdentificador;
        this.nome = nome;
        this.descricaoCompleta = descricaoCompleta;
        this.precoUnitario = precoUnitario;
    }

    /*
    * Usado para insert
    */
    public Produto(String nome, String descricaoCompleta, float precoUnitario) {
        this.codigoIdentificador = 0;
        this.nome = nome;
        this.descricaoCompleta = descricaoCompleta;
        this.precoUnitario = precoUnitario;
    }    
    
    public long getCodigoIdentificador() {
        return codigoIdentificador;
    }

    public void setCodigoIdentificador(long codigoIdentificador) {
        this.codigoIdentificador = codigoIdentificador;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricaoCompleta() {
        return descricaoCompleta;
    }

    public void setDescricaoCompleta(String descricaoCompleta) {
        this.descricaoCompleta = descricaoCompleta;
    }

    public float getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(float precoUnitario) {
        this.precoUnitario = precoUnitario;
    }
    
    
    
    
}
