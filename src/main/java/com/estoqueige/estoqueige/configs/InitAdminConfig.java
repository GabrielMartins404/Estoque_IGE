package com.estoqueige.estoqueige.configs;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.estoqueige.estoqueige.models.Usuario;
import com.estoqueige.estoqueige.models.enums.PerfisUsuario;
import com.estoqueige.estoqueige.repositories.UsuarioRepository;

//Essa configuração serve unicamente para cadastrar um usuário adm assim que a aplicação é iniciada
@Configuration
public class InitAdminConfig {
    @Bean
    public CommandLineRunner init(UsuarioRepository usuarioRepository, BCryptPasswordEncoder bCryptPasswordEncoder){
        return args -> {
            if(usuarioRepository.count() == 0){
                Usuario usuarioSuporte = new Usuario();
                usuarioSuporte.setUsuNome("Suporte IGE");
                usuarioSuporte.setUsuLogin("suporte@unifesspa");
                usuarioSuporte.setUsuSenha(bCryptPasswordEncoder.encode("suporte@102030"));
                usuarioSuporte.setUsuPerfil(PerfisUsuario.ADMIN);
                usuarioSuporte.setIsAtivo(true);

                usuarioRepository.save(usuarioSuporte);
                System.out.println("Usuário administrador criado.");
            }
        };
    }
        
}
