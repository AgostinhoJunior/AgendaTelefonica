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
public interface AcoesTableModel<E> {

    public E pegaObjeto(int indiceLinha);

    public void adicionar(E obj);

    public void adicionar(List<E> obj);

    public void remover(int indiceLinha);

    public void remover(int linhaInicio, int linhaFim);

    public void atualizar(int indiceLinha, E obj);

    public void limpar();

}
