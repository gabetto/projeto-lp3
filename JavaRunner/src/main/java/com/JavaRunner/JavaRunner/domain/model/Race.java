package com.JavaRunner.JavaRunner.domain.model;

import com.JavaRunner.JavaRunner.utils.Capitalize;
import com.JavaRunner.JavaRunner.utils.contracts.ModelValidation;
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
    @GeneratedValue
    private Long id;
    @Column(name = "nome_corrida", nullable = false, length = 64)
    private String name;
    @Column(name = "data", nullable = false, length = 12)
    private String data;
    @Column(name = "valor_corrida", nullable = false, precision = 2)
    private Double value;
    @Column(name = "maximo_inscritos", nullable = false)
    private Integer maxSubscribes;
    @Column(name = "kilometragem", nullable = true, precision = 2)
    private Double maxLength;
    @Column(name = "organizador", nullable = false, length = 64)
    private String organizer;
    @Column(name = "tipo_chip", nullable = false)
    private String tipoChip;
    @Column(name = "descricao", nullable = false, length = 1024)
    private String descricao;
    @Column(name = "cidade", nullable = true, length = 64)
    private String city;
    @Column(name = "estado", nullable = true, length = 64)
    private String state;
    @Column(name = "regiao", nullable = true, length = 64)
    private String region;
    @Column(name = "hora", nullable = false, length = 7)
    private String initialHour;
    @Column(name = "cor")
    private String cor;
    @Column(name = "hora_final", nullable = true, length = 7)
    private String finalHour;
    @Column(name = "cep_chegada", nullable = true, length = 20)
    private String finalCep;
    @Column(name = "cep_largada", nullable = true, length = 20)
    private String startCep;
    @Column(name = "descricao_chegada", nullable = true, length = 1024)
    private String descricaoChegada;
    @Column(name = "descricao_largada", nullable = true, length = 1024)
    private String descricaoLargada;
    @Column(name = "data_retirada_kit", nullable = true, length = 15)
    private String dataRetiradaKit;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "corrida_id")
    private Collection<Route> routes;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "corrida_id")
    private Collection<Kit> kits;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "corrida_id")
    private Collection<Lot> lots;

    public Race(){

    }

    @ManyToMany
    private Collection<Ranking> rankings;

    @Override
    public Race validate() throws Exception {
        return new Race(
                this.id, Capitalize.brazilianCapitalize(this.name), this.data,
                this.value, this.maxSubscribes, this.maxLength, Capitalize.brazilianCapitalize(this.organizer),
                this.tipoChip, this.descricao, Capitalize.brazilianCapitalize(this.city),
                Capitalize.brazilianCapitalize(this.state), Capitalize.brazilianCapitalize(this.region),
                this.initialHour, this.cor,this.finalHour, this.finalCep, this.startCep,
                this.descricaoChegada, this.descricaoLargada, this.dataRetiradaKit,
                this.routes, this.kits, this.lots, this.rankings
        );
    }
}
