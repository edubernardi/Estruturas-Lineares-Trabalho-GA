package com.company;

public class Main {

    public static void main(String[] args) {
        Planilha p = new Planilha(5, 5);

        p.setCel(5, 2, 2);
        p.setCel(10, 0, 0);
        p.setCel(new Formula("+", p.getCel(2, 2), p.getCel(0, 0)), 3, 3);
        p.setCel(0, 4, 4);
        p.setCel(new Formula("/", p.getCel(0, 2), p.getCel(4, 4)), 0, 4);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                p.setCel(1, i, j);
            }
        }

        p.mostraPlan();
        System.out.println("==========================");
        //p.limpaCels(0, 2, 2, 4);
        p.mostraPlan();
        p.salvaPlan("planteste.csv");
    }
}

