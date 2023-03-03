package com.example.demo.servico.impl;

import com.example.demo.entidade.Pauta;
import com.example.demo.repositorio.PautaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.servico.PautaServico;

@Service
public class PautaServicoImpl <T> implements PautaServico<T> {

    private PautaRepository pautaRepository;

    public PautaServicoImpl(PautaRepository pautaRepository) {
        this.pautaRepository = pautaRepository;
    }

    @Override
    public Pauta cria(String descricao) {
        Pauta pauta = new Pauta();
        pauta.setDescricao(descricao);
        return pautaRepository.save(pauta);
    }
}
