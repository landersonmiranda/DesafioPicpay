package com.landerson.desafiopicpay.domain.entities;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_pagamento")
public class Pagamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal valor;


    @ManyToOne
    @JoinColumn(name="remetente_id")
    private Usuario usuarioEnvio;

    @ManyToOne
    @JoinColumn(name="beneficiario_id")
    private Usuario usuarioRecebimento;;
    private LocalDateTime time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Usuario getUsuarioEnvio() {
        return usuarioEnvio;
    }

    public void setUsuarioEnvio(Usuario usuarioEnvio) {
        this.usuarioEnvio = usuarioEnvio;
    }

    public Usuario getUsuarioRecebimento() {
        return usuarioRecebimento;
    }

    public void setUsuarioRecebimento(Usuario usuarioRecebimento) {
        this.usuarioRecebimento = usuarioRecebimento;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime timestamp) {
        this.time = timestamp;
    }
}
