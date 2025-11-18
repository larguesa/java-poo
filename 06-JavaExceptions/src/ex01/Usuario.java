package ex01;

public class Usuario {
    private String email;
    private int idade;

    public Usuario(String email, int idade) {
        this.email = email;
        this.idade = idade;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}