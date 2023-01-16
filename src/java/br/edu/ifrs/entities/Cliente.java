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
public class Cliente implements Entity, Serializable {
    private long id;
    private String cpf;
    private String nome;

    public Cliente(long id, String cpf, String nome) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
    }
    
    /*
    * Usado para insert
    */
    public Cliente(String cpf, String nome) {
        this.id = 0;
        this.cpf = cpf;
        this.nome = nome;
    }    

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
}
