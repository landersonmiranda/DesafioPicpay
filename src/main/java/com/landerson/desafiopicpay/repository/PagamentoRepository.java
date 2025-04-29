package com.landerson.desafiopicpay.repository;


import com.landerson.desafiopicpay.domain.entities.Pagamento;
import com.landerson.desafiopicpay.domain.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {

}
