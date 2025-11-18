package ex02;
import java.io.IOException;

public class RetryRecuperacao implements EstrategiaRecuperacao {
    @Override
    public void recuperar(IOException e) {
        System.out.println("Tentando novamente... (simulando 3 tentativas)");
        for (int i = 1; i <= 3; i++) {
            System.out.println("Tentativa " + i + ": Falhou com " + e.getMessage());
            // Aqui você poderia tentar ler o arquivo novamente, mas pra simplicidade, só simula
        }
        System.out.println("Após 3 tentativas, abortando. Logando erro: " + e.getMessage());
    }
}