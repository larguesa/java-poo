package oo;

public abstract class Animal {
    // Método concreto, herdado por todos
    public void respirar() {
        System.out.println("Respirando...");
    }

    // Método abstrato, deve ser implementado pelas subclasses
    public abstract void fazerBarulho();
}