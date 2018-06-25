package com.JavaRunner.JavaRunner.domain.model;

import com.JavaRunner.JavaRunner.utils.validations.Validations;
import com.JavaRunner.JavaRunner.utils.validations.Validator;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashMap;

@Data
@Entity
@Table(name = "race")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class Race extends DatabaseCommons implements Validator<Race> {
    @Column(nullable = false, length = 64)
    private String name;
    @Column(nullable = false, length = 12)
    private String data;
    @Column(nullable = false, precision = 2)
    private Double price;
    @Column(nullable = false)
    private Integer subscribes;
    @Column(precision = 3, nullable = false)
    private Double length;
    @Column(nullable = false, length = 64)
    private String organizer;
    @Column(nullable = false, length = 11)
    private String chip;
    @Column(nullable = false, length = 1024)
    private String description;
    @Column(length = 64, nullable = false)
    private String city;
    @Column(length = 64, nullable = false)
    private String state;
    @Column(length = 64, nullable = false)
    private String region;
    @Column(nullable = false, length = 12)
    private String initialHour;
    @Column(nullable = false, length = 12)
    private String color;
    @Column(length = 12, nullable = false)
    private String finalHour;
    @Column(length = 20, nullable = false)
    private String finalCep;
    @Column(length = 20, nullable = false)
    private String startCep;
    @Column(length = 1024, nullable = false)
    private String startDescription;
    @Column(length = 1024, nullable = false)
    private String endDescription;
    @Column(length = 15, nullable = false)
    private String kitWithdraw;
    @Column(length = 256)
    private String image;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn
    private Collection<Route> routes;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn
    private Collection<Kit> kits;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn
    private Collection<Lot> lots;
    @ManyToMany
    private Collection<Ranking> rankings;
    @ManyToMany
    private Collection<Runner> runners;

    @Override
    public HashMap<String, String> findErrors() {
        HashMap<String, String> errors = new HashMap<>();
        if (!Validations.beforeThanToday(this.getData())) {
            errors.put("dataError", "A corrida deve ser em um dia futuro");
        }
        return errors;
    }

    @Override
    public Race beautify() {
        return new Race().setName(name).setOrganizer(organizer)
                .setPrice(price).setRegion(region).setStartCep(startCep)
                .setData(data).setSubscribes(subscribes).setLength(length)
                .setKitWithdraw(kitWithdraw).setRegion(region)
                .setState(state).setCity(city).setDescription(description)
                .setEndDescription(endDescription).setFinalCep(finalCep)
                .setColor(color).setChip(chip).setFinalHour(finalHour)
                .setInitialHour(initialHour).setStartDescription(startDescription);
    }
}
