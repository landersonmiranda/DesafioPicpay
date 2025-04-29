package com.landerson.desafiopicpay.repository;

import com.landerson.desafiopicpay.domain.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    //Optional<user> ou public?
   // public Usuario save (Usuario usu);
    Usuario  findUsuarioByCPF(String CPF);

    public Usuario findUsuarioById(Long id);

}
