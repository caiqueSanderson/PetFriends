package com.infnet.petfriends.petfriends_transporte.application.service;

import com.infnet.petfriends.petfriends_transporte.domain.model.EnderecoEntrega;
import com.infnet.petfriends.petfriends_transporte.domain.model.Remessa;
import com.infnet.petfriends.petfriends_transporte.domain.model.enums.Status;
import com.infnet.petfriends.petfriends_transporte.domain.pedido.event.PedidoStatusEvent;
import com.infnet.petfriends.petfriends_transporte.domain.repository.RemessaRepository;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;

@Service
public class TransporteEventService {
    private final RemessaRepository repository;
    private final StreamBridge streamBridge;

    public TransporteEventService(RemessaRepository repository, StreamBridge bridge) {
        this.repository = repository;
        this.streamBridge = bridge;
    }

    private EnderecoEntrega toEnderecoEntrega(PedidoStatusEvent event) {
        PedidoStatusEvent.EnderecoDTO dto = event.getEndereco();
        if (dto == null) {
            return new EnderecoEntrega("Rua X", "123", "Cidade", "UF", "00000-000", "Casa");
        }
        return new EnderecoEntrega(dto.getRua(), dto.getNumero(), dto.getCidade(),
                dto.getEstado(), dto.getCep(), dto.getComplemento());
    }

    public void processarPedidoStatus(PedidoStatusEvent event) {
        String status = event.getStatus();

        if("EM_PREPARACAO".equalsIgnoreCase(status)) {
            System.out.println("[Transporte] Pedido EM_PREPARACAO recebido: " + event.getPedidoId());

            EnderecoEntrega endereco = toEnderecoEntrega(event);

            Remessa remessa = new Remessa(event.getPedidoId(), endereco);
            repository.save(remessa);

            System.out.println("Enviando status EM_TRANSITO para Pedidos");

            streamBridge.send("novoStatusOutput",
                    new PedidoStatusEvent(event.getPedidoId(), "EM_TRANSITO"));
        } else if ("ENTREGUE".equalsIgnoreCase(status)) {
            Remessa remessa = repository.findByPedidoId(event.getPedidoId());
            if (remessa != null) {
                remessa.marcarEntregue();
                repository.save(remessa);
                streamBridge.send("novoStatusOutput",
                        new PedidoStatusEvent(event.getPedidoId(), "ENTREGUE"));
            } else {
                System.out.println("[Transporte] remessa não encontrada para marcar entregue: " + event.getPedidoId());
            }
        } else if ("DEVOLVIDO".equalsIgnoreCase(status)) {
            Remessa remessa = repository.findByPedidoId(event.getPedidoId());
            if (remessa != null) {
                remessa.marcarDevolvido();
                repository.save(remessa);
                streamBridge.send("novoStatusOutput",
                        new PedidoStatusEvent(event.getPedidoId(), "DEVOLVIDO"));
            }
        } else {
            System.out.println("[Transporte] status recebido não tratado: " + status);
        }
    }

    public void verificarExtravioEPublicar() {
        repository.findByStatus(Status.EM_TRANSITO).forEach(remessa -> {
            if (remessa.getCriadoEm().plusSeconds(30L * 24 * 3600).isBefore(java.time.Instant.now())) {
                remessa.marcarExtraviado();
                repository.save(remessa);
                streamBridge.send("novoStatusOutput",
                        new PedidoStatusEvent(remessa.getPedidoId(), "EXTRAVIADO"));

                System.out.println("[Transporte] remessa extraviada publicada pedidoId=" + remessa.getPedidoId());
            }
        });
    }
}
