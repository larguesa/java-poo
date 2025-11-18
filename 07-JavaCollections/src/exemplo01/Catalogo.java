package exemplo01;

import java.util.*;

public class Catalogo {
    private final List<Produto> itens = new ArrayList<>();

    public void add(Produto p) { itens.add(p); }

    public List<Produto> ordenarPor(Comparator<Produto> estrategia) {
        List<Produto> copia = new ArrayList<>(itens);
        copia.sort(estrategia);
        return copia;
    }

    // Estratégias
    public static final Comparator<Produto> POR_NOME =
        Comparator.comparing(Produto::getNome, String.CASE_INSENSITIVE_ORDER);

    public static final Comparator<Produto> POR_PRECO_ASC =
        Comparator.comparingDouble(Produto::getPreco);

    public static final Comparator<Produto> POR_PRECO_DESC =
        POR_PRECO_ASC.reversed();
}