package com.estoqueige.estoqueige.services;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.estoqueige.estoqueige.models.Usuario;
import com.estoqueige.estoqueige.repositories.UsuarioRepository;
import com.estoqueige.estoqueige.security.UserSpringSecurity;

@Service
public class UserDetailsImplServices implements UserDetailsService{

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UsuarioRepository usuarioRepository;

    UserDetailsImplServices(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> buscaUsuario = this.usuarioRepository.findByUsuLogin(username);
        System.out.println("Usuário encontrado? " + buscaUsuario.isPresent());
        Usuario usuario = buscaUsuario.orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado."));

        return new UserSpringSecurity(
            usuario.getUsuId(), 
            usuario.getUsuLogin(), 
            usuario.getUsuSenha(), 
            usuario.getUsuPerfil()
        );

    }
    
}
