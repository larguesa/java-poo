import java.io.*;

public class LeitorArquivo {
    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new FileReader("arquivo.txt"))) {
            String linha = br.readLine();
            System.out.println(linha);
        } catch (FileNotFoundException e) {
            System.out.println("Arquivo não encontrado: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Erro de I/O: " + e.getMessage());
        } finally {
            System.out.println("Recursos limpos!");
        }
    }
}