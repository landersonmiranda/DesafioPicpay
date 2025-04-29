package com.landerson.desafiopicpay.domain.entities;

import com.landerson.desafiopicpay.api.dto.UsuarioDTO;
import jakarta.persistence.*;

import java.math.BigDecimal;


@Entity
@Table(name = "tb_usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nomeCompleto;


    @Column(nullable = false, unique = true)
    private String CPF;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING) //confirmar
    private TipoUsuario tipoUsuario;

    private BigDecimal saldo;

    public Usuario() {
    }

    public Usuario(UsuarioDTO dto ){
        this.nomeCompleto = dto.getNomeCompleto();
        this.saldo = dto.getValor();
        this.CPF = dto.getCpf();
        this.tipoUsuario = dto.getTipoUsuario();
        this.senha = dto.getSenha();
        this.email = dto.getEmail();

    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getCPF() {
        return CPF;
    }

    public void setCPF(String CPF) {
        this.CPF = CPF;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
