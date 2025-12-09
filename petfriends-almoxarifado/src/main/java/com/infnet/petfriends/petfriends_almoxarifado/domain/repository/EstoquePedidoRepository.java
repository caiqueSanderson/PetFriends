package com.infnet.petfriends.petfriends_almoxarifado.domain.repository;

import com.infnet.petfriends.petfriends_almoxarifado.domain.model.EstoquePedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstoquePedidoRepository extends JpaRepository<EstoquePedido, String> {
}
