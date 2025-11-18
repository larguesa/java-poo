package exercicios._03;

/**
 * Subclasse Concreta que implementa a parte variável do algoritmo
 * para ler arquivos do tipo TXT.
 */
public class ProcessadorTxt extends ProcessadorArquivo {

    @Override
    protected void lerConteudo() {
        System.out.println(">> Lendo e processando o conteúdo do arquivo TXT.");
    }
}