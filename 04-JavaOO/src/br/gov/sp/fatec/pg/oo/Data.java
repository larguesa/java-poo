package br.gov.sp.fatec.pg.oo;

public class Data {
    private int dia;
    private int mês;
    private int ano;

    public Data(){
        dia = 1;
        mês = 1;
        ano = 1970;
    }

    public Data(int dia, int mês, int ano){
        this.dia = dia;
        this.mês = mês;
        this.ano = ano;
    }

    public void setDia(int novoDia) {
        if(novoDia < 1) dia = 1;
        else if(novoDia > 31) dia = 31;
        else dia = novoDia;
    }

    public int getDia() {
        return dia;
    }

    public int getMês() {
        return mês;
    }

    public void setMês(int mês) {
        this.mês = mês;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }
}