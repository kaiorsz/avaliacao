package com.example.demo.servico.impl;

import com.example.demo.entidade.SessaoVotacao;
import com.example.demo.repositorio.PautaRepository;
import com.example.demo.repositorio.SessaoVotacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.demo.servico.SessaoVotacaoServico;

import java.time.Clock;
import java.util.Date;
@Service
public class SessaoVotacaoServicoImpl implements SessaoVotacaoServico {

    private Clock relogio = Clock.systemDefaultZone();

    @Autowired
    PautaRepository pautaRepository;

    @Autowired
    SessaoVotacaoRepository sessaoVotacaoRepository;

    public SessaoVotacaoServicoImpl(PautaRepository pautaRepository, SessaoVotacaoRepository sessaoVotacaoRepository) {
        this.pautaRepository = pautaRepository;
        this.sessaoVotacaoRepository = sessaoVotacaoRepository;
    }

    @Override
    public SessaoVotacao criaSessaoVotacao(Integer min, Integer idPauta) {
        SessaoVotacao sessaoVotacao = new SessaoVotacao();
        if (pautaRepository.findById(Long.valueOf(idPauta)).isPresent()) {
            sessaoVotacao.setPauta(pautaRepository.findById(Long.valueOf(idPauta)).get());
            Integer minutosSessao = min != null ? min : 1;
            sessaoVotacao.setDataInicio(new Date(relogio.millis()));
            sessaoVotacao.setDataFinal(new Date(sessaoVotacao.getDataInicio().getTime() + (minutosSessao * 60 * 1000)));
            sessaoVotacao = sessaoVotacaoRepository.save(sessaoVotacao);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possível encontrar a pauta informada!");
        }
        return sessaoVotacao;
    }

    public Clock getRelogio() {
        return relogio;
    }

    public void setRelogio(Clock relogio) {
        this.relogio = relogio;
    }
}
