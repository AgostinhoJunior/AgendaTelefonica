/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agenda.model;

import java.util.Date;

/**
 *
 * @author ADJ-PC
 */
public class Contato {

    private Integer id;
    private String nome;
    private Date nascimento;
    private String email;
    private TipoContato tipoContato;
    private Telefone telefone;
    private Boolean ativo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getNascimento() {
        return nascimento;
    }

    public void setNascimento(Date nascimento) {
        this.nascimento = nascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TipoContato getTipoContato() {
        return tipoContato;
    }

    public void setTipoContato(TipoContato tipoContato) {
        this.tipoContato = tipoContato;
    }

    public Telefone getTelefone() {
        return telefone;
    }

    public void setTelefone(Telefone telefones) {
        this.telefone = telefones;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public String toString() {
        return "Contato{" + "id=" + id + ", nome=" + nome + ", nascimento=" + nascimento + ", email=" + email + ", tipoContato=" + tipoContato + ", telefones=" + telefone + '}';
    }

}
