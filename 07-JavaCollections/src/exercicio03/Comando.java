package exercicio03;

public interface Comando {
    void executar();
    void desfazer();
    String nome();
}