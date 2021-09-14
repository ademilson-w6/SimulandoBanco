package br.ufpb.dcx.aps.atividades.banco.Exceptions;

import br.ufpb.dcx.aps.atividades.banco.Conta;

public class ContaException extends Exception {


    public ContaException(double valor, Conta conta){
        super("Saldo insuficiente. Saldo:"+conta.getSaldo()+ " - valor do saque:"+valor);
    }
}
