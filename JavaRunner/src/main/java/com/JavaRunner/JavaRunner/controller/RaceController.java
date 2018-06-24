package com.JavaRunner.JavaRunner.controller;

import com.JavaRunner.JavaRunner.domain.model.Race;
import com.JavaRunner.JavaRunner.domain.repository.RaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

@Controller
@RequestMapping(value = "race")
public class RaceController {
    private RaceRepository raceRepository;

    @Autowired
    public RaceController(RaceRepository raceRepository) {
        this.raceRepository = raceRepository;
    }

    private Model indexModel(Model model) {
        model.addAttribute("title", "Lista de corridas");
        model.addAttribute("races", raceRepository.findAll());
        return model;
    }

    @GetMapping
    public String race(Model model) {
        this.indexModel(model);
        return "race/index";
    }

    @GetMapping(value = "add")
    public String getRaceAdd(Model model) {
        model.addAttribute("operation", "add");
        model.addAttribute("title", "Adicionar Corrida");
        model.addAttribute("botaoOperacao", "Nova corrida");
        return "race/crud";
    }

    @PostMapping(value = "add")
    public String postRaceAdd(Model model, @ModelAttribute Race race) {
        HashMap<String, String> errors = race.findErrors();
        Optional<Race> byName = raceRepository.findByName(race.getName());
        byName.ifPresent(race1 -> errors.put("exists", race1.getName().trim() + " já está cadastrada"));
        if (errors.isEmpty()) {
            Race save = raceRepository.save(race.beautify());
            model.addAttribute("newRace", save);
            indexModel(model);
            return "race/index";
        }
        model.addAttribute("errors", errors);
        model.addAttribute("botaoOperacao", "Tente novamente");
        return "race/crud";
    }

    @GetMapping(value = "/edit/{id}")
    public String getRaceEdit(Model model, @PathVariable String id) {
        model.addAttribute("operation", "edit");
        model.addAttribute("title", "Editar corrida");
        model.addAttribute("botaoOperacao", "Editar corrida");
        Optional<Race> race = raceRepository.findById(id);
        race.ifPresent(race1 -> model.addAttribute("race", race1));
        return "race/crud";
    }

    @PostMapping(value = "/edit/{id}")
    public String postRaceEdit(@ModelAttribute Race race, Model model, @PathVariable String id) {
        if (id.equals(race.getId())) {
            raceRepository.save(race);
        } else {
            model.addAttribute("error", "Dados incorretos");
        }
        return "redirect:/race";
    }

    @GetMapping(value = "/delete/{id}")
    public String getRaceDelete(Model model, @PathVariable String id) {
        model.addAttribute("operation", "delete");
        model.addAttribute("tittle", "Excluir corrida");
        model.addAttribute("botaoOperacao", "Excluir corrida");
        Optional<Race> race = raceRepository.findById(id);
        race.ifPresent(race1 -> model.addAttribute("race", race1));
        return "race/crud";
    }

    @PostMapping(value = "/delete/{id}")
    public String postRaceDelete(Model model, @PathVariable String id, @ModelAttribute Race race) {
        raceRepository.deleteById(id);
        model.addAttribute("deletedRace", race);
        return "redirect:/race";
    }
}
