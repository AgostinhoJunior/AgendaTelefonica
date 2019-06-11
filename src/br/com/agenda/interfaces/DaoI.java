/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agenda.interfaces;

import java.util.List;

/**
 *
 * @author ADJ-PC
 */
public interface DaoI<E> {

    public int inserir(E obj);

    public boolean alterar(E obj);

    public boolean deletar(E obj);

    public boolean deletar(int id);

    public boolean desativar(E obj);

    public boolean desativar(int id);

    public List<E> pesquisar();

    public List<E> pesquisar(String termo);

    public E pesquisar(int id);

}
