package exercicio02;

import java.util.*;

public class RepositorioCliente {
    private final Map<String, Cliente> clientes;

    public RepositorioCliente(PoliticaMap politica) {
        this.clientes = MapFactory.criar(politica);
    }

    public void salvar(Cliente c) {
        if (clientes.values().stream().anyMatch(x -> x.getEmail().equalsIgnoreCase(c.getEmail()))) {
            throw new IllegalArgumentException("E-mail já cadastrado: " + c.getEmail());
        }
        clientes.put(c.getCpf(), c);
    }

    public Cliente buscar(String cpf) { return clientes.get(cpf); }
    public Collection<Cliente> todos() { return clientes.values(); }
    public void remover(String cpf) { clientes.remove(cpf); }

    public List<Cliente> listarPorNome() {
        return clientes.values().stream()
                .sorted(Comparator.comparing(Cliente::getNome))
                .toList();
    }
}