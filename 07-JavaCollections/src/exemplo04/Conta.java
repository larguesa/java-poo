package exemplo04;

interface Comando {
    void executar();
    void desfazer();
    String nome();
}
class Conta {
    private double saldo;
    public void depositar(double v){ saldo+=v; }
    public void sacar(double v){ saldo-=v; }
    public double getSaldo(){ return saldo; }
}
class Depositar implements Comando {
    private final Conta c; private final double v;
    public Depositar(Conta c,double v){ this.c=c; this.v=v; }
    public void executar(){ c.depositar(v); }
    public void desfazer(){ c.sacar(v); }
    public String nome(){ return "Depositar " + v; }
}
class Sacar implements Comando {
    private final Conta c; private final double v;
    public Sacar(Conta c,double v){ this.c=c; this.v=v; }
    public void executar(){ c.sacar(v); }
    public void desfazer(){ c.depositar(v); }
    public String nome(){ return "Sacar " + v; }
}