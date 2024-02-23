package it.Develhope.Team1;

import java.util.Arrays;
import java.util.Objects;

public class Calculator {
    public static void main(String[] args) {
        String[] oper = args;

        while (true){
            int thersMult = 0;
            int[] indxMult;
            int resMult = 0;

            //se presenti molt. o divis., thersMult = 1, altrimenti = 0
            thersMult = contrMul(oper);

            //se non sono presenti molt. o divis., risolve direttamentre l'equazione...
            if(thersMult == 0) {
                System.out.println(addAndSubt(oper)); //stampa risultato
                break;
            }
            //...altrimenti...
            else if(thersMult == 1) {
                //otteniamo indici di inizio e fine moltiplicazione
                indxMult = estraiIndx(oper);
                resMult = multAndDiv(oper, indxMult);
                setNullMult(oper, indxMult);
                oper = setNewArr(resMult, oper);
            }
        }
    }

    public static int addAndSubt(String[] numb) {
        int res = Integer.parseInt(numb[0]);
        for (int i = 1; i < numb.length - 1; i += 2) {
            switch (numb[i]) {
                case "+":
                    res = res + Integer.parseInt(numb[i + 1]);
                    break;
                case "-":
                    res = res - Integer.parseInt(numb[i + 1]);
                    break;

            }
        }
        return res;
    }

    public static int multAndDiv(String[] args, int[] indx) {
        int res = Integer.parseInt(args[indx[0]]);
        for (int i = indx[0]; i <= indx[1] ; i++) {
            switch (args[i]) {
                case "*":
                    res = res * Integer.parseInt(args[i + 1]);
                    break;
                case "/":
                    res = res / Integer.parseInt(args[i + 1]);
                    break;
            }
        }
        return res;
    }

    public static void setNullMult(String[] args, int[] indx) {
        for(int i=indx[0]; i<=indx[1]; i++) {
            args[i] = null;
        }
    }

    public static int contrMul(String[] args){
        int isPresent = 0;

        for (int i = 0; i < args.length; i++) {
            if (Objects.equals(args[i], "*") || Objects.equals(args[i], "/")) {
                isPresent = 1;
            }
        }
        return isPresent;
    }



    public static int[] estraiIndx(String[] args) {
        int startMult = 0;
        int endMult = 0;

        for (int i = 0; i < args.length; i++) {
            if ((Objects.equals(args[i], "*") || Objects.equals(args[i], "/")) && startMult == 0) {
                startMult = i - 1;
            }

            if(i == (args.length)-1) {
                endMult = i;
                break;
            }

            if ((Objects.equals(args[i], "+") || Objects.equals(args[i], "-")) && startMult != 0) {
                endMult = i-1;
                break;
            }


        }

        return new int[]{startMult, endMult};
    }

    public static String[] setNewArr(int res, String[] args) {
        for(int i=0; i<args.length; i++) { //trova il primo posto libero e aggiunge il res della molt/div
            if(args[i] == null) {
                args[i] = "" + res;
                break;
            }
        }
        int newLength = 0;
        //calcola nuova lunghezza stringa senza contare i posti null
        for(int i=0; i<args.length; i++) {
            if(args[i] != null) {
                newLength++;
            }
        }
        String[] newArr = new String[newLength];
        int j = 0;

        //ora inizializziamo il nuovo array con i caratteri non null
        for(int i=0; i<args.length; i++) {
            if(args[i] != null) {
                newArr[j] = args[i];
                j++;
            }
        }
        return newArr;
    }
}

