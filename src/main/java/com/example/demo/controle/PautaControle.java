package com.example.demo.controle;

import com.example.demo.entidade.Pauta;
import com.example.demo.entidade.SessaoVotacao;
import com.example.demo.entidade.Voto;
import com.example.demo.entidade.dto.VotoDTO;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.servico.PautaServico;
import com.example.demo.servico.SessaoVotacaoServico;
import com.example.demo.servico.VotoServico;

@RestController
@RequestMapping("/pauta")
public class PautaControle {

    @Autowired
    private PautaServico pautaServico;
    @Autowired
    private SessaoVotacaoServico sessaoVotacaoServico;
    @Autowired
    private VotoServico votoServico;

    @PostMapping
    public ResponseEntity<?> cria(@RequestParam String descricao) {
        return new ResponseEntity<Pauta>(pautaServico.cria(descricao), HttpStatus.OK);
    }

    @PostMapping("/sessaoVotacao")
    public ResponseEntity<?> sessaoVotacao(@RequestParam(required = false) @Parameter (description = "Quantidade de minutos em número inteiro que a sessão de votação irá durar") Integer minutos,
                                           @RequestParam Integer idPauta) {
        return new ResponseEntity<SessaoVotacao>(sessaoVotacaoServico.criaSessaoVotacao(minutos, idPauta), HttpStatus.OK);
    }

    @PostMapping("/voto")
    public ResponseEntity<?> criaVoto(@RequestBody VotoDTO votoDTO) {
            return new ResponseEntity<Voto>(votoServico.criaVoto(votoDTO), HttpStatus.OK);
    }

    @GetMapping("/{cpf}")
    public ResponseEntity<?> podeVotar(@PathVariable String cpf) {
        return new ResponseEntity<Boolean>(votoServico.podeVotar(cpf), HttpStatus.OK);
    }

}
