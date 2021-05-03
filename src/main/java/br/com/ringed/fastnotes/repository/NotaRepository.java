package br.com.ringed.fastnotes.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.ringed.fastnotes.entity.Nota;

public interface NotaRepository extends JpaRepository<Nota, Long>{

}
