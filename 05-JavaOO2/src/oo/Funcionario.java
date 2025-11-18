package oo;

public class Funcionario {
    protected String nome;
    protected double salario;

    public Funcionario(String nome, double salario) {
        this.nome = nome;
        this.salario = salario;
    }

    public double getBonificacao() {
        return this.salario * 0.10;
    }
}