package br.gov.sp.fatec.pg.oo;

public class Carro {

    public static int numeroDeRodas = 4;

    private String cor;
    private String placa;

    public void exibirInformacoes() {
        // Acessando atributos de instância: Apenas pelo 'this'
        System.out.println("------------------------------");
        System.out.println("Carro com placa: " + this.placa);
        System.out.println("Cor do carro (instância): " + this.cor);
        // Acessando atributo de classe: via 'this' ou diretamente pela classe.
        System.out.println("Número de rodas (classe): " + Carro.numeroDeRodas);
        System.out.println("------------------------------\n");
    }

    // --- Métodos para interagir com os atributos ---

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getPlaca() {
        return placa;
    }
}