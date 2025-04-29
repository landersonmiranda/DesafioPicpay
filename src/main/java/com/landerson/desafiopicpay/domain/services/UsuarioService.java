package com.landerson.desafiopicpay.domain.services;

import com.landerson.desafiopicpay.api.dto.UsuarioDTO;
import com.landerson.desafiopicpay.domain.entities.TipoUsuario;
import com.landerson.desafiopicpay.domain.entities.Usuario;
import com.landerson.desafiopicpay.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UsuarioService {

    //pq autowired? fez isso no ultimo coodigo?
    @Autowired
    private UsuarioRepository usuarioRepository;

    public void verificarPagamento(Usuario remetente, BigDecimal valor) throws Exception{
        if(remetente.getTipoUsuario() == TipoUsuario.LOJISTA) {
            throw new Exception("Lojistas não enviam dinheiro, apenas recebem!");
        }
        if(remetente.getSaldo().compareTo(valor) < 0){
            throw new IllegalArgumentException("Saldo insuficiente");
        }
    }

    public void salvarUsuario(Usuario usuario) {
        System.out.println("Salvando usuário: " + usuario.getCPF()); // Depuração

        this.usuarioRepository.save(usuario);
    }

    // 🔥 MÉTODO QUE CRIA UM USUÁRIO A PARTIR DO DTO
    public UsuarioDTO criar(UsuarioDTO dto) {
        Usuario newUsuario = new Usuario(dto);
        this.salvarUsuario(newUsuario);
        return new UsuarioDTO(newUsuario);
    }


    public List<UsuarioDTO> getTodosUsuarios(){
        return this.usuarioRepository.findAll()
                .stream()
                .map(UsuarioDTO::new)
                .toList();
    }
}
