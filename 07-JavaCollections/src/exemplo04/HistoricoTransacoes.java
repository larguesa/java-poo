package exemplo04;

import java.util.*;

public class HistoricoTransacoes {
    private final Deque<Comando> feitos = new ArrayDeque<>();
    private final Deque<Comando> desfeitos = new ArrayDeque<>();

    public void executar(Comando c){
        c.executar();
        feitos.push(c);
        desfeitos.clear();
    }
    public void undo(){
        if(!feitos.isEmpty()){
            var c = feitos.pop();
            c.desfazer();
            desfeitos.push(c);
        }
    }
    public void redo(){
        if(!desfeitos.isEmpty()){
            var c = desfeitos.pop();
            c.executar();
            feitos.push(c);
        }
    }
}