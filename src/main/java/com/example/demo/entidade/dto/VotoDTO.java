package com.example.demo.entidade.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.constraints.NotNull;

public class VotoDTO {

    @Schema(description = "Descrição do voto (sim ou não)", example = "sim")
    private String descricaoVoto;
    @NotNull
    private Integer idSessaoVotacao;
    @NotNull
    private Integer idAssociado;

    public String getDescricaoVoto() {
        return descricaoVoto;
    }

    public void setDescricaoVoto(String descricaoVoto) {
        this.descricaoVoto = descricaoVoto;
    }

    public Integer getIdSessaoVotacao() {
        return idSessaoVotacao;
    }

    public void setIdSessaoVotacao(Integer idSessaoVotacao) {
        this.idSessaoVotacao = idSessaoVotacao;
    }

    public Integer getIdAssociado() {
        return idAssociado;
    }

    public void setIdAssociado(Integer idAssociado) {
        this.idAssociado = idAssociado;
    }
}
