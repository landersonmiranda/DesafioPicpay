package com.landerson.desafiopicpay.api.dto;

import com.landerson.desafiopicpay.domain.entities.Pagamento;

import java.math.BigDecimal;

public class PagamentoDTO {
    private BigDecimal valor;
    private Long remetenteId;

    private Long beneficiarioId;

    public PagamentoDTO() { }  // Construtor padrão

    public PagamentoDTO(BigDecimal valor, Long remetenteId, Long beneficiarioId) {
        this.valor = valor;
        this.remetenteId = remetenteId;
        this.beneficiarioId = beneficiarioId;
    }
    public PagamentoDTO(Pagamento pagamento) {
        this.valor = pagamento.getValor();
        this.remetenteId = pagamento.getUsuarioEnvio().getId();
        this.beneficiarioId = pagamento.getUsuarioRecebimento().getId();
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Long getRemetenteId() {
        return remetenteId;
    }

    public void setRemetenteId(Long remetenteId) {
        this.remetenteId = remetenteId;
    }

    public Long getBeneficiarioId() {
        return beneficiarioId;
    }

    public void setBeneficiarioId(Long beneficiarioId) {
        this.beneficiarioId = beneficiarioId;
    }

}
