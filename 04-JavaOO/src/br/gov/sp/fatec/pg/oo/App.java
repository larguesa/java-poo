package br.gov.sp.fatec.pg.oo;
public class App {
    public static void main(String[] args) throws Exception {
        Data hoje = new Data();
        hoje.setDia(1);
        hoje.setMês(9);
        hoje.setAno(2025);

        Data meuNascimento = new Data();
        meuNascimento.setDia(1);
        meuNascimento.setMês(7);
        meuNascimento.setAno(1979);
    }

    /*public static void main(String[] args) throws Exception {
        Horario agora = new Horario();
        agora.horas = 14;
        agora.minutos = 45;
        agora.segundos = 30;

        Horario intervalo = new Horario();
        intervalo.horas = 15;
        intervalo.minutos = 00;
        intervalo.segundos = 00;
    }*/
}