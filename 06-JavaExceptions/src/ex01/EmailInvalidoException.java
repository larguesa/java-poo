package ex01;

public class EmailInvalidoException extends Exception {
    public EmailInvalidoException(String mensagem) {
        super(mensagem);
    }
}