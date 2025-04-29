package com.landerson.desafiopicpay.api.controllers;


import com.landerson.desafiopicpay.api.dto.PagamentoDTO;
import com.landerson.desafiopicpay.domain.entities.Pagamento;
import com.landerson.desafiopicpay.domain.services.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pagamento")
public class PagamentoController {

    @Autowired
    private PagamentoService pagamentoService;

    @PostMapping
    public ResponseEntity<PagamentoDTO> criarPagamento(@RequestBody PagamentoDTO pagamento) throws Exception {
        Pagamento novoPagamento = pagamentoService.criarPagamento(pagamento);
        PagamentoDTO resposta = new PagamentoDTO(novoPagamento); // Converte entidade para DTO
        return ResponseEntity.ok(resposta);
    }

}
