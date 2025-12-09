package com.infnet.petfriends.petfriends_almoxarifado.domain.model;

import jakarta.persistence.Embeddable;

@Embeddable
public class ItemEstoque {
    private String produtoId;
    private Integer quantidade;

    protected ItemEstoque() {}

    public ItemEstoque(String produtoId, Integer quantidade) {
        this.produtoId = produtoId;
        this.quantidade = quantidade;
    }
}
