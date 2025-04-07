package com.estoqueige.estoqueige.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.estoqueige.estoqueige.models.Usuario;
import java.util.List;
import java.util.Optional;


@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByUsuLogin(String usuLogin);

    @Query(value = "SELECT * FROM usuario WHERE is_ativo = 1", nativeQuery = true)
    List<Usuario> buscarUsuariosAtivos();
}
