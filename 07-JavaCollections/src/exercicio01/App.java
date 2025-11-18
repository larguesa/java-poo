package exercicio01;

public class App {
    public static void main(String[] args) {
        Catalogo c = new Catalogo();
        c.adicionar(new Produto("A1", "Mouse", 79.9));
        c.adicionar(new Produto("B2", "Teclado", 149.0));
        c.adicionar(new Produto("C3", "Webcam", 299.0));
        c.adicionar(new Produto("D4", "Monitor", 799.0));

        System.out.println("Por nome:");
        c.ordenarPor(Catalogo.POR_NOME).forEach(System.out::println);

        System.out.println("\nPor preço desc:");
        c.ordenarPor(Catalogo.POR_PRECO_DESC).forEach(System.out::println);

        System.out.println("\nBuscar 'mo':");
        c.buscarPorNome("mo").forEach(System.out::println);
    }
}