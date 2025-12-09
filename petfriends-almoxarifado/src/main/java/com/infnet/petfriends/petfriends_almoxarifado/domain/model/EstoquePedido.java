package com.infnet.petfriends.petfriends_almoxarifado.domain.model;

import com.infnet.petfriends.petfriends_almoxarifado.domain.model.enums.StatusAlmoxarifado;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "item_estoque")
public class EstoquePedido {
    @Id
    private String pedidoId;

    @ElementCollection
    @CollectionTable(name = "estoque_pedido_itens", joinColumns = @JoinColumn(name = "pedido_id"))
    private List<ItemEstoque> itens;

    @Enumerated(EnumType.STRING)
    private StatusAlmoxarifado status;

    protected EstoquePedido() {}

    public EstoquePedido(String pedidoId, List<ItemEstoque> itens) {
        this.pedidoId = pedidoId;
        this.itens = itens;
        this.status = StatusAlmoxarifado.RECEBIDO;
    }

    public void iniciarPreparacao() {
        this.status = StatusAlmoxarifado.EM_PREPARACAO;
    }

    public void finalizarPreparacao() {
        this.status = StatusAlmoxarifado.FINALIZADO;
    }

    public String getPedidoId() { return pedidoId; }
    public List<ItemEstoque> getItens() { return itens; }
    public StatusAlmoxarifado getStatus() { return status; }
}
