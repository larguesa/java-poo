package exercicio03;
import java.util.*;

public class Historico {
    private final Deque<Comando> feitos = new ArrayDeque<>();
    private final Deque<Comando> desfeitos = new ArrayDeque<>();

    public void executar(Comando c) {
        c.executar();
        feitos.push(c);
        desfeitos.clear();
    }

    public void undo() {
        if (!feitos.isEmpty()) {
            Comando c = feitos.pop();
            c.desfazer();
            desfeitos.push(c);
        }
    }

    public void redo() {
        if (!desfeitos.isEmpty()) {
            Comando c = desfeitos.pop();
            c.executar();
            feitos.push(c);
        }
    }
}