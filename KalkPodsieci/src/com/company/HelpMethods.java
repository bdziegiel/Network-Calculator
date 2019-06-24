package com.company;

import java.io.PrintWriter;

public class HelpMethods {

    static void printData(String name, Integer[] Address) {
        Integer[]tab = decTab(Address);
        System.out.print(name+"\n");

        for (int i = 0, j = 1; i < 32; i++, j++) {
            System.out.print(Address[i]);

            if (j % 8 == 0 && j < 32) {
                System.out.print(".");

            }
        }
    }

    static void print(String name, String string, Integer[] tab){
        System.out.println(name+": \n"+string+"\n");
        for (int i = 0; i < tab.length; i++) {
            System.out.print(tab[i]);
            if((i+1)%8==0 && i+1<32)
                System.out.print(".");
        }
    }

    //convert bin tab to dec tab
    static Integer[] decTab(Integer[] intAddress){
        Integer[] netTab = new Integer[4];

        for(int j=0; j<4; j++) {
            String tempString ="";
            for (int i = j*8; i < (j*8+8); i++) {
                tempString += intAddress[i];
            }

            netTab[j] = Integer.parseInt(tempString, 2);
        }
        return netTab;
    }

    static void printDec(Integer[] tab){
        for (int i=0; i<tab.length;i++) {
            System.out.print(tab[i]);
            if(i<3) System.out.print(".");
        }
    }

}
