package ex01;

public class ValidadorUsuario {
    public static void validar(String email, int idade) throws EmailInvalidoException, IdadeInvalidaException {
        if (email == null || !email.contains("@")) {
            throw new EmailInvalidoException("Email inválido: deve conter '@'. Valor fornecido: " + email);
        }
        if (idade < 0) {
            throw new IdadeInvalidaException("Idade inválida: deve ser maior ou igual a 0. Valor fornecido: " + idade);
        }
        System.out.println("Usuário válido! Email: " + email + ", Idade: " + idade);
    }
}