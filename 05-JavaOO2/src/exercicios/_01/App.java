package exercicios._01;

import java.util.ArrayList;

public class App {

    public static void main(String[] args) {
        // 1. Criando uma lista do tipo da superclasse abstrata.
        // Esta lista pode conter qualquer objeto que seja uma 'Figura'.
        ArrayList<Figura> minhasFiguras = new ArrayList<>();

        // 2. Adicionando instâncias das subclasses concretas.
        minhasFiguras.add(new Retangulo(10, 5)); // Área esperada: 50
        minhasFiguras.add(new Circulo(7));       // Área esperada: ~153.93
        minhasFiguras.add(new Retangulo(4, 3));  // Área esperada: 12

        System.out.println("--- Calculando a área das figuras (Polimorfismo em ação!) ---");

        // 3. Percorrendo a lista e chamando o método polimórfico.
        for (Figura figuraDaVez : minhasFiguras) {
            // O Java sabe qual método 'calcularArea()' chamar (o do Retangulo ou o do Circulo)
            // com base no objeto real que está na lista naquele momento.
            System.out.printf("A área da figura '%s' é: %.2f%n",
                    figuraDaVez.getClass().getSimpleName(), // Pega o nome da classe do objeto
                    figuraDaVez.calcularArea());
        }
    }
}