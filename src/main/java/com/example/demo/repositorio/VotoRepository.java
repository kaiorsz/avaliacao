package com.example.demo.repositorio;

import com.example.demo.entidade.Associado;
import com.example.demo.entidade.Pauta;
import com.example.demo.entidade.SessaoVotacao;
import com.example.demo.entidade.Voto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VotoRepository extends JpaRepository<Voto, Long> {

    List<Voto> findAllByAssociadoAndSessaoVotacao_Pauta(Associado associado, Pauta pauta);

}
