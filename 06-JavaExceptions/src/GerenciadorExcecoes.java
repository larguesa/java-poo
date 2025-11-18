import java.io.IOException;

public class GerenciadorExcecoes {
    private EstrategiaTratamento estrategia;

    public void setEstrategia(EstrategiaTratamento estrategia) {
        this.estrategia = estrategia;
    }

    public void processar() {
        try {
            // Código que pode falhar
            throw new IOException("Falha de rede");
        } catch (Exception e) {
            estrategia.tratar(e);
        }
    }
}