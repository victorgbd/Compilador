package main;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import javax.swing.SwingUtilities;


public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        SwingUtilities.invokeLater(() -> {
            try {
                //UIManager.setLookAndFeel(new QuaquaLookAndFeel());
            } catch (Exception e) {
            }
            new Menu().setVisible(true);
        });
    }
}