/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agenda.model.tablemodel;

import br.com.agenda.interfaces.AcoesTableModel;
import br.com.agenda.model.Telefone;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ADJ-PC
 */
public class TelefoneTableModel extends AbstractTableModel implements AcoesTableModel<Telefone> {

    private static final int DDD = 0;
    private static final int NUMERO = 1;

    private List<Telefone> linhas;
    private String[] COLUNAS = {"DDD", "Numero"};

    public TelefoneTableModel() {
        linhas = new ArrayList<>();
    }

    public TelefoneTableModel(List<Telefone> listTelefone) {
        linhas = new ArrayList<>(listTelefone);
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
            case DDD:
                return Integer.class;
            case NUMERO:
                return Integer.class;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        Telefone telefone = linhas.get(linha);
        switch (coluna) {
            case DDD:
                return telefone.getDdd();
            case NUMERO:
                return telefone.getNumero();
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public Telefone pegaObjeto(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    @Override
    public void adicionar(Telefone obj) {
        linhas.add(obj);
        int ultimoIndice = getRowCount() - 1; // linhas -1
        fireTableRowsInserted(ultimoIndice, ultimoIndice); // atualiza insert
    }

    @Override
    public void adicionar(List<Telefone> obj) {
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
    public void atualizar(int indiceLinha, Telefone obj) {
        linhas.set(indiceLinha, obj);
        fireTableRowsUpdated(indiceLinha, indiceLinha); // atualiza delete
    }

    @Override
    public void limpar() {
        linhas.clear();
        fireTableDataChanged(); // atualiza toda tabela.
    }

}
