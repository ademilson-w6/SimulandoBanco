package br.ufpb.dcx.aps.atividades.banco;

import br.ufpb.dcx.aps.atividades.banco.Exceptions.BancoException;
import br.ufpb.dcx.aps.atividades.banco.Exceptions.ContaException;

public class BancoFacade {

    private Banco banco;

    public BancoFacade(String nomeDoBanco) {
        this.banco = new Banco(nomeDoBanco);
    }

    public void cadastrarCorrentista(String cpf, String nome) throws BancoException {
        banco.addCorrentista(new Correntista(cpf, nome));
    }

    public Conta criarContaPF(String cpf) throws BancoException {
        return banco.criarConta(banco.getCorrentista(cpf));

    }

    public Conta getConta(Integer numero) {
        return banco.getConta(numero);
    }

    public double saldo(Integer numero) {
        return banco.getConta(numero).getSaldo();
    }

    public void depositar(double valor, int numeroConta){
        banco.getConta(numeroConta).depositar(valor);
    }

    public void sacar(double valor, Integer numero) throws ContaException {
        banco.getConta(numero).sacar(valor);
    }

    public String extrato(Integer numero) {
        return banco.getConta(numero).extrato();
    }
}
