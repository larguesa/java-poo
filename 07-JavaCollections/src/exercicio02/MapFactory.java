package exercicio02;

import java.util.*;

public class MapFactory {
    public static <K, V> Map<K, V> criar(PoliticaMap politica) {
        return switch (politica) {
            case RAPIDO -> new HashMap<>();
            case ORDEM_INSERCAO -> new LinkedHashMap<>();
            case ORDENADO -> new TreeMap<>();
        };
    }
}