package com.jabarunner.JabaRunner.domain.model;

import com.jabarunner.JabaRunner.utils.Capitalize;
import com.jabarunner.JabaRunner.utils.contracts.ModelValidation;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "corrida")
@Data
@AllArgsConstructor
public class Race implements Serializable, ModelValidation<Race> {
    @Id
    @Column(name = "id", nullable = false)
    private final Long id;
    @Basic
    @Column(name = "nome_corrida", nullable = false, length = 64)
    private final String name;
    @Basic
    @Column(name = "data", nullable = false, length = 12)
    private final String data;
    @Basic
    @Column(name = "valor_corrida", nullable = false, precision = 2)
    private final Double value;
    @Basic
    @Column(name = "maximo_inscritos", nullable = false)
    private final Integer maxSubscribes;
    @Basic
    @Column(name = "kilometragem", nullable = true, precision = 2)
    private final Double maxLength;
    @Basic
    @Column(name = "organizador", nullable = false, length = 64)
    private final String organizer;
    @Basic
    @Column(name = "tipo_chip", nullable = false)
    private final String tipoChip;
    @Basic
    @Column(name = "descricao", nullable = false, length = 1024)
    private final String descricao;
    @Basic
    @Column(name = "cidade", nullable = true, length = 64)
    private final String city;
    @Basic
    @Column(name = "estado", nullable = true, length = 64)
    private final String state;
    @Basic
    @Column(name = "regiao", nullable = true, length = 64)
    private final String region;
    @Basic
    @Column(name = "hora", nullable = false, length = 7)
    private final String initialHour;
    @Basic
    @Column(name = "color", nullable = true, length = 7)
    private final String color;
    @Basic
    @Column(name = "hora_final", nullable = true, length = 7)
    private final String finalHour;
    @Basic
    @Column(name = "cep_chegada", nullable = true, length = 9)
    private final String finalCep;
    @Basic
    @Column(name = "cep_largada", nullable = true, length = 9)
    private final String startCep;
    @Basic
    @Column(name = "descricao_chegada", nullable = true, length = 1024)
    private final String descricaoChegada;
    @Basic
    @Column(name = "descricao_largada", nullable = true, length = 1024)
    private final String descricaoLargada;
    @Basic
    @Column(name = "data_retirada_kit", nullable = true, length = 15)
    private final String dataRetiradaKit;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "race")
//    @JoinColumn(name = "corrida_id")
    private Collection<Route> routes;
    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "corrida_id")
    private final Collection<Kit> kits;
    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "corrida_id")
    private final Collection<Lot> lots;
    @ManyToMany(mappedBy = "races")
    private final Collection<Ranking> rankings;

    @Override
    public Race validate() throws Exception {
        return new Race(
                this.id, Capitalize.brazilianCapitalize(this.name), this.data,
                this.value, this.maxSubscribes, this.maxLength, Capitalize.brazilianCapitalize(this.organizer),
                this.tipoChip, this.descricao, Capitalize.brazilianCapitalize(this.city),
                Capitalize.brazilianCapitalize(this.state), Capitalize.brazilianCapitalize(this.region),
                this.initialHour, this.color,this.finalHour, this.finalCep, this.startCep,
                this.descricaoChegada, this.descricaoLargada, this.dataRetiradaKit,
                this.routes, this.kits, this.lots, this.rankings
        );
    }
}
