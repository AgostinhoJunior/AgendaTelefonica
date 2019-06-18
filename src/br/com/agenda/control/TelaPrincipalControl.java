/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agenda.control;

import br.com.agenda.view.TelaPrincipal;
import javax.swing.JFrame;

/**
 *
 * @author ADJ-PC
 */
public class TelaPrincipalControl {

    TelaPrincipal telaPrincipal;
    private TelaTipoContatoControl telaTipoContatoControl = null;
    private TelaContatoControl telaContatoControl = null;

    public TelaPrincipalControl() {
    }

    public void chamarTelaPrincipal() {
        telaPrincipal = new TelaPrincipal();
        telaPrincipal.setTitle("AGENDA TELEFONICA!");
        telaPrincipal.setLocationRelativeTo(null);
        telaPrincipal.setExtendedState(JFrame.MAXIMIZED_BOTH);
        telaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telaPrincipal.setVisible(true);
    }

    public void chamarTelaTipoContatoAction() {
        if (telaTipoContatoControl != null) {
            telaTipoContatoControl.chamarTelaGerenciarTipoContato();
        } else {
            telaTipoContatoControl = new TelaTipoContatoControl();
            telaTipoContatoControl.chamarTelaGerenciarTipoContato();
        }
    }

    public void chamarTelaContatoAction() {
        if (telaContatoControl != null) {
            telaContatoControl.chamarTelaGerenciarTipoContato();
        } else {
            telaContatoControl = new TelaContatoControl();
            telaContatoControl.chamarTelaGerenciarTipoContato();
        }
    }
}
