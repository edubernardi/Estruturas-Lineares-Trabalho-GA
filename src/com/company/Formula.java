package com.company;

public class Formula {
    private Celula resultado;

    public Formula(String operation, Celula celA, Celula celB) {
        if (celA.isTemFormula()) {
            celA = celA.getFormula().getResultado();
        } else if (celB.isTemFormula()) {
            celB = celB.getFormula().getResultado();
        }
        if (celA.isVazia() || celB.isVazia()) {
            resultado = new Celula("ERRO");
        } else if (celA.isTemString() && celB.isTemString()) {
            //concatenacao das strings
            resultado = new Celula(celA.getDadosS() + celB.getDadosD());
        } else if (celA.isTemDouble() && celB.isTemDouble()) {
            double a = celA.getDadosD();
            double b = celB.getDadosD();

            switch (operation) {
                case "+" -> resultado = new Celula(a + b);
                case "-" -> resultado = new Celula(a - b);
                case "/" -> resultado = new Celula(a / b);
                case "*" -> resultado = new Celula(a * b);
                default -> resultado = new Celula("Erro");
            }
        } else {
            resultado = new Celula("Erro");
        }
    }

    public Celula getResultado() {
        return resultado;
    }

    @Override
    public String toString() {
        return resultado.toString();
    }
}
