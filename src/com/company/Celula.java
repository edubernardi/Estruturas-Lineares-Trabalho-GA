package com.company;

public class Celula {
    private boolean vazia;
    private boolean temDouble;
    private boolean temFormula;
    private boolean temString;

    private String dadosS;
    private double dadosD;
    private Formula formula;

    public Celula() {
        vazia = true;
        temDouble = false;
        temFormula = false;
        temString = false;
    }

    public Celula(String dadosS) {
        this.dadosS = dadosS;
        vazia = false;
        temDouble = false;
        temFormula = false;
        temString = true;
    }

    public Celula(double n) {
        this.dadosD = n;
        vazia = false;
        temDouble = true;
        temFormula = false;
        temString = false;
    }

    public Celula(Formula formula) {
        this.formula = formula;
        vazia = false;
        temDouble = false;
        temFormula = true;
        temString = false;
    }

    //esses metodos vao substituir os dados na celula, independente se esta vazia ou nao
    public void adicionar(int n) {
        vazia = false;
        temFormula = false;
        temString = false;
        temDouble = true;
        this.dadosD = n;
    }

    public void adicionar(String s) {
        vazia = false;
        temFormula = false;
        temString = true;
        temDouble = false;
        this.dadosS = s;
    }

    public void adicionar(Formula f) {
        vazia = false;
        temFormula = true;
        temString = false;
        temDouble = false;
        this.formula = f;
    }

    public void limparCelula() {
        vazia = true;
        temString = false;
        temFormula = false;
        temDouble = false;
    }

    public String toString() {
        if (vazia) {
            return "    ";
        } else if (temDouble) {
            return "" + dadosD;
        } else if (temString) {
            return dadosS;
        } else if (temFormula) {
            return formula.toString();
        } else {
            return "ERRO";
        }
    }

    public String getDadosS() {
        return dadosS;
    }

    public double getDadosD() {
        return dadosD;
    }

    public Formula getFormula() {
        return formula;
    }

    public boolean isVazia() {
        return vazia;
    }

    public boolean isTemDouble() {
        return temDouble;
    }

    public boolean isTemFormula() {
        return temFormula;
    }

    public boolean isTemString() {
        return temString;
    }
}
