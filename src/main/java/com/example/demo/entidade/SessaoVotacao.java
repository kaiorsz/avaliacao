package com.example.demo.entidade;

import jakarta.persistence.*;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Objects;

@Entity
public class SessaoVotacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    @NotNull
    private Pauta pauta;
    private Date dataInicio;
    private Date dataFinal;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Pauta getPauta() {
        return pauta;
    }

    public void setPauta(Pauta pauta) {
        this.pauta = pauta;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SessaoVotacao that = (SessaoVotacao) o;
        return Objects.equals(id, that.id) && Objects.equals(pauta, that.pauta) && Objects.equals(dataInicio, that.dataInicio) && Objects.equals(dataFinal, that.dataFinal);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, pauta, dataInicio, dataFinal);
    }
}
