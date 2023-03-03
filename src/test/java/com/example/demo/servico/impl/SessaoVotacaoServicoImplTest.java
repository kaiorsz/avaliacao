package com.example.demo.servico.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.Clock;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

import com.example.demo.entidade.Pauta;
import com.example.demo.entidade.SessaoVotacao;
import com.example.demo.repositorio.PautaRepository;
import com.example.demo.repositorio.SessaoVotacaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@ExtendWith(MockitoExtension.class)
public class SessaoVotacaoServicoImplTest {

    private SessaoVotacaoServicoImpl sessaoVotacaoServico;

    @Mock
    private PautaRepository pautaRepository;

    @Mock
    private SessaoVotacaoRepository sessaoVotacaoRepository;

    private Clock relogioFixo;

    @BeforeEach
    public void setup() {
        relogioFixo = Clock.fixed(Instant.now(), ZoneId.systemDefault());
        sessaoVotacaoServico = new SessaoVotacaoServicoImpl(pautaRepository, sessaoVotacaoRepository);
        sessaoVotacaoServico.setRelogio(relogioFixo);
    }

    @Test
    public void criaSessaoVotacao() {
        Integer min = 1;
        Integer idPauta = 1;

        Pauta pauta = new Pauta();
        pauta.setId(idPauta);

        when(pautaRepository.findById(Long.valueOf(pauta.getId()))).thenReturn(Optional.of(pauta));

        SessaoVotacao sessaoVotacaoEsperada = new SessaoVotacao();
        sessaoVotacaoEsperada.setPauta(pauta);
        sessaoVotacaoEsperada.setDataInicio(new Date(relogioFixo.millis()));
        sessaoVotacaoEsperada.setDataFinal(new Date(sessaoVotacaoEsperada.getDataInicio().getTime() + (min * 60 * 1000)));

        when(sessaoVotacaoRepository.save(sessaoVotacaoEsperada)).thenReturn(sessaoVotacaoEsperada);

        SessaoVotacao sessaoVotacaoCriada = sessaoVotacaoServico.criaSessaoVotacao(min, idPauta);

        assertEquals(sessaoVotacaoEsperada, sessaoVotacaoCriada);
    }

    @Test
    public void criaSessaoVotacaoExcessao() {
        Integer min = null;
        Integer idPauta = 1;

        when(pautaRepository.findById(Long.valueOf(idPauta))).thenReturn(Optional.empty());

        assertThrows(ResponseStatusException.class, () -> sessaoVotacaoServico.criaSessaoVotacao(min, idPauta),
                "Não foi possível encontrar a pauta informada!");

        ResponseStatusException exception = assertThrows(ResponseStatusException.class,
                () -> sessaoVotacaoServico.criaSessaoVotacao(min, idPauta));

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatusCode());
        assertEquals("Não foi possível encontrar a pauta informada!", exception.getReason());
    }
}