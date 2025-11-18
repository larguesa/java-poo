package exemplo03;

import java.util.*;

public class RepositorioCliente {
    private final Map<String, Cliente> porCpf;

    public RepositorioCliente(Map<String, Cliente> impl) {
        this.porCpf = impl;
    }
    public void salvar(Cliente c) { porCpf.put(c.getCpf(), c); }
    public Cliente buscar(String cpf) { return porCpf.get(cpf); }
    public Collection<Cliente> todos() { return porCpf.values(); }
    public Cliente remover(String cpf) { return porCpf.remove(cpf); }
}

class Cliente {
    private final String cpf; private final String nome;
    public Cliente(String cpf, String nome){ this.cpf=cpf; this.nome=nome; }
    public String getCpf(){ return cpf; } public String getNome(){ return nome; }
}

enum PoliticaMap { RAPIDO, ORDEM_INSERCAO, ORDENADO; }

class MapFactory {
    public static <K,V> Map<K,V> criar(PoliticaMap p) {
        return switch (p) {
            case RAPIDO -> new HashMap<>();
            case ORDEM_INSERCAO -> new LinkedHashMap<>();
            case ORDENADO -> new TreeMap<>();
        };
    }
}