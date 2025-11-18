package exemplo01;

public class Produto {
    private final String sku;
    private String nome;
    private double preco;

    public Produto(String sku, String nome, double preco) {
        this.sku = sku;
        this.nome = nome;
        this.preco = preco;
    }
    public String getSku() { return sku; }
    public String getNome() { return nome; }
    public double getPreco() { return preco; }

    @Override public String toString() {
        return "%s - %s (R$ %.2f)".formatted(sku, nome, preco);
    }
}