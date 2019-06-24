package com.company;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.regex.Pattern;

public class Split {

    public String IP;
    public Integer mask; //int maska
    public String[] decIP = new String[4];
    public String []b = new String[4];
    public String ip = ""; //koncowy string zawierajacy bin ip

    public Integer[] binIP = new Integer[32]; // tablica bin ip
    public Integer[] binMask = new Integer[32]; //zapis binarny maski w tablicy
    public InetAddress myAddress;

    public String getLocalAddress() {
        try {
            myAddress = InetAddress.getLocalHost();
            String localIP = String.valueOf(myAddress.getHostAddress());

            try {
                NetworkInterface networkInterface = NetworkInterface.getByInetAddress(myAddress);
                String localMask = String.valueOf(networkInterface.getInterfaceAddresses().get(0).getNetworkPrefixLength());

                return localIP + "/" + localMask;

            } catch (SocketException e) {
                System.err.println(e);
            }

        } catch (UnknownHostException e) {
            System.err.println(e);
        } return null;
    }

    boolean addressSplit (String address) {
        String[] a = address.split("/");
        mask = Integer.valueOf(a[1]);
        IP = String.valueOf(a[0]);

        //check mask
        try {
            if (mask < 0 || mask > 32)
                System.err.println("Wrong mask!");
        } catch (IllegalArgumentException e) {
            System.err.println(e.getMessage());
            return false;
        }

        decIP = a[0].split(Pattern.quote("."));

        //check IP
        for (int i = 0; i < 4; i++) {
            try {
                if (Integer.valueOf(decIP[i]) < 0 || Integer.valueOf(decIP[i]) > 255)
                    System.err.println("Wrong IP format!");
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
                return false;
            }
        }return true;
    }

    void convert(){
        //convert string mask to binary
        for (int i = 0; i < 32; i++) {
            if (i < mask) binMask[i] = 1;
            else binMask[i] = 0;
        }

        //convert every string part ip to binary
        for (int i = 0; i < 4; i++) {
            b[i]=Integer.toBinaryString(0x100 + Integer.valueOf(decIP[i])).substring(1);
        }

        ip = String.join("",b);

        //split string ip after every number
        String[] tab = ip.split("");

        //split every part of string ip, convert to int
        for (int i = 0; i < tab.length; i++)
            binIP[i] = Integer.valueOf(tab[i]);
        }
}
