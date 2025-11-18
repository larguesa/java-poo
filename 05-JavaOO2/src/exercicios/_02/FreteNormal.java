package exercicios._02;

/**
 * Estratégia Concreta para o cálculo de um frete normal.
 */
public class FreteNormal implements TipoDeFrete {

    @Override
    public double calcular(double distancia) {
        // Lógica específica do frete normal: R$ 1,25 por km.
        return distancia * 1.25;
    }
}