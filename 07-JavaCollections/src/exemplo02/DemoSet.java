package exemplo02;

import java.util.*;

public class DemoSet {
    public static void main(String[] args) {
        // HashSet: rápido, sem ordem
        Set<String> tags = new HashSet<>(List.of("java","oo","java","collections"));
        System.out.println(tags); // sem duplicatas

        // LinkedHashSet: preserva ordem de inserção
        Set<String> ordemInsercao = new LinkedHashSet<>(List.of("b","a","c","a"));
        System.out.println(ordemInsercao); // [b, a, c]

        // TreeSet: ordenado (natural ou Comparator)
        Set<String> ordenado = new TreeSet<>(String.CASE_INSENSITIVE_ORDER);
        ordenado.addAll(List.of("Delta","alpha","Bravo"));
        System.out.println(ordenado); // [alpha, Bravo, Delta]
    }
}