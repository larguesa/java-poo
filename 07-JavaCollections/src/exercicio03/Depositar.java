package exercicio03;

public class Depositar implements Comando {
    private final Conta c;
    private final double v;
    public Depositar(Conta c, double v){ this.c=c; this.v=v; }

    @Override public void executar(){ c.depositar(v); }
    @Override public void desfazer(){ c.sacar(v); }
    @Override public String nome(){ return "Depositar " + v; }
}