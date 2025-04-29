package com.landerson.desafiopicpay.api.dto;

import com.landerson.desafiopicpay.domain.entities.TipoUsuario;
import com.landerson.desafiopicpay.domain.entities.Usuario;

import java.math.BigDecimal;

public class UsuarioDTO {

    String nomeCompleto;

    String cpf;
  //  @NotEmpty
  //  @Email(message="O email não é válido!")
    String email;

  //  @Size(min=5,max=20, message="A senha deve ter entre 5 e 20 caracteres")
    String senha;


    BigDecimal valor;

    TipoUsuario tipoUsuario;

    public UsuarioDTO() {}


    public UsuarioDTO(Usuario usuario) {
        this.nomeCompleto = usuario.getNomeCompleto();
        this.cpf = usuario.getCPF();
        this.email = usuario.getEmail();
        this.senha = usuario.getSenha();
        this.valor = usuario.getSaldo();
        this.tipoUsuario = usuario.getTipoUsuario();
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }


    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
