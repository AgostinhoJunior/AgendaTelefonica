/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agenda.model.tablemodel;

import br.com.agenda.interfaces.AcoesTableModel;
import br.com.agenda.model.TipoContato;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ADJ-PC
 */
public class TipoContatoTableModel extends AbstractTableModel implements AcoesTableModel<TipoContato> {

    private static final int CODIGO = 0;
    private static final int NOME = 1;
    private static final int ATIVO = 2;

    private List<TipoContato> linhas;
    private String[] COLUNAS = {"Código", "Nome", "Ativo"};

    public TipoContatoTableModel() {
        linhas = new ArrayList<>();
    }

    public TipoContatoTableModel(List<TipoContato> listTipoContato) {
        linhas = new ArrayList<>(listTipoContato);
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
            case ATIVO:
                return String.class;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        TipoContato tipoContato = linhas.get(linha);
        switch (coluna) {
            case CODIGO:
                return tipoContato.getId();
            case NOME:
                return tipoContato.getNome();
            case ATIVO:
                if (tipoContato.getAtivo() == true) {
                    return "Ativado";
                } else {
                    return "Desativado";
                }

            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");
        }
    }

    @Override
    public void setValueAt(Object valor, int linha, int coluna) {
        TipoContato tipoContato = linhas.get(linha);
        switch (coluna) {
            case CODIGO:
                tipoContato.setId(Integer.valueOf((String) valor));
                break;
            case NOME:
                tipoContato.setNome((String) valor);
                break;
            case ATIVO:
                if (valor.equals("Ativado")) {
                    tipoContato.setAtivo(true);
                } else {
                    tipoContato.setAtivo(false);
                }
                ;
                break;
            default:
                throw new IndexOutOfBoundsException("columnIndex out of bounds");

        }
        this.fireTableCellUpdated(linha, coluna); // Atualiza Celula da tabela

    }

    @Override
    public TipoContato pegaObjeto(int indiceLinha) {
        return linhas.get(indiceLinha);
    }

    @Override
    public void adicionar(TipoContato tipoContato) {
        linhas.add(tipoContato);
        int ultimoIndice = getRowCount() - 1; // linhas -1
        fireTableRowsInserted(ultimoIndice, ultimoIndice); // atualiza insert
    }

    @Override
    public void adicionar(List<TipoContato> tipoContato) {
        int indice = getRowCount();
        linhas.addAll(tipoContato);
        fireTableRowsInserted(indice, indice + tipoContato.size());
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
    public void atualizar(int indiceLinha, TipoContato tipoContato) {
        linhas.set(indiceLinha, tipoContato);
        fireTableRowsUpdated(indiceLinha, indiceLinha); // atualiza delete
    }

    @Override
    public void limpar() {
        linhas.clear();
        fireTableDataChanged(); // atualiza toda tabela.
    }

}
