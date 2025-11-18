package exercicios._03;

/**
 * Subclasse Concreta que implementa a parte variável do algoritmo
 * para ler arquivos do tipo XML.
 */
public class ProcessadorXml extends ProcessadorArquivo {

    @Override
    protected void lerConteudo() {
        System.out.println(">> Lendo e processando o conteúdo do arquivo XML.");
    }
}