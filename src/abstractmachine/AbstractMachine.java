/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package abstractmachine;

import Model.stringProcessor;
import java.util.Scanner;

/**
 *
 * @author rizzauliarahman
 */
public class AbstractMachine {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        stringProcessor sp = new stringProcessor(); // menginisialisasi objek stringProcessor
        Scanner kalimat = new Scanner(System.in);
        System.out.println("Masukkan formula : ");
        String kal = kalimat.nextLine(); // input formula yg akan dianalisa
        sp.getKata(kal); // memisah lexic-lexic yg ada dalam formula
        sp.formatList(); // mengecek ulang lexic-lexic hasil pemisahan
        sp.abstraksi(); // mencari nilai token dari masing-masing lexic
        sp.showList(); // menampilkan lexic-lexic hasil pemisahan dan nilai token-nya masing-masing
    }
    
}
