package com.example.demo.servico.impl;

import com.example.demo.entidade.Associado;
import com.example.demo.entidade.SessaoVotacao;
import com.example.demo.entidade.Voto;
import com.example.demo.entidade.dto.VotoDTO;
import com.example.demo.repositorio.AssociadoRepository;
import com.example.demo.repositorio.PautaRepository;
import com.example.demo.repositorio.SessaoVotacaoRepository;
import com.example.demo.repositorio.VotoRepository;
import com.example.demo.servico.RestService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import com.example.demo.servico.VotoServico;

import java.util.Date;
import java.util.List;

@Service
public class VotoServicoImpl<T> implements VotoServico<T> {
    @Autowired
    PautaRepository pautaRepository;
    @Autowired
    VotoRepository votoRepository;
    @Autowired
    SessaoVotacaoRepository sessaoVotacaoRepository;
    @Autowired
    AssociadoRepository associadoRepository;
    @Autowired
    RestService restService;

    @Override
    public Voto criaVoto(VotoDTO votoDTO) {

        Voto voto = new Voto();
        if (!associadoRepository.findById(Long.valueOf(votoDTO.getIdAssociado())).isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possível encontrar o associado informada!");
        }
        Associado associado = associadoRepository.findById(Long.valueOf(votoDTO.getIdAssociado())).get();
        if (!sessaoVotacaoRepository.findById(Long.valueOf(votoDTO.getIdSessaoVotacao())).isPresent()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Não foi possível encontrar a sessao de votação informada!");
        }
        if (sessaoVotacaoRepository.findById(Long.valueOf(votoDTO.getIdSessaoVotacao())).get().getDataFinal().before(new Date(System.currentTimeMillis()))) {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "A sessao informada não aceita mais votos!");
        }
        SessaoVotacao sessaoVotacao = sessaoVotacaoRepository.findById(Long.valueOf(votoDTO.getIdSessaoVotacao())).get();
        if (!(votoDTO.getDescricaoVoto().toLowerCase().equals("sim") || votoDTO.getDescricaoVoto().toLowerCase().equals("não"))) {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Nâo foi possível identificar o voto. Indique o voto com \"sim\" ou \"não\"!");
        }
        if (!votoRepository.findAllByAssociadoAndSessaoVotacao_Pauta(associado, sessaoVotacao.getPauta()).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "O associado informado já votou na sessão informada");
        }
        voto.setVoto(votoDTO.getDescricaoVoto().toLowerCase().equals("não") ? false : true);
        voto.setAssociado(associado);
        voto.setSessaoVotacao(sessaoVotacao);
        voto = votoRepository.save(voto);

        return voto;
    }

    @Override
    public Boolean podeVotar(String cpf) {
        String url = "https://user-info.herokuapp.com/users/" + cpf.replaceAll("\\D", "");

        String resposta = restService.getJSON(url);

        if (!resposta.contains("status")) {
            return false;
        }

        JSONObject jsonObject = new JSONObject(resposta);

        if (jsonObject.get("status") != null && jsonObject.get("status").toString().toUpperCase().equals("ABLE_TO_VOTE")) {
            return true;
        }

        return false;
    }
}
