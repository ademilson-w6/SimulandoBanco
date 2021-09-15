package br.ufpb.dcx.aps.atividades.banco;

import br.ufpb.dcx.aps.atividades.banco.Exceptions.ContaException;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;


public class Conta {
    public Correntista getCorrentista() {
        return correntista;
    }

    private Correntista correntista;
    private int numero;
    private ArrayList<Transacao> transacoes = new ArrayList<>();
    private Banco banco;
    private double saldo;
    private static int idTransacao;




    public Conta(Correntista correntista, int numero, Banco banco){
        this.correntista = correntista;
        this.numero = numero;
        this.banco = banco;
    }

    public Transacao depositar(double valor){
        Conta.idTransacao++;
        Transacao transacao = new Transacao(idTransacao,valor);
        this.saldo = this.saldo + transacao.getValor();
        transacao.setTipo(Transacao.Tipo.CREDITO);
        this.transacoes.add(transacao);
        return transacao;
    }

    public Transacao sacar (double valor) throws ContaException {
        Conta.idTransacao++;
        if(valor<0){
            throw new RuntimeException("Valor negativo");
        }
        if(valor == 0){
            throw new RuntimeException("Valor: "+valor);
        }
        if(this.saldo >= valor) {
            Transacao transacao = new Transacao(idTransacao,valor);
            this.saldo = this.saldo - transacao.getValor();
            transacao.setTipo(Transacao.Tipo.DEBITO);
            this.transacoes.add(transacao);
            return transacao;
        }
        else throw new ContaException(valor,this);
    }

    public double saldo(){
        return this.saldo;
    }

    public double getSaldo() {
        return saldo;
    }

    public String extrato (){

        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols();
        otherSymbols.setDecimalSeparator(',');

        DecimalFormat df = new DecimalFormat("0.00", otherSymbols);

        String textoTransa = "";

        for(Transacao transa:transacoes){
            String simboloDeposito = "";
            if(transa.getTipo().equals(Transacao.Tipo.DEBITO)){
                simboloDeposito = "-";
            }
            textoTransa+=transa.getTipo().getDescricao()+"\t"+simboloDeposito+"R$ "+df.format(transa.getValor())+"\n";
        }

        return ">> "+ this.banco.getNome()+"\n"+
                ">> Correntista: "+"\n"+
                " CPF: "+this.correntista.getCpf()+"\n"+
                " "+this.correntista.getNome()+"\n"+
                "> EXTRATO"+"\n"+
                "------------------------------------"+"\n"+

                textoTransa+
                "------------------------------------"+"\n"+
                "SALDO:	R$ "+df.format(this.saldo);

    }

    public ArrayList<Transacao> getTransacoes() {
        return transacoes;
    }

    public int getNumero() {
        return numero;
    }
}
