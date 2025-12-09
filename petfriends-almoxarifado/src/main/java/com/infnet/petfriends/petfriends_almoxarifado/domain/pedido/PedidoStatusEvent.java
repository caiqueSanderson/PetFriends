package com.infnet.petfriends.petfriends_almoxarifado.domain.pedido;

import com.infnet.petfriends.petfriends_almoxarifado.domain.model.ItemEstoque;

import java.time.Instant;
import java.util.List;

public class PedidoStatusEvent {
    private String pedidoId;
    private String status;
    private List<ItemEstoque> itens;
    private Instant atualizadoEm;

    public PedidoStatusEvent() {}

    public PedidoStatusEvent(String pedidoId, String status, List<ItemEstoque> itens) {
        this.pedidoId = pedidoId;
        this.status = status;
        this.itens = itens;
        this.atualizadoEm = Instant.now();
    }

    public String getPedidoId() {return pedidoId;}
    public String getStatus() {return status;}
    public Instant getAtualizadoEm() {return atualizadoEm;}

    public List<ItemEstoque> getItens() { return itens; }
    public void setItens(List<ItemEstoque> itens) { this.itens = itens; }
}
