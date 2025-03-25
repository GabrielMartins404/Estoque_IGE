package com.estoqueige.estoqueige.services;

import com.estoqueige.estoqueige.models.Usuario;
import com.estoqueige.estoqueige.models.enums.PerfisUsuario;
import com.estoqueige.estoqueige.repositories.UsuarioRepository;
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

import java.util.List;
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
    public Boolean validarUsuario(String mensagem) {
        UserSpringSecurity usuarioLogado = autenticado();

        // Verifica se o usuário está autenticado
        if (usuarioLogado == null) {
            throw new ErroAutorizacao(mensagem);
        }
    
        // Verifica se o usuário tem perfil de ADMIN
        if (usuarioLogado.hasRole(PerfisUsuario.ADMIN)) {
            return true;
        }

        throw new ErroAutorizacao(mensagem);
    }

    public Usuario buscarUsuarioPorId(Long id) {
        //Um usuário que não é adm deverá ver somente as informações dele
        UserSpringSecurity usuarioLogado = autenticado();
        //Verifico se o usuário está logado, ou se não é admin ou se está buscando um usuário cujo id não seja o mesmo do usuário, dará erro
        if (usuarioLogado == null || (!usuarioLogado.hasRole(PerfisUsuario.ADMIN) && !id.equals(usuarioLogado.getId()))) {
            throw new ErroAutorizacao("Usuário não tem permissão para buscar outros usuários");
        }

        Optional<Usuario> usuario = this.usuarioRepository.findById(id);
        return usuario.orElseThrow(() -> new ErroAoBuscarObjetos("Não foi possivel encontrar o usuario de id: "+id));
    }

    public List<Usuario> buscarTodosUsuarios() {
        
        if(!validarUsuario("Usuário não tem permissão para buscar outros usuários!!!")){
            return null;
        }
        
        List<Usuario> usuarios = this.usuarioRepository.findAll();
        return usuarios;
        
    }

    @Transactional
    public Usuario cadastrarUsuario(Usuario usuario) {
        
        if(this.usuarioRepository.findByUsuLogin(usuario.getUsuLogin()).isPresent()){
            throw new ErroValidacoesObjRepetidos("Já existe um usuário cadastrado com esse login! Realizar login, por gentileza");
        }else{
            usuario.setUsuId(null);
            usuario.setUsuSenha(this.bCryptPasswordEncoder.encode(usuario.getUsuSenha())); //Esse método criptografa a senha

            // Validação do perfil de usuário
            if (usuario.getUsuPerfil() == null) {
                //Se o usuário passado for inválido, o mesmo será almoxarifado
                usuario.setUsuPerfil(PerfisUsuario.ALMOXARIFADO);
            } else if (usuario.getUsuPerfil() != PerfisUsuario.ADMIN && usuario.getUsuPerfil() != PerfisUsuario.ALMOXARIFADO) {
                throw new ErroCamposFixos("O perfil de usuário é inválido");
            }
            
            return this.usuarioRepository.save(usuario);
        }
    }

    @Transactional
    public Usuario atualizarUsuario(Usuario usuario) {
        Usuario newUsuario = this.buscarUsuarioPorId(usuario.getUsuId());

        if (usuario.getUsuNome() != null && !usuario.getUsuNome().isBlank()) {
            newUsuario.setUsuNome(usuario.getUsuNome());
        }
        
        if (usuario.getUsuPerfil() != null) {
            newUsuario.setUsuPerfil(usuario.getUsuPerfil());
        }
        
        if (usuario.getUsuSenha() != null && !usuario.getUsuSenha().isBlank()) {
            newUsuario.setUsuSenha(this.bCryptPasswordEncoder.encode(usuario.getUsuSenha()));
        }
        
        if (usuario.getIsAtivo() != null) {
            newUsuario.setIsAtivo(usuario.getIsAtivo());
        }

        this.usuarioRepository.save(newUsuario);

        return newUsuario;
    }

    @Transactional
    public Usuario alterarStatusAtivoUsuario(Long id) {
        if(!validarUsuario("Usuário não tem permissão para inativar usuários")){
            return null;
        }
        Usuario usuario = this.buscarUsuarioPorId(id);

        usuario.setIsAtivo(!usuario.getIsAtivo());
        return this.usuarioRepository.save(usuario);   
        
        
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
