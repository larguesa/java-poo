package exemplo05;

import java.util.*;

public abstract class Importador<T> {
    public final List<T> importar(String fonte) {
        var linhas = carregarLinhas(fonte);
        var itens = new ArrayList<T>();
        for (String l : linhas) {
            if (ignorar(l)) continue;
            itens.add(parse(l));
        }
        return posProcessar(itens);
    }
    protected abstract List<String> carregarLinhas(String fonte);
    protected abstract T parse(String linha);
    protected boolean ignorar(String linha){ return linha.isBlank(); }
    protected List<T> posProcessar(List<T> itens){ return itens; }
}