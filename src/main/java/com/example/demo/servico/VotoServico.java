package com.example.demo.servico;

import com.example.demo.entidade.Pauta;
import com.example.demo.entidade.Voto;
import com.example.demo.entidade.dto.VotoDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface VotoServico<T> {

    public Voto criaVoto(VotoDTO votoDTO);

    Boolean podeVotar(String cpf);
}
