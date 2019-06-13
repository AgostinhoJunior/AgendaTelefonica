/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agenda.main;

import br.com.agenda.control.TelaPrincipalControl;
import br.com.agenda.uteis.InterfaceJanela;
import javax.swing.JOptionPane;

/**
 *
 * @author ADJ-PC
 */
public class principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        try {
            InterfaceJanela.MudaSwingParaPadraoDoSO();
        } catch (Exception exception) {
            JOptionPane.showMessageDialog(null, "ERRO");
        }
        TelaPrincipalControl telaPrincipal = new TelaPrincipalControl();
        telaPrincipal.chamarTelaPrincipal();

    }

}
