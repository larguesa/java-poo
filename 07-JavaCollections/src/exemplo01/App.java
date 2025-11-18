package exemplo01;

public class App {
    public static void main(String[] args) {
        var c = new Catalogo();
        c.add(new Produto("A1","Mouse",79.9));
        c.add(new Produto("B2","Teclado",149.0));
        c.add(new Produto("C3","Webcam",299.0));

        System.out.println(c.ordenarPor(Catalogo.POR_NOME));
        System.out.println(c.ordenarPor(Catalogo.POR_PRECO_DESC));
    }
}
