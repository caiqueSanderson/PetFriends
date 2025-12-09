package com.infnet.petfriends.petfriends_transporte.domain.model;

import com.infnet.petfriends.petfriends_transporte.domain.model.enums.Status;
import jakarta.persistence.*;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "remessa")
public class Remessa {
    @Id
    private String id;

    private String pedidoId;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Embedded
    private EnderecoEntrega endereco;

    private Instant criadoEm;
    private Instant atualizadaEm;

    protected Remessa() {}

    public Remessa(String pedidoId, EnderecoEntrega endereco) {
        this.id = UUID.randomUUID().toString();
        this.pedidoId = pedidoId;
        this.status = Status.EM_TRANSITO;
        this.endereco = endereco;
        this.criadoEm = Instant.now();
        this.atualizadaEm = Instant.now();
    }

    public void marcarEntregue() {
        this.status = Status.ENTREGUE;
        this.atualizadaEm = Instant.now();
    }

    public void marcarDevolvido() {
        this.status = Status.DEVOLVIDO;
        this.atualizadaEm = Instant.now();
    }

    public void marcarExtraviado() {
        this.status = Status.EXTRAVIADO;
        this.atualizadaEm = Instant.now();
    }

    public String getId() { return id; }
    public String getPedidoId() { return pedidoId; }
    public Status getStatus() { return status; }
    public EnderecoEntrega getEndereco() { return endereco; }
    public Instant getCriadoEm() { return criadoEm; }
    public Instant getAtualizadaEm() {return atualizadaEm;}
}
