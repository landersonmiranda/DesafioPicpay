package com.landerson.desafiopicpay.api.controllers;


import com.landerson.desafiopicpay.api.dto.UsuarioDTO;
import com.landerson.desafiopicpay.domain.entities.Usuario;
import com.landerson.desafiopicpay.domain.services.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    public final UsuarioService service;

    public UsuarioController(UsuarioService service){
        this.service = service;
    }
    @PostMapping
    public ResponseEntity<UsuarioDTO> criarUsuario(@RequestBody UsuarioDTO dto){
        UsuarioDTO novoUsuario = service.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoUsuario);
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> getTodosUsuarios(){
        List<UsuarioDTO> usuarios = this.service.getTodosUsuarios();
        return ResponseEntity.ok(usuarios);
    }
}
