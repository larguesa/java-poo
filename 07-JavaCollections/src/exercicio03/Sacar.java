package exercicio03;

public class Sacar implements Comando {
    private final Conta c;
    private final double v;
    public Sacar(Conta c, double v){ this.c=c; this.v=v; }

    @Override public void executar(){ c.sacar(v); }
    @Override public void desfazer(){ c.depositar(v); }
    @Override public String nome(){ return "Sacar " + v; }
}