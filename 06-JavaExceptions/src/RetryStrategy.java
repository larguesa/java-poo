class RetryStrategy implements EstrategiaTratamento {
    @Override
    public void tratar(Exception e) {
        System.out.println("Tentando novamente... " + e.getMessage());
        // Lógica de retry aqui
    }
}