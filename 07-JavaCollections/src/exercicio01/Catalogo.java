package exercicio01;

import java.util.*;

public class Catalogo {
    private final List<Produto> produtos = new ArrayList<>();

    public void adicionar(Produto p) { produtos.add(p); }

    public void removerPorSku(String sku) {
        produtos.removeIf(p -> p.getSku().equalsIgnoreCase(sku));
    }

    public List<Produto> buscarPorNome(String parcial) {
        return produtos.stream()
                .filter(p -> p.getNome().toLowerCase().contains(parcial.toLowerCase()))
                .toList();
    }

    public List<Produto> ordenarPor(Comparator<Produto> estrategia) {
        return produtos.stream().sorted(estrategia).toList();
    }

    // Estratégias pré-definidas
    public static final Comparator<Produto> POR_NOME =
        Comparator.comparing(Produto::getNome, String.CASE_INSENSITIVE_ORDER);
    public static final Comparator<Produto> POR_PRECO_ASC =
        Comparator.comparingDouble(Produto::getPreco);
    public static final Comparator<Produto> POR_PRECO_DESC = POR_PRECO_ASC.reversed();
    public static final Comparator<Produto> POR_SKU =
        Comparator.comparing(Produto::getSku);
}