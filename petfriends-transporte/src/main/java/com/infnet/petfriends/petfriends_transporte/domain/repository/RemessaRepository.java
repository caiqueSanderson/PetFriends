package com.infnet.petfriends.petfriends_transporte.domain.repository;

import com.infnet.petfriends.petfriends_transporte.domain.model.Remessa;
import com.infnet.petfriends.petfriends_transporte.domain.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RemessaRepository extends JpaRepository<Remessa, String> {
    List<Remessa> findByStatus(Status status);
    Remessa findByPedidoId(String pedidoId);
}
