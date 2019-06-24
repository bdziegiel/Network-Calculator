package com.company;


public class Main {

    public static void main(String[] args) {
        Split toSplit = new Split();
        String address;


        //jeśli nie podano adresu - pobieram lokalny
        if (args.length==0) {
            address = toSplit.getLocalAddress();
        }
        else  address = args[0];

        //jesli adres jest ok - wykonuje obliczenia, jesli nie - koniec programu
        if(toSplit.addressSplit(address)){

            toSplit.convert();
            HelpMethods.print("IP",toSplit.IP,toSplit.binIP);
            HelpMethods.print("\n\nMASK", String.valueOf(toSplit.mask), toSplit.binMask);

            Calculate calc = new Calculate();

            //network address
            calc.netAddress(toSplit.binMask, toSplit.binIP);
            HelpMethods.printData("\n\nNetwork Address",calc.netAddress);

            //broadcast address
            calc.broadcastAddress(toSplit.binMask, toSplit.binIP);
            HelpMethods.printData("\n\nBroadcast Address", calc.brodAddress);

            //class
            String type = calc.classType(toSplit.decIP);
            System.out.println("\n\nClass: "+type);

            //public/private
            System.out.println("\n\nPublic or private?");
            boolean priv = calc.isAddressPrivate(toSplit.decIP,type);
            if(priv) System.out.println("\nPrivate");
            else System.out.println("\nPublic");

            //first host
            System.out.println("\n\nFirst host: ");
            Integer[] first = calc.firstHost(calc.netAddress);
            HelpMethods.printDec(first);

            //last host
            System.out.println("\n\nLast host: ");
            Integer[] last = calc.lastHost(calc.brodAddress);
            HelpMethods.printDec(last);

            //max hosts
                System.out.println("\n\nNumber of hosts: ");
                System.out.println(calc.sumOfHosts(first,last,toSplit.mask));

            }
        }
    }



