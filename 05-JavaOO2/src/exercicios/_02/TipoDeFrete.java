package exercicios._02;

/**
 * Interface (Strategy) que define o contrato para os diferentes tipos de cálculo de frete.
 * Qualquer classe que implemente esta interface será uma estratégia de frete válida.
 */
public interface TipoDeFrete {

    /**
     * Calcula o valor do frete com base em uma distância.
     *
     * @param distancia A distância em quilômetros para o cálculo.
     * @return O valor final do frete.
     */
    double calcular(double distancia);
}