package exercicios._02;

/**
 * Estratégia Concreta para o cálculo de um frete expresso.
 */
public class FreteExpresso implements TipoDeFrete {

    @Override
    public double calcular(double distancia) {
        // Lógica específica do frete expresso: R$ 2,50 por km.
        return distancia * 2.50;
    }
}