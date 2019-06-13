/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agenda.uteis;

import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author agostinho.junior
 */
public class UtilTable {

    /**
     * Redefinir largura da tabela
     *
     * @param tableModel
     * @param coluna
     * @param largura
     */
    public static void redimensionar(JTable tableModel, Integer coluna, Integer largura) {
        tableModel.setAutoResizeMode(tableModel.AUTO_RESIZE_OFF);
        tableModel.getColumnModel().getColumn(coluna).setPreferredWidth(largura);
    }

    /**
     * Centralizar o cabecalho da tabela
     *
     * @param tableModel
     */
    public static void centralizarCabecalho(JTable tableModel) {
        ((DefaultTableCellRenderer) tableModel.getTableHeader().getDefaultRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
    }

    /**
     * Limpar linha selecionada na tabela
     *
     * @param tableModel
     */
    public static void limparSelecaoDaTabela(JTable tableModel) {
        tableModel.getSelectionModel().clearSelection();
    }
}
