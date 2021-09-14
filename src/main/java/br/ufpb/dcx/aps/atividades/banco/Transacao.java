package br.ufpb.dcx.aps.atividades.banco;

import java.util.Calendar;

public class Transacao {

    private int id;
    private Calendar data;
    private double valor;
    private Tipo tipo;
    public enum Tipo{
        CREDITO("CRÉDITO"), DEBITO("DÉBITO");
        private String descricao;

        Tipo(String descricao) {
            this.descricao = descricao;
        }

        public String getDescricao() {
            return descricao;
        }
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }

    public Transacao(int id, double valor){
        this.id = id;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public Calendar getDataTransacao(){
        return this.data;
    }

    public double getValor() {
        return valor;
    }

    @Override
    public String toString() {
        return "Transacao{" + "id=" + id + ", data=" + data + ", valor=" + valor + '}';
    }


}
