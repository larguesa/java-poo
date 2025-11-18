package exercicios._01;

/**
 * Classe abstrata que representa o conceito genérico de uma figura geométrica.
 * Não pode ser instanciada diretamente.
 * Obriga todas as subclasses a implementarem o método de cálculo de área.
 */
public abstract class Figura {

    /**
     * Método abstrato (sem implementação) que deve ser sobrescrito
     * pelas subclasses para calcular a área específica da figura.
     *
     * @return O valor da área calculada (double).
     */
    public abstract double calcularArea();
}