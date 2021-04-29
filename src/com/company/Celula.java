package com.company;

public class Celula {
    private Object dados;

    public Celula() {
        this.dados = null;
    }

    public Celula(Object dados) {
        if (dados instanceof Double || dados instanceof String || dados instanceof Formula){
            this.dados = dados;
        }
        else{
            System.out.println("Tipo invalido");
        }
    }

    //esse metodo vai substituir os dados na celula, independente se esta vazia ou nao
    public void adicionar(Object dados) {
        this.dados = dados;
    }

    public void limparCelula() {
        dados = null;
    }

    public String toString() {
        if (dados == null) {
            return "    ";
        } else if (dados instanceof Double) {
            return "" + dados;
        } else if (dados instanceof String) {
            return (String) dados;
        } else if (dados instanceof Formula) {
            return dados.toString();
        } else {
            return "ERRO";
        }
    }

    public Object getDados() {
        return dados;
    }
}
