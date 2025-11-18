package exercicios._01;

/**
 * Classe que representa um Retângulo.
 * É uma subclasse de Figura e, portanto, deve implementar o método calcularArea().
 */
public class Retangulo extends Figura {

    private double base;
    private double altura;

    // Construtor para inicializar os atributos do retângulo.
    public Retangulo(double base, double altura) {
        this.base = base;
        this.altura = altura;
    }

    /**
     * Implementação concreta do método abstrato da superclasse.
     * Calcula a área com a fórmula específica do retângulo.
     */
    @Override
    public double calcularArea() {
        return this.base * this.altura;
    }
}