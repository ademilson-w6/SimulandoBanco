package br.ufpb.dcx.aps.atividades.banco;

import java.util.InputMismatchException;
import java.util.Objects;

public class Correntista {
    private String cpf;
    private String nome;
    private Conta conta;

    public Correntista(String cpf, String nome){
        if(!cpfValido(cpf)){
           throw new RuntimeException("CPF invalido:"+cpf);
        }
        this.cpf = cpf;
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public Conta getConta() {
        return conta;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setConta(Conta conta) {
        this.conta = conta;
    }

    public static Boolean cpfValido(String CPF){

        CPF = CPF.replace( " " , ""); //tira espaço em branco
        CPF = CPF.replace( "." , ""); //tira ponto
        CPF = CPF.replace( "/" , ""); //tira barra
        CPF = CPF.replace( "-" , ""); //tira hífen

        // considera-se erro CPF's formados por uma sequencia de numeros iguais
        if (CPF.equals("00000000000") ||
                CPF.equals("11111111111") ||
                CPF.equals("22222222222") || CPF.equals("33333333333") ||
                CPF.equals("44444444444") || CPF.equals("55555555555") ||
                CPF.equals("66666666666") || CPF.equals("77777777777") ||
                CPF.equals("88888888888") || CPF.equals("99999999999") ||
                (CPF.length() != 11))
            return(false);

        char dig10, dig11;
        int sm, i, r, num, peso;

        // "try" - protege o codigo para eventuais erros de conversao de tipo (int)
        try {
            // Calculo do 1o. Digito Verificador
            sm = 0;
            peso = 10;
            for (i=0; i<9; i++) {
                // converte o i-esimo caractere do CPF em um numero:
                // por exemplo, transforma o caractere '0' no inteiro 0
                // (48 eh a posicao de '0' na tabela ASCII)
                num = (int)(CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig10 = '0';
            else dig10 = (char)(r + 48); // converte no respectivo caractere numerico

            // Calculo do 2o. Digito Verificador
            sm = 0;
            peso = 11;
            for(i=0; i<10; i++) {
                num = (int)(CPF.charAt(i) - 48);
                sm = sm + (num * peso);
                peso = peso - 1;
            }

            r = 11 - (sm % 11);
            if ((r == 10) || (r == 11))
                dig11 = '0';
            else dig11 = (char)(r + 48);

            // Verifica se os digitos calculados conferem com os digitos informados.
            if ((dig10 == CPF.charAt(9)) && (dig11 == CPF.charAt(10))) return (true);
            return(false);
        } catch (InputMismatchException erro) {return(false);}

    }

    public static String removeSeparadores(String cpf){
        cpf = cpf.replace( " " , ""); //tira espaço em branco
        cpf = cpf.replace( "." , ""); //tira ponto
        cpf = cpf.replace( "/" , ""); //tira barra
        cpf = cpf.replace( "-" , ""); //tira hífen
        return cpf;
    }


    @Override
    public String toString() {
        return "Correntista{" + "cpf='" + cpf + '\'' + ", nome='" + nome + '\'' +'}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Correntista that = (Correntista) o;
        return cpf.equals(that.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }
}

