package com.infnet.petfriends.petfriends_transporte.domain.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class EnderecoEntrega {
    private String rua;
    private String numero;
    private String cidade;
    private String estado;
    private String cep;
    private String complemento;

    protected EnderecoEntrega() {}

    public EnderecoEntrega(String rua, String numero, String cidade, String estado, String cep, String complemento) {
        this.rua = rua;
        this.numero = numero;
        this.cidade = cidade;
        this.estado = estado;
        this.cep = cep;
        this.complemento = complemento;
    }

    public String getRua() { return rua; }
    public String getNumero() { return numero; }
    public String getCidade() { return cidade; }
    public String getEstado() { return estado; }
    public String getCep() { return cep; }
    public String getComplemento() { return complemento; }
}
