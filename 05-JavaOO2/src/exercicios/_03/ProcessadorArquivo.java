package exercicios._03;

/**
 * Classe Abstrata (Template) que define o esqueleto de um algoritmo
 * para processar um arquivo. O método `processar()` é o "Template Method".
 */
public abstract class ProcessadorArquivo {
    /**
     * Este é o Template Method. Ele é 'final' para garantir que as subclasses
     * não possam alterar a sequência de passos do algoritmo.
     */
    public final void processar() {
        // Passo 1: Fixo, implementado na classe base.
        abrirArquivo();
        // Passo 2: Variável, deve ser implementado pelas subclasses.
        lerConteudo();
        // Passo 3: Fixo, implementado na classe base.
        fecharArquivo();
    }
    private void abrirArquivo() {
        System.out.println("Abrindo arquivo...");
    }
    private void fecharArquivo() {
        System.out.println("Fechando arquivo...");
    }
    /**
     * Este método é a parte do algoritmo que varia e deve ser
     * implementado por cada subclasse concreta.
     */
    protected abstract void lerConteudo();
}