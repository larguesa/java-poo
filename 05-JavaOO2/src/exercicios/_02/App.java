package exercicios._02;

public class App {

    public static void main(String[] args) {
        // O cliente (nosso método main) controla qual estratégia usar.
        CalculadoraDeFrete calculadora = new CalculadoraDeFrete();
        double distancia = 100.0; // Distância de 100km para o teste

        // 1. Instanciando as estratégias concretas
        TipoDeFrete freteNormal = new FreteNormal();
        TipoDeFrete freteExpresso = new FreteExpresso();
        TipoDeFrete freteRetirada = new FreteRetirada();

        // 2. Usando a calculadora com a estratégia de Frete Normal
        double valorNormal = calculadora.calculaFrete(freteNormal, distancia);
        System.out.printf("O valor do frete Normal para %.1fkm é: R$ %.2f%n", distancia, valorNormal);

        // 3. Usando a mesma calculadora com a estratégia de Frete Expresso
        double valorExpresso = calculadora.calculaFrete(freteExpresso, distancia);
        System.out.printf("O valor do frete Expresso para %.1fkm é: R$ %.2f%n", distancia, valorExpresso);

        // 4. Usando a mesma calculadora com a estratégia de Retirada
        double valorRetirada = calculadora.calculaFrete(freteRetirada, distancia);
        System.out.printf("O valor do frete por Retirada para %.1fkm é: R$ %.2f%n", distancia, valorRetirada);
    }
}