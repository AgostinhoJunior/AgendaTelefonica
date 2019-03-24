/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agenda.model;

/**
 *
 * @author ADJ-PC
 */
public class TipoContato {

    private Integer id;
    private String nome;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo() {
        return nome;
    }

    public void setTipo(String tipo) {
        this.nome = tipo;
    }

    @Override
    public String toString() {
        return getTipo();
    }
}
