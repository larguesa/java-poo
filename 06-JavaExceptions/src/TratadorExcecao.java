abstract class TratadorExcecao {
    public final void tratar(Exception e) {
        capturar(e);
        recuperar(e);
        notificar(e);
    }

    private void capturar(Exception e) {
        System.out.println("Capturando: " + e.getMessage());
    }

    protected abstract void recuperar(Exception e);

    private void notificar(Exception e) {
        System.out.println("Notificando usuário sobre: " + e.getClass().getSimpleName());
    }
}