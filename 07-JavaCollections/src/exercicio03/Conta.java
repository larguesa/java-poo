package exercicio03;

public class Conta {
    private double saldo;

    public void depositar(double v){ saldo += v; }
    public void sacar(double v){ saldo -= v; }
    public double getSaldo(){ return saldo; }

    @Override
    public String toString(){ return "Saldo: R$ %.2f".formatted(saldo); }
}