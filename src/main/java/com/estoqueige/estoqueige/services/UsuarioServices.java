package com.estoqueige.estoqueige.services;

import com.estoqueige.estoqueige.models.Usuario;
import com.estoqueige.estoqueige.repositories.UsuarioRepository;
import com.estoqueige.estoqueige.services.exceptions.ErroAoBuscarObjetos;
import com.estoqueige.estoqueige.services.exceptions.ErroValidacoesObjRepetidos;

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
        return usuario.orElseThrow(() -> new ErroAoBuscarObjetos("Não foi possivel encontrar o usuario de id: "+id));
    }

    public List<Usuario> buscarTodosUsuarios() {
        List<Usuario> usuarios = this.usuarioRepository.findAll();
        return usuarios;
    }

    //Implementar método de login

    @Transactional
    public Usuario cadastrarUsuario(Usuario usuario) {
        
        if(this.usuarioRepository.findByUsuLogin(usuario.getUsuLogin()).isPresent()){
            throw new ErroValidacoesObjRepetidos("Já existe um usuário cadastrado com esse login! Realizar login, por gentileza");
        }else{
            usuario.setUsuId(null);
            return this.usuarioRepository.save(usuario);
        }
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
