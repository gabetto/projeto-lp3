package com.JavaRunner.JavaRunner.controller;

import com.JavaRunner.JavaRunner.domain.model.Race;
import com.JavaRunner.JavaRunner.domain.repository.RaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class IndexController {
    @Autowired
    RaceRepository raceRepository;

    @GetMapping
    public String index(Model model) {
        model.addAttribute("races", raceRepository.findAllByOrderByDataDesc());
        return "index";
    }

    @GetMapping("subscribe/{id}")
    public String subscribeView(Model model, @PathVariable String id) {
        Optional<Race> race = raceRepository.findById(id);
        race.ifPresent(race1 -> {
            try {
                model.addAttribute("race", race1);
                model.addAttribute("routes", race1.getLots());
                model.addAttribute("kits", race1.getKits());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return "race/subscribe";
    }

}
