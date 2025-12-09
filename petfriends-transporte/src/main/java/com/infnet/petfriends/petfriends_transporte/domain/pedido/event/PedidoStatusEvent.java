package com.infnet.petfriends.petfriends_transporte.domain.pedido.event;

import java.time.Instant;
import java.util.List;

public class PedidoStatusEvent {
    private String pedidoId;
    private String status;

    private EnderecoDTO endereco;
    private ClienteDTO cliente;
    private List<ItemEntregaDTO> itens;

    private Instant atualizadoEm;

    public PedidoStatusEvent() {}

    public PedidoStatusEvent(String pedidoId,
                             String status,
                             ClienteDTO cliente,
                             List<ItemEntregaDTO> itens)
    {
        this.pedidoId = pedidoId;
        this.status = status;
        this.cliente = cliente;
        this.itens = itens;
        this.atualizadoEm = Instant.now();
    }

    public PedidoStatusEvent(String pedidoId, String status) {
        this.pedidoId = pedidoId;
        this.status = status;
        this.atualizadoEm = Instant.now();
    }

    public String getPedidoId() {return pedidoId;}
    public void setPedidoId(String pedidoId) {this.pedidoId = pedidoId;}

    public String getStatus() {return status;}
    public void setStatus(String status) {this.status = status;}

    public Instant getAtualizadoEm() {return atualizadoEm;}
    public void setAtualizadoEm(Instant atualizadoEm) {this.atualizadoEm = atualizadoEm;}

    public EnderecoDTO getEndereco() {return endereco;}
    public void setEndereco(EnderecoDTO endereco) {this.endereco = endereco;}

    public ClienteDTO getCliente() { return cliente; }
    public void setCliente(ClienteDTO cliente) { this.cliente = cliente; }

    public List<ItemEntregaDTO> getItens() { return itens; }
    public void setItens(List<ItemEntregaDTO> itens) { this.itens = itens; }

    public static class EnderecoDTO {
        private String rua;
        private String numero;
        private String cidade;
        private String estado;
        private String cep;
        private String complemento;

        public EnderecoDTO() {}

        public EnderecoDTO(String rua, String numero, String cidade, String estado, String cep, String complemento) {
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

    public static class ClienteDTO {
        private String nome;
        private String telefone;

        public ClienteDTO() {}

        public ClienteDTO(String nome, String telefone) {
            this.nome = nome;
            this.telefone = telefone;
        }

        public String getNome() { return nome; }
        public String getTelefone() { return telefone; }
    }

    public static class ItemEntregaDTO {
        private String descricao;
        private Double peso;
        private Double volume;

        public ItemEntregaDTO() {}

        public ItemEntregaDTO(String descricao, Double peso, Double volume) {
            this.descricao = descricao;
            this.peso = peso;
            this.volume = volume;
        }

        public String getDescricao() { return descricao; }
        public Double getPeso() { return peso; }
        public Double getVolume() { return volume; }
    }
}
