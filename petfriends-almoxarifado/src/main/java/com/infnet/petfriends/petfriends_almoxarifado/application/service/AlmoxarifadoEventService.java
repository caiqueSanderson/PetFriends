package com.infnet.petfriends.petfriends_almoxarifado.application.service;

import com.infnet.petfriends.petfriends_almoxarifado.domain.model.EstoquePedido;
import com.infnet.petfriends.petfriends_almoxarifado.domain.pedido.PedidoStatusEvent;
import com.infnet.petfriends.petfriends_almoxarifado.domain.repository.EstoquePedidoRepository;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
public class AlmoxarifadoEventService {
    private final EstoquePedidoRepository repository;
    private final StreamBridge streamBridge;

    public AlmoxarifadoEventService(EstoquePedidoRepository repository, StreamBridge bridge) {
        this.repository = repository;
        this.streamBridge = bridge;
    }

    public void processarPedidoFechado(PedidoStatusEvent event) {

        System.out.println("[Almoxarifado] Pedido fechado recebido: " + event.getPedidoId());

        EstoquePedido estoque = new EstoquePedido(event.getPedidoId(), event.getItens());
        estoque.iniciarPreparacao();
        repository.save(estoque);

        System.out.println("Enviando status EM_PREPARACAO para Pedidos");

        streamBridge.send("novoStatusOutput",
                new PedidoStatusEvent(event.getPedidoId(), "EM_PREPARACAO", null));
    }
}
