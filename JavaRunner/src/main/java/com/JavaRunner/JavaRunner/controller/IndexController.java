package com.JavaRunner.JavaRunner.controller;

import com.JavaRunner.JavaRunner.controller.requests.PaymentRequest;
import com.JavaRunner.JavaRunner.domain.model.Race;
import com.JavaRunner.JavaRunner.domain.repository.KitRepository;
import com.JavaRunner.JavaRunner.domain.repository.RaceRepository;
import com.JavaRunner.JavaRunner.domain.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/")
public class IndexController {
    @Autowired
    RaceRepository raceRepository;
    @Autowired
    RouteRepository routeRepository;
    @Autowired
    KitRepository kitRepository;

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
                model.addAttribute("routes", routeRepository.findAllByRace(race1));
                model.addAttribute("kits", kitRepository.findAllByRace(race1));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return "race/subscribe";
    }

    @PostMapping("subscribe/{id}")
    public String doPayment(@ModelAttribute PaymentRequest payment) {

        return "";
    }

}
