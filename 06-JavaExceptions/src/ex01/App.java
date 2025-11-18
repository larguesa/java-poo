package ex01;

import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("--- Validador de Usuário ---");
        System.out.println("Digite o email e a idade. Vamos testar validação com exceções!");

        while (true) {
            System.out.print("Email: ");
            String email = scanner.nextLine();

            System.out.print("Idade: ");
            int idade = -1; // Valor padrão pra caso dê erro na leitura
            try {
                idade = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Erro: Idade deve ser um número inteiro. Tente novamente.");
                continue; // Volta pro loop
            }

            // Cria o usuário (usando encapsulamento)
            Usuario usuario = new Usuario(email, idade);

            // Tenta validar – aqui entra o try-catch pro tratamento
            try {
                ValidadorUsuario.validar(usuario.getEmail(), usuario.getIdade());
                System.out.println("Sucesso! Usuário criado.");
            } catch (EmailInvalidoException e) {
                System.out.println("Ops! " + e.getMessage() + " Tente corrigir o email.");
            } catch (IdadeInvalidaException e) {
                System.out.println("Ops! " + e.getMessage() + " Tente corrigir a idade.");
            }

            System.out.print("Quer testar outro? (s/n): ");
            String resposta = scanner.nextLine();
            if (!resposta.equalsIgnoreCase("s")) {
                break;
            }
        }

        System.out.println("Fim do teste! Lembre-se: exceções tornam o código mais robusto.");
        scanner.close();
    }
}