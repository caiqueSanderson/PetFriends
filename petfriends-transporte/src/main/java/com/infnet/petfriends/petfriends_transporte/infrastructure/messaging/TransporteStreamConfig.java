package com.infnet.petfriends.petfriends_transporte.infrastructure.messaging;

import com.infnet.petfriends.petfriends_transporte.application.service.TransporteEventService;
import com.infnet.petfriends.petfriends_transporte.domain.pedido.event.PedidoStatusEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.function.Consumer;

@Configuration
public class TransporteStreamConfig {
    private final TransporteEventService transporteEventService;

    public TransporteStreamConfig(TransporteEventService transporteEventService) {
        this.transporteEventService = transporteEventService;
    }

    @Bean
    public Consumer<PedidoStatusEvent> pedidoStatusInput() {
        return event -> {
            System.out.println("[Transporte] Evento recebido: " + event);
            transporteEventService.processarPedidoStatus(event);
        };
    }
}
