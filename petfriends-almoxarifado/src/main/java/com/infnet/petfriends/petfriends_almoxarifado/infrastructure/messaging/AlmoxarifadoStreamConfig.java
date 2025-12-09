package com.infnet.petfriends.petfriends_almoxarifado.infrastructure.messaging;

import com.infnet.petfriends.petfriends_almoxarifado.domain.pedido.PedidoStatusEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
public class AlmoxarifadoStreamConfig {
    @Bean
    public Consumer<PedidoStatusEvent> pedidoStatusInput() {
        return event -> {
            System.out.println("[Almoxarifado] Evento recebido: " + event);
        };
    }
}
