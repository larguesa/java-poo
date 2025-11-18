package ex02;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EstrategiaRecuperacao estrategiaAtual = new RetryRecuperacao(); // Começa com retry
        ProcessadorArquivo processador = new ProcessadorArquivo(estrategiaAtual);

        System.out.println("--- Processador de Arquivos com Strategy ---");
        System.out.println("Vamos testar com um arquivo inexistente: 'arquivo_inexistente.txt'");

        while (true) {
            System.out.println("\nEstratégia atual: " + estrategiaAtual.getClass().getSimpleName());
            System.out.println("1. Processar arquivo (usando estratégia atual)");
            System.out.println("2. Trocar para RetryRecuperacao");
            System.out.println("3. Trocar para AbortRecuperacao");
            System.out.println("4. Sair");
            System.out.print("Escolha: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir quebra de linha

            switch (opcao) {
                case 1:
                    processador.processarArquivo("arquivo_inexistente.txt");
                    break;
                case 2:
                    estrategiaAtual = new RetryRecuperacao();
                    processador.setEstrategia(estrategiaAtual);
                    System.out.println("Estratégia trocada para Retry!");
                    break;
                case 3:
                    estrategiaAtual = new AbortRecuperacao();
                    processador.setEstrategia(estrategiaAtual);
                    System.out.println("Estratégia trocada para Abort!");
                    break;
                case 4:
                    System.out.println("Saindo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }
}