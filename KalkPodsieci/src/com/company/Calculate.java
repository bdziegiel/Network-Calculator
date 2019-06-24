package com.company;

import org.omg.PortableInterceptor.INACTIVE;

import java.util.Arrays;

public class Calculate {

    public Integer[] netAddress = new Integer[32];
    public Integer[] brodAddress = new Integer[32];

    void netAddress(Integer[] binMask, Integer[] binIP) {
        for (int i = 0; i < 32; i++) {
            if (binMask[i] == 1)
                netAddress[i] = binIP[i];
            else netAddress[i] = 0;
        }
    }

    void broadcastAddress(Integer[] binMask, Integer[] binIP) {
        for (int i = 0; i < 32; i++) {
            if (binMask[i] == 1)
                brodAddress[i] = binIP[i];
            else brodAddress[i] = 1;
        }
    }

     String classType(String[] string){
        int temp = Integer.valueOf(string[0]);
            if(temp>=0 && temp <=127) return ("A");
            else if(temp>=128 && temp <=191) return ("B");
            else return ("C");
        }

    boolean isAddressPrivate(String[] tabString, String string){
        if(string.equals("A")) {
            if(tabString[0]=="10") return true;
        }
        else if(string.equals("B")){
            if((tabString[0]=="172") && (Integer.valueOf(tabString[1]) >=16) &&
                    (Integer.valueOf(tabString[1])<=31) &&
                    tabString[3]=="0" && tabString[0] == "0")
                return true;
        }
        else if((tabString[0]=="192") && (tabString[1]=="168") &&
                    (Integer.valueOf(tabString[2]) >=0) &&
                    (Integer.valueOf(tabString[2])<=255) &&
                    tabString[3] == "0")
            return true;

        return false;
    }

        //first host
    Integer[] firstHost(Integer[]netAddress){
        Integer[] str=HelpMethods.decTab(netAddress);
        int temp = str[3]+1;
        str[3] = temp;
        return str;
    }

        //last host
     Integer[] lastHost(Integer[]brodAddress){
        Integer[] str=HelpMethods.decTab(brodAddress);
        int temp = str[3]-1;
        str[3] = temp;
        return str;
    }

        //max hosts
    int sumOfHosts(Integer[] firstHost, Integer[] lastHost, Integer mask){
        int sum = (int)Math.pow(2,32-mask)-2;
        return sum;
    }

}
