/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agenda.control;

import br.com.agenda.model.TipoContato;
import br.com.agenda.model.dao.TipoContatoDao;
import br.com.agenda.model.tablemodel.TipoContatoTableModel;
import br.com.agenda.uteis.Mensagem;
import br.com.agenda.uteis.Texto;
import br.com.agenda.uteis.UtilTable;
import br.com.agenda.view.TelaGerenciarTipoContato;
import br.com.agenda.view.TelaPrincipal;
import javax.swing.JOptionPane;

/**
 *
 * @author ADJ-PC
 */
public class TelaTipoContatoControl {

    private TelaGerenciarTipoContato telaGerenciarTipoContato = null;
    private TipoContatoTableModel tipoContatoTableModel;
    private TipoContatoDao tipoContatoDao;
    private TipoContato tipoContato;

    public TelaTipoContatoControl() {
        tipoContatoDao = new TipoContatoDao();
        tipoContatoTableModel = new TipoContatoTableModel();
    }

    public void chamarTelaGerenciarTipoContato() {
        if (telaGerenciarTipoContato == null) { // se tiver nulo chama janela normalmente
            telaGerenciarTipoContato = new TelaGerenciarTipoContato(this);
            TelaPrincipal.desktopPane.add(telaGerenciarTipoContato);
            telaGerenciarTipoContato.setVisible(true);
        } else {//se ele estiver criado
            if (telaGerenciarTipoContato.isVisible()) {
                telaGerenciarTipoContato.pack();//Redimensiona ao Quadro Original
            } else {
                TelaPrincipal.desktopPane.add(telaGerenciarTipoContato);
                telaGerenciarTipoContato.setVisible(true);
            }

        }
        telaGerenciarTipoContato.getTblTipoContato().setModel(tipoContatoTableModel);
        redimensionarTela();
        tipoContatoTableModel.limpar();
        tipoContatoTableModel.adicionar(tipoContatoDao.pesquisar());
    }

    private void redimensionarTela() {
        UtilTable.centralizarCabecalho(telaGerenciarTipoContato.getTblTipoContato());
        UtilTable.redimensionar(telaGerenciarTipoContato.getTblTipoContato(), 0, 50);
        UtilTable.redimensionar(telaGerenciarTipoContato.getTblTipoContato(), 1, 350);
        UtilTable.redimensionar(telaGerenciarTipoContato.getTblTipoContato(), 2, 50);
    }

    private void cadastrarTipoContato() {
        if (validarCampos()) {
            Mensagem.info(Texto.ERRO_CADASTRAR);
            return;
        }
        tipoContato = new TipoContato();
        tipoContato.setId(Integer.MAX_VALUE);
        tipoContato.setNome(telaGerenciarTipoContato.getTfNome().getText());
        if (telaGerenciarTipoContato.getCheckAtivo().isSelected()) {
            tipoContato.setAtivo(true);
        } else {
            tipoContato.setAtivo(false);
        }

        Integer idInserido = tipoContatoDao.inserir(tipoContato);
        if (idInserido != 0) {
            tipoContato.setId(idInserido);
            tipoContatoTableModel.adicionar(tipoContato);
            limparCampos();
            Mensagem.info(Texto.SUCESSO_CADASTRAR);
        } else {
            Mensagem.info(Texto.ERRO_CADASTRAR);
        }
        tipoContato = null;
    }

    private void limparCampos() {
        telaGerenciarTipoContato.getTfNome().setText("");
        telaGerenciarTipoContato.getTfPesquisa().setText("");
        telaGerenciarTipoContato.getCheckAtivo().setSelected(false);
        telaGerenciarTipoContato.getTfNome().requestFocus();
    }

    private boolean validarCampos() {
        if (telaGerenciarTipoContato.getTfNome().getText().isEmpty()) {
            telaGerenciarTipoContato.getTfNome().requestFocus();
            return true;
        }
        return false;
    }
}
