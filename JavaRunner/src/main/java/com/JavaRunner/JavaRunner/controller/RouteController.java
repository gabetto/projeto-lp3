package com.JavaRunner.JavaRunner.controller;

import com.JavaRunner.JavaRunner.domain.model.Route;
import com.JavaRunner.JavaRunner.domain.repository.RaceRepository;
import com.JavaRunner.JavaRunner.domain.repository.RouteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("route")
public class RouteController {

    @Autowired
    RouteRepository routeRepository;

    @Autowired
    RaceRepository raceRepository;

    @GetMapping(value = "")
    public String route(Model model){
        model.addAttribute("routes", routeRepository.findAll());
        return "route/listRoute";
    }

    @GetMapping(value = "add")
    public String getRouteAdd(Model model){
        model.addAttribute("operation", "add");
        model.addAttribute("tittle", "Adicionar percurso");
        model.addAttribute("botaoOperacao", "Adicionar percurso");
        model.addAttribute("corridas", raceRepository.findAll());
        return "route/formRoute";
    }

    @PostMapping(value = "add")
    public String postRouteAdd(Model model, @ModelAttribute Route route){
        routeRepository.save(route);
        return "redirect:/route";
    }

    @GetMapping(value = "/edit/{id}")
    public String getRouteEdit(Model model, @PathVariable String id) {
        model.addAttribute("operation", "edit");
        model.addAttribute("title", "Editar percurso");
        model.addAttribute("botaoOperacao", "Editar percurso");
        Optional<Route> route = routeRepository.findById(id);
        if (route.isPresent()){
            model.addAttribute("route", route.get());
        }
        return "route/formRoute";
    }

    @PostMapping(value = "/edit/{id}")
    public String postRouteEdit(@ModelAttribute Route route, Model model,
                              @PathVariable String id) throws Exception {
        if (id.equals(route.getId())) {
            routeRepository.save(route);
        } else {
            model.addAttribute("error", "Dados incorretos");
        }
        return "redirect:/route";
    }

    @GetMapping(value = "delete/{id}")
    public String getRunDelete(Model model, @PathVariable String id) {
        model.addAttribute("operation", "delete");
        model.addAttribute("tittle", "Excluir percurso");
        model.addAttribute("botaoOperacao", "Excluir percurso");
        Optional<Route> route = routeRepository.findById(id);
        if (route.isPresent()) {
            model.addAttribute("route", route.get());
        }
        return "route/formRoute";
    }

    @PostMapping(value = "delete/{id}")
    public String postRunDelete(@PathVariable String id, @ModelAttribute Route route) {
        routeRepository.delete(route);
        return "redirect:/route";
    }
}
