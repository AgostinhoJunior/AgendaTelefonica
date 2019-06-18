/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agenda.model.tablemodel;

import br.com.agenda.interfaces.AcoesTableModel;
import br.com.agenda.model.Contato;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ADJ-PC
 */
public class ContatoTableModel extends AbstractTableModel implements AcoesTableModel<Contato> {

    private static final int CODIGO = 0;
    private static final int NOME = 1;
    private static final int EMAIL = 2;
    private static final int NASCIMENTO = 3;
    private static final int ATIVO = 4;

    private List<Contato> linhas;
    private String[] COLUNAS = {"Código", "Nome", "Email", "Nascimento", "Ativo"};

    public ContatoTableModel() {
        linhas = new ArrayList<>();
    }

    public ContatoTableModel(List<Contato> listContatos) {
        linhas = new ArrayList<>(listContatos);
    }

    @Override
    public int getRowCount() {
        return linhas.size();
    }

    @Override
    public int getColumnCount() {
        return COLUNAS.length;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return COLUNAS[columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case CODIGO:
                return String.class;
            case NOME:
                return String.class;
            case EMAIL:
                return String.class;
            case NASCIMENTO:
                return String.class;
            case ATIVO:
                return String.class;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Contato contato = linhas.get(linha);
        switch (coluna) {
            case CODIGO:
                return contato.getId();
            case NOME:
                return contato.getNome();
            case EMAIL:
                return contato.getEmail();
            case NASCIMENTO:
                return contato.getNascimento();
            case ATIVO:
                if (contato.getAtivo() == true) {
                    return "Ativado";
                } else {
                    return "Desativado";
                }

            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public Contato pegaObjeto(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    @Override
    public void adicionar(Contato obj) {
        linhas.add(obj);
        int ultimoIndice = getRowCount() - 1; // linhas -1
        fireTableRowsInserted(ultimoIndice, ultimoIndice); // atualiza insert
    }

    @Override
    public void adicionar(List<Contato> obj) {
        int indice = getRowCount();
        linhas.addAll(obj);
        fireTableRowsInserted(indice, indice + obj.size());
        fireTableDataChanged();
    }

    @Override
    public void remover(int indiceLinha) {
        linhas.remove(indiceLinha);
        fireTableRowsDeleted(indiceLinha, indiceLinha); // atualiza delete
    }

    @Override
    public void remover(int linhaInicio, int linhaFim) {
        for (int i = linhaInicio; i <= linhaFim; i++) {
            linhas.remove(i);
            fireTableRowsDeleted(linhaInicio, linhaFim); // atualiza delete
        }
    }

    @Override
    public void atualizar(int indiceLinha, Contato obj) {
        linhas.set(indiceLinha, obj);
        fireTableRowsUpdated(indiceLinha, indiceLinha); // atualiza delete
    }

    @Override
    public void limpar() {
        linhas.clear();
        fireTableDataChanged(); // atualiza toda tabela.
    }

}
