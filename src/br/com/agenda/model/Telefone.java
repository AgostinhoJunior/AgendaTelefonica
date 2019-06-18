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
public class Telefone {

    private Integer id;
    private Integer Ddd;
    private Integer numero;
    private Contato contato;

    public Integer getDdd() {
        return Ddd;
    }

    public void setDdd(Integer Ddd) {
        this.Ddd = Ddd;
    }

    public Integer getNumero() {
        return numero;
    }

    public void setNumero(Integer numero) {
        this.numero = numero;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    @Override
    public String toString() {
        return "Telefone{" + "id=" + id + ", Ddd=" + Ddd + ", numero=" + numero + ", contato=" + contato + '}';
    }

}
