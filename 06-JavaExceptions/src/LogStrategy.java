class LogStrategy implements EstrategiaTratamento {
    @Override
    public void tratar(Exception e) {
        System.out.println("Logando erro: " + e.getMessage());
        // Escreve em log
    }
}