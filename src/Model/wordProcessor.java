/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author rizzauliarahman
 */
public class wordProcessor {
    int token, ci;
    char cc;
    String pitaChar; // menampung lexic yang akan dicek token-nya
    
    // constructor objek yang akan mengisi pitaChar dengan string s yg ada di parameter
    public wordProcessor (String s) {
        pitaChar = s;
    }
    
    // menginisialisasi pembacaan karakter di karakter paling pertama
    public void start() {
        ci = 0;
        cc = pitaChar.charAt(ci);
    }
    
    // memindahkan pembacaan karakter ke karakter selanjutnya
    public void adv() {
        if (ci < pitaChar.length()-1) {
            ci++;
            cc = pitaChar.charAt(ci);            
        }
    }
    
    // mengecek apakah lexic tersebut merupakan lexic "if"/"iff"
    public int checkIf() {
        token = 0;
        start();
        if (pitaChar.length() > 3) {
            token = 0;
        } else {
            if (Character.toUpperCase(cc) == 'I') {
                adv();
                if (Character.toUpperCase(cc) == 'F') {
                    token = 6;
                    if (ci < pitaChar.length()-1) {
                        adv();
                        if (Character.toUpperCase(cc) == 'F') {
                            token = 8;
                        } else {
                            token = 0;
                        }
                    }
                }
            }
        }
        return token;
    }
    
    // mengecek apakah lexic tersebut merupakan lexic "and"
    public int checkAnd() {
        token = 0;
        start();
        if (pitaChar.length() != 3) {
            token = 0;
        } else {
            if (Character.toUpperCase(cc) == 'A') {
                adv();
                if (Character.toUpperCase(cc) == 'N') {
                    adv();
                    if (Character.toUpperCase(cc) == 'D') {
                        token = 3;
                    }
                }
            }
        }
        return token;
    }
    
    // mengecek apakah lexic tersebut merupakan lexic "not"
    public int checkNot() {
        token = 0;
        start();
        if (pitaChar.length() != 3) {
            token = 0;
        } else {
            if (Character.toUpperCase(cc) == 'N') {
                adv();
                if (Character.toUpperCase(cc) == 'O') {
                    adv();
                    if (Character.toUpperCase(cc) == 'T') {
                        token = 2;
                    }
                }
            }
        }
        return token;
    }

    // mengecek apakah lexic tersebut merupakan lexic "or"
    public int checkOr() {
        token = 0;
        start();
        if (pitaChar.length() != 2) {
            token = 0;
        } else {
            if (Character.toUpperCase(cc) == 'O') {
                adv();
                if (Character.toUpperCase(cc) == 'R') {
                    token = 4;
                }
            }
        }
        return token;
    }
    
    // mengecek apakah lexic tersebut merupakan lexic "then"
    public int checkThen() {
        token = 0;
        start();
        if (pitaChar.length() != 4) {
            token = 0;
        } else {
            if (Character.toUpperCase(cc) == 'T') {
                adv();
                if (Character.toUpperCase(cc) == 'H') {
                    adv();
                    if (Character.toUpperCase(cc) == 'E') {
                        adv();
                        if (Character.toUpperCase(cc) == 'N') {
                            token = 7;
                        }
                    }
                }
            }
        }
        return token;
    }
    
    // mengecek apakah lexic tersebut merupakan lexic "xor"
    public int checkXor() {
        token = 0;
        start();
        if (pitaChar.length() != 3) {
            token = 0;
        } else {
            if (Character.toUpperCase(cc) == 'X') {
                adv();
                if (Character.toUpperCase(cc) == 'O') {
                    adv();
                    if (Character.toUpperCase(cc) == 'R') {
                        token = 5;
                    }
                }
            }
        }
        return token;
    }
    
    // mengecek apakah lexic tersebut merupakan lexic proposisi (p,q,r,s)
    public int checkProposisi() {
        token = 0;
        List<Character> prop = new ArrayList<>();
        prop.add('P');
        prop.add('Q');
        prop.add('R');
        prop.add('S');
        boolean check;
        start();
        if (pitaChar.length() != 1) {
            token = 0;
        } else {
            if (prop.contains(Character.toUpperCase(pitaChar.charAt(0)))) {
                token = 1;
            } 
        }
        return token;
    }
    
    // mengecek apakah lexic tersebut merupakan tanda kurung ('('/')')
    public int checkBracket() {
        token = 0;
        start();
        if (pitaChar.length() != 1) {
            token = 0;
        } else {
            if (cc == '(') {
                token = 9;
            } else if (cc == ')') {
                token = 10;
            }
        }
        return token;
    }
    
    // mencocokkan lexic dalam pitaChar dengan setiap lexic yang mungkin
    public List<Integer> checkWord() {
        // list utk menampung token-token hasil pencocokan
        List<Integer> arrToken = new ArrayList<>();
        arrToken.add(checkAnd());
        arrToken.add(checkBracket());
        arrToken.add(checkIf());
        arrToken.add(checkNot());
        arrToken.add(checkOr());
        arrToken.add(checkProposisi());
        arrToken.add(checkThen());
        arrToken.add(checkXor());
        return arrToken;
    }
    
}
