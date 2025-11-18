package ex02;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ProcessadorArquivo {
    private EstrategiaRecuperacao estrategia;

    public ProcessadorArquivo(EstrategiaRecuperacao estrategia) {
        this.estrategia = estrategia;
    }

    public void setEstrategia(EstrategiaRecuperacao estrategia) {
        this.estrategia = estrategia;
    }

    public void processarArquivo(String caminho) {
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                System.out.println("Linha lida: " + linha);
            }
            System.out.println("Arquivo processado com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao processar arquivo: " + e.getMessage());
            estrategia.recuperar(e); // Delega pra estratégia
        }
    }
}