package com.JavaRunner.JavaRunner.controller;

import com.JavaRunner.JavaRunner.domain.model.Race;
import com.JavaRunner.JavaRunner.domain.repository.RaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(value = "race")
public class RaceController {

    @Autowired
    RaceRepository raceRepository;

    @GetMapping(value = "")
    public String race(Model model){
        model.addAttribute("tittle", "Lista de corridas");
        model.addAttribute("races", raceRepository.findAll());
        return "race/listRaces";
    }

    @GetMapping(value = "add")
    public String getRaceAdd(Model model){
        model.addAttribute("operation", "add");
        model.addAttribute("tittle", "Adicionar corrida");
        model.addAttribute("botaoOperacao", "Cadastrar corrida");
        return "race/formRace";
    }

    @PostMapping(value = "add")
    public String postRaceAdd(Model model, @ModelAttribute Race race){
        model.addAttribute("tittle", "Adicionar corrida");
        raceRepository.save(race);
        return "redirect:/race";
    }

    @GetMapping(value = "/edit/{id}")
    public String getRaceEdit(Model model, @PathVariable Long id) {
        model.addAttribute("operation", "edit");
        model.addAttribute("title", "Editar corrida");
        model.addAttribute("botaoOperacao", "Editar corrida");
        Optional<Race> run = raceRepository.findById(id);
        if (run.isPresent()){
            model.addAttribute("race", run.get());
        }
        return "race/formRace";
    }

    @PostMapping(value = "/edit/{id}")
    public String postRaceEdit(@ModelAttribute Race race, Model model,
                              @PathVariable Long id) throws Exception {
        if (id.equals(race.getId())) {
            raceRepository.save(race);
        } else {
            model.addAttribute("error", "Dados incorretos");
        }
        return "redirect:/race";
    }

    @GetMapping(value = "/delete/{id}")
    public String getRaceDelete(Model model, @PathVariable Long id) {
        model.addAttribute("operation", "delete");
        model.addAttribute("tittle", "Excluir corrida");
        model.addAttribute("botaoOperacao", "Excluir corrida");
        Optional<Race> race = raceRepository.findById(id);
        if (race.isPresent()) {
            model.addAttribute("race", race.get());
        }

        return "race/formRace";
    }

    @PostMapping(value = "/delete/{id}")
    public String postRaceDelete(@PathVariable Long id, @ModelAttribute Race race) {
        raceRepository.delete(race);
        return "redirect:/race";
    }
}
