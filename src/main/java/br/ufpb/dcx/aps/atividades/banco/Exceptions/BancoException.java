package br.ufpb.dcx.aps.atividades.banco.Exceptions;

import br.ufpb.dcx.aps.atividades.banco.Correntista;

public class BancoException extends Exception {


    public BancoException(Correntista correntista) {
        super("Correntista não cadastrado no banco:" + correntista.toString());
    }

    public BancoException(String msg){
        super(msg);
    }


}
