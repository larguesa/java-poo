class TratadorValidacao extends TratadorExcecao {
    @Override
    protected void recuperar(Exception e) {
        System.out.println("Solicitando dados corretos...");
    }
}