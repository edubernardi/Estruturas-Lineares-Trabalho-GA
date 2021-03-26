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

    public void setCel(int i, int j) {
        celulas[i][j].limparCelula();
    }

    public void setCel(int dados, int i, int j) {
        try {
            celulas[i][j].adicionar(dados);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Atribuicao invalida");
        }
    }

    public void setCel(String dados, int i, int j) {
        try {
            celulas[i][j].adicionar(dados);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Atribuicao invalida");
        }
    }

    public void setCel(Formula dados, int i, int j) {
        try {
            celulas[i][j].adicionar(dados);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Atribuicao invalida");
        }
    }

    public Celula getCel(int i, int j) {
        try {
            return celulas[i][j];
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Tentativa de acessar dados inexistentes");
            return new Celula();
        }
    }

    public void mostraPlan() {
        for (int i = 0; i < comprimento; i++) {
            System.out.print("======");
        }
        System.out.print("\n");
        for (int i = 0; i < celulas.length; i++) {
            System.out.print(i + 1 + "|");
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
                System.out.print(i + 1 + "|");
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
        try {
            celulas[iA][jA].limparCelula();
            //recursivamente limpas as celulas verticalmente
            limpaCels(iA + 1, iB, jA, jB);
            //recursivamente limpa as celulas horizontalmente
            limpaCels(iA, iB, jA + 1, jB);
        } catch (ArrayIndexOutOfBoundsException e) {
            return;
        }
    }

    public void salvaPlan(String nomeArquivo) {
        try (PrintWriter out = new PrintWriter(new FileWriter(nomeArquivo))) {
            for (int i = 0; i < altura; i++) {
                for (int j = 0; j < comprimento; j++) {
                    if (!celulas[i][j].isVazia()) {
                        out.print(celulas[i][j].toString() + ";");
                    } else {
                        out.print(";");
                    }
                }
                out.print("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void lePlan(String nomeArquivo) {
        try (BufferedReader in = new BufferedReader(new FileReader(nomeArquivo))) {
            String linha;
            int i = 0;
            int j = 0;
            String valorCelula = "";

            for (int k = 0; k < altura; k++) {
                String line = in.readLine();
                if (line != null) {
                    for (int l = 0; l < line.length() && i < altura && j < comprimento; l++) {
                        if (line.charAt(l) == ';') {
                            try {
                                //tenta converter o valor da celula para double e armazenar no array celulas
                                double valorCelulaDouble = Double.parseDouble(valorCelula);
                                celulas[i][j] = new Celula(valorCelulaDouble);
                            } catch (NumberFormatException e) {
                                // se essa conversao gerar uma excessao, o valor eh armazenado em celulas como String
                                if (valorCelula.equals("")) {
                                    celulas[i][j] = new Celula();
                                } else {
                                    celulas[i][j] = new Celula(valorCelula);
                                }
                            } finally {
                                //de qualquer forma, o valorCelula eh resetado e o iterador j passa pra proxima celula
                                valorCelula = "";
                                j++;
                            }
                        } else {
                            valorCelula += line.charAt(l);
                        }
                    }
                    j = 0;
                    i++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
