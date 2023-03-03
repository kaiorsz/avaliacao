package com.example.demo.servico;

import com.example.demo.entidade.Pauta;
import com.example.demo.entidade.SessaoVotacao;
import org.springframework.stereotype.Service;

public interface SessaoVotacaoServico {

    public SessaoVotacao criaSessaoVotacao(Integer min, Integer idPauta);
}
