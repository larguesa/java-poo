package exercicio02;

public class App {
    public static void main(String[] args) {
        RepositorioCliente repo = new RepositorioCliente(PoliticaMap.ORDEM_INSERCAO);
        repo.salvar(new Cliente("111", "Ana", "ana@ex.com"));
        repo.salvar(new Cliente("222", "Bruno", "bruno@ex.com"));
        repo.salvar(new Cliente("333", "Carlos", "carlos@ex.com"));

        System.out.println("Todos:");
        repo.todos().forEach(System.out::println);

        System.out.println("\nOrdenados por nome:");
        repo.listarPorNome().forEach(System.out::println);
    }
}