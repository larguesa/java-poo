package br.gov.sp.fatec.pg.oo;
import java.time.LocalDate;
import java.time.Period;

public class Pessoa {
    String nome;
    String cpf;
    double altura;
    double peso;
    Pessoa mãe;
    Pessoa pai;
    LocalDate nascimento;

    int getIdade() {
        LocalDate dataAtual = LocalDate.now();
        Period periodo = Period.between(nascimento, dataAtual);
        return periodo.getYears();
    }
    public static void main(String[] args) {
        Pessoa pessoa = new Pessoa();
        pessoa.nome = "Ricard";
        pessoa.cpf = "123.456.789-00";
        pessoa.altura = 1.8;
        pessoa.peso = 90.0;
        pessoa.nascimento = LocalDate.of(1979, 7, 1);
        System.out.println("Idade: " + pessoa.getIdade());
    }
}