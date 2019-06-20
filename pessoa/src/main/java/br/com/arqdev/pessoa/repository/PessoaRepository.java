package br.com.arqdev.pessoa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.arqdev.pessoa.entity.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{
}
