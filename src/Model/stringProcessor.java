/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.awt.image.SampleModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *
 * @author rizzauliarahman
 */
public class stringProcessor {
    List<String> str; //list untuk menampung lexic-lexic yang ada dalam formula
    List<Integer> tokenList = new ArrayList<>(); // list untuk menampung nilai token dari masing-masing lexic
    
    // mengosongkan isi dari list str
    public void resetList() {
        str = new ArrayList();
    }
    
    // mengambil lexic-lexic yang ada dalam suatu formula
    public void getKata(String s) {
        resetList();
        int start = 0, end;
        s = s.trim(); // menghapus spasi yg mungkin ada di awal dan akhir formula
        for (int i = 0; i < s.length(); i++) {
            // kondisi utk mngubah posisi awal kata yg dibaca
            if ((i == 0) || (s.charAt(i-1) == ' ') || (s.charAt(i-1) == '(')) {
                start = i;
            }
            // kondisi utk menambahkan tanda kurung ke dalam list str dan mengubah posisi start
            if ((s.charAt(i) == '(') || (s.charAt(i) == ')')) {
                str.add(s.substring(i, i+1));
                start = i+1;
            }
            // kondisi utk memasukkan lexic ke dalam list apabila sudah di akhir kalimat atau ditutup oleh tutup kurung
            else if ((i == s.length()-1) || (s.charAt(i+1) == ')')) {
                end = i+1;
                str.add(s.substring(start, end));
            // kondisi utk menambahkan lexic sebelum spasi yg sedang dibaca ke dalam list str
            } else if ((s.charAt(i) == ' ')) {
                end = i;
                str.add(s.substring(start, end));
            }
        }
    }
    
    // pengecekan ulang elemen-elemen yang ada di dalam list str
    public void formatList() {
        for (int i = 0; i < str.size(); i++) {
            // kondisi apabila terdapat elemen yg berisi tanda spasi ataupun elemen kosong
            if ((str.get(i).equals(" ")) || (str.get(i).equals(""))) {
                str.remove(i);
            }
        }
    }
    
    // mencari nilai token dari masing-masing lexic yang ada di list str
    public void abstraksi() {
        // list utk menampung hasil pencocokan elemen dengan setiap lexic yg mungkin
        List<Integer> arrToken = new ArrayList<>();
        for (String s : str) { 
            s = s.trim(); // menghapus spasi yg mungkin ada di awal atau di akhir lexic
            wordProcessor wp = new wordProcessor(s);
            arrToken = wp.checkWord();
            int i = 0;
            // perulangan untuk mencari token hasil pencocokan yg tidak sama dengan 0
            while ((i < arrToken.size()-1) && (arrToken.get(i) == 0)) {
                i++;
            }
            // menambahkan token ke dalam list tokenList apabila tidak sama dengan 0
            if (arrToken.get(i) != 0) {
                tokenList.add(arrToken.get(i));
            } else {
                // menambahkan 0 ke dalam list tokenList apabila semua token bernilai 0
                if (i == arrToken.size()-1) {
                    tokenList.add(arrToken.get(i));
                }
            }
        }
    }
    
    // menampilkan lexic-lexic yg ada di dalam formula
    // menampilkan nilai token dari tiap lexic yg ada
    public void showList() {
        // perulangan menampilkan tiap elemen dalam list str
        for (String s : str) {
            System.out.print(s+" ");
        }
        System.out.println(" ");
        int i = 0;
        // menampilkan token dalam list tokenList selama token masih bernilai tidak sama dengan 0
        while ((i < tokenList.size()-1) && (tokenList.get(i) != 0)) {
            System.out.print(tokenList.get(i)+" ");
            i++;
        }
        // menampilkan error apabila token bernilai 0
        if (tokenList.get(i) == 0) {
            System.out.println("ERROR");
        } else {
            System.out.println(tokenList.get(i)+" ");
        }
    }
    
}
