package com.company;
import java.io.*;

public class Planilha {
    private int altura;
    private int comprimento;
    private Celula[][] celulas;

    public Planilha(int altura, int comprimento) {
        this.altura = altura;
        this.comprimento = comprimento;
        this.celulas = new Celula[altura][comprimento];

        for (int i = 0; i < altura; i++) {
            for (int j = 0; j < comprimento; j++) {
                celulas[i][j] = new Celula();
            }
        }
    }

    public void setCel(int dados, int i, int j) {
        celulas[i][j].adicionar(dados);
    }

    public void setCel(String dados, int i, int j) {
        celulas[i][j].adicionar(dados);
    }

    public void setCel(Formula dados, int i, int j) {
        celulas[i][j].adicionar(dados);
    }

    public Celula getCel(int i, int j) {
        return celulas[i][j];
    }

    public void mostraPlan() {
        for (int i = 0; i < celulas.length; i++) {
            System.out.print(i+1+ "|");
            for (int j = 0; j < celulas[i].length; j++) {
                System.out.print(celulas[i][j].toString());
                System.out.print("|");
            }
            System.out.print("\n");
        }
    }

    public void mostraPlan(int iA, int iB, int jA, int jB) {
        try {
            for (int i = iA; i < iB; i++) {
                System.out.print(i+1+"|");
                for (int j = jA; j < jB; j++) {
                    //optei por nao utilizar os indices (A,B,C,D,E...), porque eles nao ficavam alinhados as celulas
                    System.out.print(celulas[i][j].toString());
                    System.out.print("|");
                }
                System.out.print("\n");
            }
        } catch (IndexOutOfBoundsException e) {
            System.out.println(e.getMessage());
        }
    }

    public void limpaCels(int iA, int iB, int jA, int jB) {
        if (iA > iB || jA > jB) {
            return;
        }
        celulas[iA][jA].limparCelula();

        limpaCels(iA + 1, iB, jA, jB);
        limpaCels(iA, iB, jA + 1, jB);
        limpaCels(iA + 1, iB, jA + 1, jB);
    }

    public void salvaPlan(String nomeArquivo){
        try (PrintWriter out = new PrintWriter( new FileWriter(nomeArquivo))){
            for (int i = 0; i < altura; i++) {
                for (int j = 0; j < comprimento; j++) {
                    if(celulas[i][j].toString() != "   "){
                        out.print(celulas[i][j].toString() + ";");
                    }
                }
                out.print("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
