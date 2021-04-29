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

    public void setCel(int linha, int coluna) {
        celulas[linha][coluna].limparCelula();
    }

    public void setCel(Double dados, int linha, int coluna) {
        try {
            celulas[linha][coluna].adicionar(dados);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Atribuicao invalida");
        }
    }

    public void setCel(String dados, int linha, int coluna) {
        try {
            celulas[linha][coluna].adicionar(dados);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Atribuicao invalida");
        }
    }

    public void setCel(Formula dados, int linha, int coluna) {
        try {
            celulas[linha][coluna].adicionar(dados);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Atribuicao invalida");
        }
    }

    public Celula getCel(int linha, int coluna) {
        try {
            return celulas[linha][coluna];
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

    public void mostraPlan(int linhaInicial, int linhaFinal, int colunaInicial, int colunaFinal) {
        if (linhaInicial < 0) {linhaInicial = 0;}
        if (colunaInicial < 0) {colunaInicial = 0;}

        for (int i = 0; i < comprimento; i++) {
            System.out.print("======");
        }
        for (int i = linhaInicial; i < linhaFinal; i++) {
            try{
                Object valor = celulas[i][0];
                System.out.print("\n" + (i + 1) + "|");
                for (int j = colunaInicial; j < colunaFinal; j++) {
                    //optei por nao utilizar os indices (A,B,C,D,E...), porque eles nao ficavam alinhados as celulas
                    System.out.print(celulas[i][j].toString());
                    System.out.print("|");
                }
                System.out.print("\n");
            }
            catch (IndexOutOfBoundsException e) {}
        }
    }

    public void limpaCels(int linhaInicial, int linhaFinal, int colunaInicial, int colunaFinal) {
        if (linhaInicial > linhaFinal || colunaInicial > colunaFinal) {
            return;
        }
        try {
            celulas[linhaInicial][colunaInicial].limparCelula();
            //recursivamente limpas as celulas verticalmente
            limpaCels(linhaInicial + 1, linhaFinal, colunaInicial, colunaFinal);
            //recursivamente limpa as celulas horizontalmente
            limpaCels(linhaInicial, linhaFinal, colunaInicial + 1, colunaFinal);
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
