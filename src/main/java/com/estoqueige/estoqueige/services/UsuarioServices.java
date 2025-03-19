package com.estoqueige.estoqueige.services;

import com.estoqueige.estoqueige.EstoqueIgeApplication;
import com.estoqueige.estoqueige.controllers.FaculdadeController;
import com.estoqueige.estoqueige.models.Usuario;
import com.estoqueige.estoqueige.models.enums.PerfisUsuario;
import com.estoqueige.estoqueige.repositories.UsuarioRepository;
import com.estoqueige.estoqueige.security.JWTutil;
import com.estoqueige.estoqueige.security.UserSpringSecurity;
import com.estoqueige.estoqueige.services.exceptions.ErroAoBuscarObjetos;
import com.estoqueige.estoqueige.services.exceptions.ErroAutorizacao;
import com.estoqueige.estoqueige.services.exceptions.ErroCamposFixos;
import com.estoqueige.estoqueige.services.exceptions.ErroValidacoesObjRepetidos;

import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UsuarioServices {


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder; //Aqui serve para criptografar
    
    private final UsuarioRepository usuarioRepository;

    public UsuarioServices(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;

    }

    /* Método services */
    public boolean validarUsuario(String mensagem){
        UserSpringSecurity usuarioLogado = autenticado();
        //Verifico se o usuário está logado, ou se não é admin
        if(Objects.nonNull(usuarioLogado) && usuarioLogado.hasRole(PerfisUsuario.ADMIN)){
            return true;
        }else{
            throw new ErroAutorizacao(mensagem);
        }
    }

    public Usuario buscarUsuarioPorId(Long id) {
        //Um usuário que não é adm deverá ver somente as informações dele
        UserSpringSecurity usuarioLogado = autenticado();
        //Verifico se o usuário está logado, ou se não é admin ou se está buscando um usuário cujo id não seja o mesmo do usuário, dará erro
        if(!Objects.nonNull(usuarioLogado) || !usuarioLogado.hasRole(PerfisUsuario.ADMIN) && !id.equals(usuarioLogado.getId())){
            throw new ErroAutorizacao("Usuário não tem permissão para buscar outros usuários");
        }

        Optional<Usuario> usuario = this.usuarioRepository.findById(id);
        return usuario.orElseThrow(() -> new ErroAoBuscarObjetos("Não foi possivel encontrar o usuario de id: "+id));
    }

    public List<Usuario> buscarTodosUsuarios() {
        if(validarUsuario("Usuário não tem permissão para buscar outros usuários!!!")){
            List<Usuario> usuarios = this.usuarioRepository.findAll();
            return usuarios;
        }
        return null;
        
    }

    //Implementar método de login

    @Transactional
    public Usuario cadastrarUsuario(Usuario usuario) {
        
        if(this.usuarioRepository.findByUsuLogin(usuario.getUsuLogin()).isPresent()){
            throw new ErroValidacoesObjRepetidos("Já existe um usuário cadastrado com esse login! Realizar login, por gentileza");
        }else{
            usuario.setUsuId(null);
            usuario.setUsuSenha(this.bCryptPasswordEncoder.encode(usuario.getUsuSenha())); //Esse método criptografa a senha

            //Esse campo não está validando corretamente
            if(usuario.getUsuPerfil() == PerfisUsuario.ADMIN){
                usuario.setUsuPerfil(PerfisUsuario.ADMIN);
            }else if(usuario.getUsuPerfil() == PerfisUsuario.ALMOXARIFADO || usuario.getUsuPerfil() == null){
                usuario.setUsuPerfil(PerfisUsuario.ALMOXARIFADO);
            }else{
                throw new ErroCamposFixos("O perfil de usuário é inválido");
            }
            
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
        if(validarUsuario("Usuário não tem permissão para inativar usuários")){
            Usuario usuario = this.buscarUsuarioPorId(id);

            usuario.setIsAtivo(!usuario.getIsAtivo());
            return this.usuarioRepository.save(usuario);    
        }

        return null;
        
    }



    //Função serve para verificar se o usuário está autenticado
    public static UserSpringSecurity autenticado(){
        try {
            return (UserSpringSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        } catch (Exception e) {
            return null;
        }
    }
}
