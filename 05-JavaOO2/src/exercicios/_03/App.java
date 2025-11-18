package exercicios._03;

public class App {

    public static void main(String[] args) {
        System.out.println("--- Iniciando processamento de um arquivo TXT ---");
        // Instancia o processador de TXT
        ProcessadorArquivo processadorTxt = new ProcessadorTxt();
        // Chama o Template Method. Ele executará os 3 passos na ordem correta.
        processadorTxt.processar();

        System.out.println("\n------------------------------------------------\n");

        System.out.println("--- Iniciando processamento de um arquivo XML ---");
        // Instancia o processador de XML
        ProcessadorArquivo processadorXml = new ProcessadorXml();
        // Chama o mesmo Template Method, mas o resultado do passo 2 será diferente.
        processadorXml.processar();
    }
}