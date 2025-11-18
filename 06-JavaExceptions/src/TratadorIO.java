class TratadorIO extends TratadorExcecao {
    @Override
    protected void recuperar(Exception e) {
        System.out.println("Recuperando arquivo backup...");
    }
}