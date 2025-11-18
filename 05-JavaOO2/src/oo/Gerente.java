package oo;

public class Gerente extends Funcionario implements Autenticavel {
    private String area;

    public Gerente(String nome, double salario, String area) {
        super(nome, salario); // Chama o construtor de Funcionario
        this.area = area;
    }
    @Override // Boa prática para garantir a sobrescrita
    public double getBonificacao() {
        return this.salario * 0.15 + 1000;
        /* No main:
            Funcionario f = new Gerente("Ricardo", 10000.0, "TI");
            System.out.println(f.getBonificacao());
        */
    }
    
    public boolean autenticar(String senha) {
        return "1234".equals(senha);
    }
}