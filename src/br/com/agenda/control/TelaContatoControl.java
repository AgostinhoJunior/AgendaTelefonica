/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agenda.control;

import br.com.agenda.model.Contato;
import br.com.agenda.model.Telefone;
import br.com.agenda.model.TipoContato;
import br.com.agenda.model.dao.ContatoDao;
import br.com.agenda.model.dao.TelefoneDao;
import br.com.agenda.model.dao.TipoContatoDao;
import br.com.agenda.model.tablemodel.ContatoTableModel;
import br.com.agenda.model.tablemodel.TelefoneTableModel;
import br.com.agenda.uteis.Mensagem;
import br.com.agenda.uteis.Texto;
import br.com.agenda.uteis.UtilDate;
import br.com.agenda.view.TelaContatoGerenciar;
import br.com.agenda.view.TelaPrincipal;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;

/**
 *
 * @author ADJ-PC
 */
public class TelaContatoControl {

    private TelaContatoGerenciar telaContatoGerenciar = null;
    private TelefoneTableModel telefoneTableModel;
    private ContatoTableModel contatoTableModel;
    private TelefoneDao telefoneDao;
    private ContatoDao contatoDao;
    private Telefone telefone;
    private Contato contato;
    private TipoContato tipoContato;
    private Integer linhaSelecionada;
    private List<TipoContato> listTipoContatos;
    private List<Telefone> listTelefones;
    private TipoContatoDao tipoContatoDao;

    public TelaContatoControl() {
        telefoneDao = new TelefoneDao();
        tipoContatoDao = new TipoContatoDao();
        contatoDao = new ContatoDao();
        telefoneTableModel = new TelefoneTableModel();
        contatoTableModel = new ContatoTableModel();
        listTelefones = new ArrayList<>();
    }

    public void chamarTelaGerenciarTipoContato() {
        if (telaContatoGerenciar == null) { // se tiver nulo chama janela normalmente
            telaContatoGerenciar = new TelaContatoGerenciar(this);
            TelaPrincipal.desktopPane.add(telaContatoGerenciar);
            telaContatoGerenciar.setVisible(true);
        } else {//se ele estiver criado
            if (telaContatoGerenciar.isVisible()) {
                telaContatoGerenciar.pack();//Redimensiona ao Quadro Original
            } else {
                TelaPrincipal.desktopPane.add(telaContatoGerenciar);
                telaContatoGerenciar.setVisible(true);
            }
        }
        carregarTipoContatoNaCombo();
        telaContatoGerenciar.getTblContato().setModel(contatoTableModel);
        telaContatoGerenciar.getTblTelefone().setModel(telefoneTableModel);
        contatoTableModel.limpar();
        contatoTableModel.adicionar(contatoDao.pesquisar());
    }

    private void carregarTipoContatoNaCombo() {
        listTipoContatos = tipoContatoDao.pesquisar();
        DefaultComboBoxModel<TipoContato> model = new DefaultComboBoxModel(listTipoContatos.toArray());
        telaContatoGerenciar.getCbTipoContato().setModel(model);
    }

    public void adicionarTelefoneAction() {
        telefone = new Telefone();
        telefone.setId(1);
        telefone.setDdd(Integer.valueOf(telaContatoGerenciar.getTfDdd().getText()));
        telefone.setNumero(Integer.valueOf(telaContatoGerenciar.getTfNumero().getText()));
        listTelefones.add(telefone);
        telefoneTableModel.adicionar(telefone);
        telaContatoGerenciar.getTfDdd().setText(null);
        telaContatoGerenciar.getTfNumero().setText(null);
        telefone = null;
    }

    public void removeTelefoneAction() throws Exception {
        int retorno = Mensagem.confirmacao(Texto.PERGUNTA_REMOVER_TELEFONE);
        if (retorno == JOptionPane.NO_OPTION) {
            return;
        }
        telefone = telefoneTableModel.pegaObjeto(telaContatoGerenciar.getTblTelefone().getSelectedRow());
        telefoneDao.deletar(telefone);
        listTelefones.remove(telefone);
        telefoneTableModel.remover(telaContatoGerenciar.getTblTelefone().getSelectedRow());
        Mensagem.info(Texto.SUCESSO_REMOVER);
        telefone = null;

    }

    private void cadastrarContato() throws Exception {
        contato = new Contato();
        contato.setEmail(telaContatoGerenciar.getTfEmail().getText());
        contato.setNascimento(UtilDate.dataUsuarioParaBanco(telaContatoGerenciar.getTfNascimento().getText()));
        contato.setNome(telaContatoGerenciar.getTfNome().getText());
        contato.setTipoContato((TipoContato) telaContatoGerenciar.getCbTipoContato().getSelectedItem());
        contato.setTelefone(listTelefones);
        if (telaContatoGerenciar.getCheckAtivo().isSelected()) {
            contato.setAtivo(true);
        } else {
            contato.setAtivo(false);
        }
        Integer idInserido = contatoDao.inserir(contato);
        if (idInserido != 0) {
            contato.setId(idInserido);
            contatoTableModel.adicionar(contato);
            Mensagem.info(Texto.SUCESSO_CADASTRAR);
        }
        for (int i = 0; i < listTelefones.size(); i++) {
            Telefone umTelefoneEntrada = listTelefones.get(i);
            umTelefoneEntrada.setContato(contato);
            umTelefoneEntrada.setContato(contato);
            telefoneDao.inserir(umTelefoneEntrada);
        }
        limparCampos();
        contato = null;
    }

