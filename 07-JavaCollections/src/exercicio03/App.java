package exercicio03;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Conta conta = new Conta();
        Historico hist = new Historico();
        Scanner sc = new Scanner(System.in);

        int op;
        do {
            System.out.println("\n[1] Depositar [2] Sacar [3] Saldo [4] Undo [5] Redo [0] Sair");
            op = sc.nextInt();

            switch (op) {
                case 1 -> { System.out.print("Valor: "); double v=sc.nextDouble(); hist.executar(new Depositar(conta,v)); }
                case 2 -> { System.out.print("Valor: "); double v=sc.nextDouble(); hist.executar(new Sacar(conta,v)); }
                case 3 -> System.out.println(conta);
                case 4 -> hist.undo();
                case 5 -> hist.redo();
            }
        } while (op != 0);
    }
}