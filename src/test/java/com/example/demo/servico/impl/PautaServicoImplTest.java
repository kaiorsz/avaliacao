package com.example.demo.servico.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.example.demo.entidade.Pauta;
import com.example.demo.repositorio.PautaRepository;
import com.example.demo.servico.PautaServico;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class PautaServicoImplTest {

    private PautaServico<Pauta> pautaServico;

    @Mock
    private PautaRepository pautaRepository;

    @BeforeEach
    public void setup() {
        pautaServico = new PautaServicoImpl<>(pautaRepository);
    }

    @Test
    public void cria() {
        String descricao = "Teste descrição pauta";

        Pauta pautaEsperada = new Pauta();
        pautaEsperada.setDescricao(descricao);

        when(pautaRepository.save(pautaEsperada)).thenReturn(pautaEsperada);

        Pauta pautaCriada = pautaServico.cria(descricao);

        assertEquals(pautaEsperada, pautaCriada);
    }
}
