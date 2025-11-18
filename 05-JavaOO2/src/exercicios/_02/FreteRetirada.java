package exercicios._02;

/**
 * Estratégia Concreta para retirada no local, com custo zero.
 */
public class FreteRetirada implements TipoDeFrete {

    @Override
    public double calcular(double distancia) {
        // Lógica específica para retirada: custo sempre zero, a distância é irrelevante.
        return 0.0;
    }
}