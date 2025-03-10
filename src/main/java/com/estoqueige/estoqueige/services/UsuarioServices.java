package com.estoqueige.estoqueige.services;

import com.estoqueige.estoqueige.models.Usuario;
import com.estoqueige.estoqueige.repositories.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServices {
    private final UsuarioRepository usuarioRepository;

    public UsuarioServices(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    /* Método services */
    public Usuario buscarUsuarioPorId(Long id) {
        Optional<Usuario> usuario = this.usuarioRepository.findById(id);
        return usuario.orElseThrow(() -> new RuntimeException("Não foi possivel encontrar o usuario"));
    }

    public List<Usuario> buscarTodosUsuarios() {
        List<Usuario> usuarios = this.usuarioRepository.findAll();
        return usuarios;
    }

    //Implementar método de login

    @Transactional
    public Usuario cadastrarUsuario(Usuario usuario) {
        usuario.setUsuId(null);
        return this.usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario atualizarUsuario(Usuario usuario) {
        Usuario newUsuario = this.buscarUsuarioPorId(usuario.getUsuId());

        //Copio todas as propriedades do usuario para newUsuario, menos o ID
        BeanUtils.copyProperties(usuario, newUsuario, "reqId");
        this.usuarioRepository.save(newUsuario);

        return newUsuario;
    }

    @Transactional
    public Usuario alterarStatusAtivoUsuario(Long id) {
        Usuario usuario = this.buscarUsuarioPorId(id);

        usuario.setIsAtivo(!usuario.getIsAtivo());
        return this.usuarioRepository.save(usuario);
    }
}
