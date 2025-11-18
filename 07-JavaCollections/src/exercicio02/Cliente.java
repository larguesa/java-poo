package exercicio02;

public class Cliente {
    private final String cpf;
    private final String nome;
    private final String email;

    public Cliente(String cpf, String nome, String email) {
        this.cpf = cpf; this.nome = nome; this.email = email;
    }
    public String getCpf() { return cpf; }
    public String getNome() { return nome; }
    public String getEmail() { return email; }

    @Override
    public String toString() {
        return "%s - %s <%s>".formatted(cpf, nome, email);
    }
}