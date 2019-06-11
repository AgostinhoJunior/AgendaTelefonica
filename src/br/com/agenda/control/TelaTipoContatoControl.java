/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agenda.control;

import br.com.agenda.view.TelaGerenciarTipoContato;
import br.com.agenda.view.TelaPrincipal;

/**
 *
 * @author ADJ-PC
 */
public class TelaTipoContatoControl {

    TelaGerenciarTipoContato telaGerenciarTipoContato = null;

    public TelaTipoContatoControl() {
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
    }
}
