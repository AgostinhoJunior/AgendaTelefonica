/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.agenda.main;

import br.com.agenda.view.TelaPrincipal;
import javax.swing.JFrame;

/**
 *
 * @author ADJ-PC
 */
public class principal {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TelaPrincipal telaPrincipal;
        telaPrincipal = new TelaPrincipal();
        telaPrincipal.setTitle("AGENDA TELEFONICA!");
        telaPrincipal.setLocationRelativeTo(null);
        telaPrincipal.setExtendedState(JFrame.MAXIMIZED_BOTH);
        telaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telaPrincipal.setVisible(true);
    }

}