    public void carregaContatoAction() throws Exception {
        contato = contatoTableModel.pegaObjeto(telaContatoGerenciar.getTblContato().getSelectedRow());
        telaContatoGerenciar.getTfNome().setText(contato.getNome());
        telaContatoGerenciar.getTfEmail().setText(contato.getEmail());
        telaContatoGerenciar.getTfNascimento().setText(UtilDate.data(contato.getNascimento()));
        telefoneTableModel.adicionar(telefoneDao.pesquisarTelefoneContatos(contato));
        if (contato.getAtivo() == true) {
            telaContatoGerenciar.getCheckAtivo().setSelected(true);
        } else {
            telaContatoGerenciar.getCheckAtivo().setSelected(false);
        }

        telaContatoGerenciar.getTpContato().setEnabledAt(0, false); // disabilita o tabbed pane
        telaContatoGerenciar.getTpContato().setSelectedIndex(1); // seleciona o tabbed pane
        telaContatoGerenciar.getTfNome().requestFocus();

    }

    public void gravarContatoAction() throws Exception {
        if (contato == null) {
            cadastrarContato();
            telaContatoGerenciar.getTpContato().setSelectedIndex(0); // seleciona o tabbed pane
        } else {
            alterarContato();
            telaContatoGerenciar.getTpContato().setEnabledAt(0, true);
            telaContatoGerenciar.getTpContato().setSelectedIndex(0);
        }
    }

    private void alterarContato() {
        contato.setEmail(telaContatoGerenciar.getTfEmail().getText());
        contato.setNascimento(UtilDate.dataUsuarioParaBanco(telaContatoGerenciar.getTfNascimento().getText()));
        contato.setNome(telaContatoGerenciar.getTfNome().getText());
        contato.setTipoContato((TipoContato) telaContatoGerenciar.getCbTipoContato().getSelectedItem());
        contato.setTelefone(listTelefones);
        if (telaContatoGerenciar.getCheckAtivo().isSelected()) {
            contato.setAtivo(true);
        } else {
            contato.setAtivo(false);
        }

        boolean alterado = contatoDao.alterar(contato);
        linhaSelecionada = telaContatoGerenciar.getTblContato().getSelectedRow();
        if (alterado) {
            contatoTableModel.atualizar(linhaSelecionada, contato);
            Mensagem.info(Texto.SUCESSO_EDITAR);
        } else {
            Mensagem.erro(Texto.ERRO_EDITAR);
        }
        for (int i = 0; i < listTelefones.size(); i++) {
            Telefone umTelefoneEntrada = listTelefones.get(i);
            umTelefoneEntrada.setContato(contato);
            telefoneDao.inserir(umTelefoneEntrada);
        }

        limparCampos();
        contato = null;
    }

    public void desativarContatoAction() {
        int retorno = Mensagem.confirmacao(Texto.PERGUNTA_DESATIVAR);
        if (retorno == JOptionPane.NO_OPTION) {
            return;
        }
        if (retorno == JOptionPane.CLOSED_OPTION) {
            return;
        }
        contato = contatoTableModel.pegaObjeto(telaContatoGerenciar.getTblContato().getSelectedRow());
        boolean deletado = contatoDao.desativar(contato);
        if (deletado) {
            contatoTableModel.remover(telaContatoGerenciar.getTblContato().getSelectedRow());
            telaContatoGerenciar.getTblContato().clearSelection();
            Mensagem.info(Texto.SUCESSO_DESATIVAR);
        } else {
            Mensagem.erro(Texto.ERRO_DESATIVAR);
        }
        contato = null;
    }

    public void pesquisarContatoAction() {
        List<Contato> contatosPesquisados = contatoDao.pesquisar(telaContatoGerenciar.getTfPesquisa().getText());
        if (contatosPesquisados == null) {
            contatoTableModel.limpar();
            contatosPesquisados = contatoDao.pesquisar();
        } else {
            contatoTableModel.limpar();
            contatoTableModel.adicionar(contatosPesquisados);
        }

    }

    private void limparCampos() {
        telaContatoGerenciar.getTfDdd().setText(null);
        telaContatoGerenciar.getTfEmail().setText(null);
        telaContatoGerenciar.getTfNascimento().setText(null);
        telaContatoGerenciar.getTfNome().setText(null);
        telaContatoGerenciar.getTfNumero().setText(null);
        telaContatoGerenciar.getTfPesquisa().setText(null);
        telaContatoGerenciar.getCheckAtivo().setSelected(false);
        telaContatoGerenciar.getCbTipoContato().setSelectedIndex(0);
        telefoneTableModel.limpar();
    }
}
