package com.company;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        Planilha p = new Planilha(5, 5);

        //Enchendo a planilha com numeros aleatorios para teste
        Random r = new Random();
        for (int i = 0; i < 10; i++){
            for (int j = 0; j < 10; j++){
                p.setCel(r.nextInt(1000), i, j);
            }
        }

        //p.lePlan("planteste.csv");

        p.setCel("Joao", 3,3);
        p.setCel(new Formula("+", p.getCel(0,0), p.getCel(1,1)), 4,4);
        p.setCel(0,0);

        p.salvaPlan("planteste.csv");
        p.mostraPlan();
        //isso vai limpar toda a planilha
        p.limpaCels(0, 15, 0, 15);


        //p.setCel(new Formula("+", p.getCel(4,4), p.getCel(0,0)), 1, 1);

        p.salvaPlan("planteste2.csv");
        p.mostraPlan();

        p.lePlan("planteste.csv");
        p.mostraPlan();
    }
}

