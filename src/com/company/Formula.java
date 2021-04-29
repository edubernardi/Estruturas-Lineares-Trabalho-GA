package com.company;

public class Formula {
    private Celula resultado;

    public Formula(String operation, Celula celA, Celula celB) {
        if (celA.getDados() instanceof Formula) {
            celA = ((Formula) celA.getDados()).getResultado();
        } else if (celB.getDados() instanceof Formula) {
            celB = ((Formula) celB.getDados()).getResultado();
        }
        if (celA.getDados() == null || celB.getDados() == null) {
            resultado = new Celula("ERRO");
        } else if (celA.getDados() instanceof String && celB.getDados() instanceof String) {
            //concatenacao das strings
            resultado = new Celula((String) celA.getDados() + celB.getDados());
        } else if (celA.getDados() instanceof Double && celB.getDados() instanceof Double) {
            Double a = (Double) celA.getDados();
            Double b = (Double) celB.getDados();

            switch (operation) {
                case "+" -> resultado = new Celula(a + b);
                case "-" -> resultado = new Celula(a - b);
                case "/" -> resultado = new Celula(a / b);
                case "*" -> resultado = new Celula(a * b);
                default -> resultado = new Celula("Erro");
            }
        } else {
            resultado = new Celula("ERRO");
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
