package com.company;

import java.util.Random;

public class Main {

    public static void main(String[] args) {
        //Gerando e enchendo a planilha
        Planilha p = new Planilha(5, 5);

        p.setCel("Funcionarios",0,0);
        p.setCel("Pedro",1,0);
        p.setCel("Bianca",2,0);
        p.setCel("Raphael da Silva",3,0);

        p.setCel("Salario", 0, 1);
        p.setCel(2000.0, 1, 1);
        p.setCel(2500.0, 2, 1);
        p.setCel(5000.0, 3, 1);

        p.setCel("Horas/dia", 0, 2);
        p.setCel(6.0, 1, 2);
        p.setCel(8.0, 2, 2);
        p.setCel(8.0, 3, 2);

        p.setCel("Horas/mes", 0, 3);
        p.setCel(new Formula("*", p.getCel(1,2), 20.0), 1, 3);
        p.setCel(new Formula("*", p.getCel(2,2), 20.0), 2, 3);
        p.setCel(new Formula("*", p.getCel(3,2), 20.0), 3, 3);

        p.setCel("Salario/hora", 0, 4);
        p.setCel(new Formula("/", p.getCel(1,1), p.getCel(1,3)), 1, 4);
        p.setCel(new Formula("/", p.getCel(2,1), p.getCel(2,3)), 2, 4);
        p.setCel(new Formula("/", p.getCel(3,1), p.getCel(3,3)), 3, 4);

        p.mostraPlan();
        p.mostraCel(3,0);

        //Salvamento e carregamento
        p.salvaPlan("funcionarios.csv");
        Planilha b = new Planilha(5,5);
        b.lePlan("funcionarios.csv");
        b.mostraPlan();

        //Limpa celulas
        b.limpaCels(0,2,0,100);
        b.mostraPlan();

        //b.setCel(new Formula("/", 5.0, 0.0), 0,0);

    }
}

