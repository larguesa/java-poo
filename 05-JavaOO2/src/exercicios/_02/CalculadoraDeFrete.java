package exercicios._02;

/**
 * Classe de Contexto que utiliza uma estratégia de frete para realizar o cálculo.
 * Ela não conhece os detalhes de como o cálculo é feito, apenas delega a responsabilidade
 * para o objeto de estratégia que recebe.
 */
public class CalculadoraDeFrete {

    /**
     * Executa o cálculo de frete utilizando a estratégia fornecida.
     *
     * @param tipo O objeto da estratégia de frete a ser usada.
     * @param distancia A distância para o cálculo.
     * @return O valor do frete calculado pela estratégia.
     */
    public double calculaFrete(TipoDeFrete tipo, double distancia) {
        return tipo.calcular(distancia);
    }
}