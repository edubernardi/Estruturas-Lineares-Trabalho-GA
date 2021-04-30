package com.company;

public class Formula {
    private Celula resultado;

    public Formula(String operation, Object celA, Object celB) {
        if (celA instanceof Double || celA instanceof String || celA instanceof Formula){
            celA = new Celula(celA);
        }
        if (celB instanceof Double || celB instanceof String || celB instanceof Formula){
            celB = new Celula(celB);
        }

        if (((Celula) celA).getDados() instanceof Formula) {
            celA = ((Formula) ((Celula) celA).getDados()).getResultado();
        } else if (((Celula) celB).getDados() instanceof Formula) {
            celB = ((Formula) ((Celula) celB).getDados()).getResultado();
        }
        if (((Celula) celA).getDados() == null || ((Celula) celB).getDados() == null) {
            resultado = new Celula("ERRO");
        } else if (((Celula) celA).getDados() instanceof String && ((Celula) celB).getDados() instanceof String) {
            //concatenacao das strings
            resultado = new Celula((String) ((Celula) celA).getDados() + ((Celula) celB).getDados());
        } else if (((Celula) celA).getDados() instanceof Double && ((Celula) celB).getDados() instanceof Double) {
            Double a = (Double) ((Celula) celA).getDados();
            Double b = (Double) ((Celula) celB).getDados();

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
