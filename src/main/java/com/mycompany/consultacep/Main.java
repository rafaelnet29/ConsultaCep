package com.mycompany.consultacep;

import javax.swing.JOptionPane;

public class Main {

    public static void main(String[] args) {
        
       ConsultaCep cep = new ConsultaCep();
       int opc = 0;
        do {
            cep.consultarCep();
            opc = Integer.parseInt(JOptionPane.showInputDialog("Deseja consultar outro CEP? 1 - SIM ou 0 - NÃO"));
        } while (opc != 0);
      
    }
}
