package br.ufpb.dcx.aps.atividades.banco;

import br.ufpb.dcx.aps.atividades.banco.Exceptions.BancoException;

import java.util.HashMap;
import java.util.Map;

public class Banco {
    private Map<Integer, Conta> contasMap = new HashMap<>();
    private static int numeroContaLivre;
    private Map<String, Correntista> correntistaMap = new HashMap<>();
    private static int numero;
    private String nome;

    public Banco (String nome){
        this.nome = nome;
    }

    public Conta criarConta(Correntista correntista) throws BancoException {
        if(!correntistaMap.containsKey(correntista.getCpf())) {
            throw new BancoException(correntista);
        }

        for(Conta conta:contasMap.values()){
            if(conta.getCorrentista().getCpf().equals(correntista.getCpf())){
                throw new BancoException("Correntista já tem conta cadastrada");
            }
        }

        Conta conta = new Conta(correntista, gerarNumeroConta(),this);
        contasMap.put(conta.getNumero(),conta);
        return conta;
    }


    public void addCorrentista(Correntista correntista){
        if(correntistaMap.containsKey(correntista.getCpf())){
            throw new RuntimeException("Correntista ja cadastrado:"+correntista);
        }
        this.correntistaMap.put(correntista.getCpf(), correntista);
    }

    public Correntista getCorrentista(String cpf){
        if(!correntistaMap.containsKey(cpf)){
            throw new RuntimeException("Não existe correntista com cpf:"+cpf);
        }
        return correntistaMap.get(cpf);
    }

    public Conta getConta(Correntista titular){
       return correntistaMap.get(titular.getCpf()).getConta();
    }

    public Conta getConta(int conta){
        return contasMap.get(conta);
    }

    public static Integer gerarNumeroConta(){
       return Banco.numero ++;
    }


    public String getNome() {
        return nome;
    }
}
