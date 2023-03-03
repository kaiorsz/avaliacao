package com.example.demo.servico;

import com.example.demo.entidade.Pauta;

public interface PautaServico<T> {

    public Pauta cria(String descricao);
}
