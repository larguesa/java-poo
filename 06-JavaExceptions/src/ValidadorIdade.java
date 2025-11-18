public class ValidadorIdade {
    public static void validarIdade(int idade) throws IdadeInvalidaException {
        if (idade < 0 || idade > 150) {
            throw new IdadeInvalidaException("Idade inválida: " + idade);
        }
        System.out.println("Idade válida!");
    }

    public static void main(String[] args) {
        try {
            validarIdade(-5);
        } catch (IdadeInvalidaException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }
}