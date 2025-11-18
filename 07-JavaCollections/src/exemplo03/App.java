package exemplo03;

public class App {
    public static void main(String[] args) {
        var repo = new RepositorioCliente(MapFactory.criar(PoliticaMap.ORDEM_INSERCAO));
        repo.salvar(new Cliente("111","Ana"));
        repo.salvar(new Cliente("222","Bruno"));
        for (var e : repo.todos()) System.out.println(e.getNome());
    }
}