package com.example.demo.entidade;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Voto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    private Associado associado;
    @OneToOne
    private SessaoVotacao sessaoVotacao;
    private Boolean voto;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Voto voto1 = (Voto) o;
        return Objects.equals(id, voto1.id) && Objects.equals(associado, voto1.associado) && Objects.equals(sessaoVotacao, voto1.sessaoVotacao) && Objects.equals(voto, voto1.voto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, associado, sessaoVotacao, voto);
    }

    public SessaoVotacao getSessaoVotacao() {
        return sessaoVotacao;
    }

    public void setSessaoVotacao(SessaoVotacao sessaoVotacao) {
        this.sessaoVotacao = sessaoVotacao;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Associado getAssociado() {
        return associado;
    }

    public void setAssociado(Associado associado) {
        this.associado = associado;
    }

    public Boolean getVoto() {
        return voto;
    }

    public void setVoto(Boolean voto) {
        this.voto = voto;
    }

    public Integer getId() {
        return id;
    }
}
