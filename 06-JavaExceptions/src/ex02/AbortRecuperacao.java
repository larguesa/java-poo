package ex02;
import java.io.IOException;

public class AbortRecuperacao implements EstrategiaRecuperacao {
    @Override
    public void recuperar(IOException e) {
        System.out.println("Abortando operação. Logando erro: " + e.getMessage());
        // Poderia escrever em um arquivo de log real aqui
    }
}