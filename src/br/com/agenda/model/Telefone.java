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
    private String Ddd;
    private String numero;
    private Contato contato;

    public String getDdd() {
        return Ddd;
    }

    public void setDdd(String Ddd) {
        this.Ddd = Ddd;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Telefone{" + "id=" + id + ", Ddd=" + Ddd + ", numero=" + numero + ", contato=" + contato + '}';
    }
}
