package com.hugomarques.rinhabackend2023.pessoas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface PessoaRepository extends JpaRepository<Pessoa, UUID> {

    @Query(nativeQuery = true,
            value = "select p.id, p.apelido, p.nome, p.nascimento, p.stack from pessoas p " +
                    "where p.termo like concat('%', :term, '%')")
    List<Pessoa> findAllByTerm(@Param("term") final String term);

}
