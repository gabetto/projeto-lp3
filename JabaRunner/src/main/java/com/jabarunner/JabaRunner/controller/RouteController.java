package com.jabarunner.JabaRunner.controller;

import com.jabarunner.JabaRunner.domain.model.Route;
import com.jabarunner.JabaRunner.domain.repository.RouteRepository;
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

    @GetMapping(value = "")
    public String route(Model model){
        model.addAttribute("route", routeRepository.findAll());
        return "route/listRoutes";
    }

    @GetMapping(value = "add")
    public String getRouteAdd(Model model){
        model.addAttribute("operation", "add");
        model.addAttribute("tittle", "Adicionar rota");
        model.addAttribute("botaoOperacao", "Adicionar rota");
        return "route/formRoute";
    }

    @PostMapping(value = "add")
    public String postRouteAdd(Model model, @ModelAttribute Route route){
        routeRepository.save(route);
        return "redirect:/route";
    }

    @GetMapping(value = "/edit/{id}")
    public String getRouteEdit(Model model, @PathVariable Long id) {
        model.addAttribute("operation", "edit");
        model.addAttribute("title", "Editar rota");
        model.addAttribute("botaoOperacao", "Editar rota");
        Optional<Route> route = routeRepository.findById(id);
        if (route.isPresent()){
            model.addAttribute("route", route.get());
        }
        return "route/formRoute";
    }

    @PostMapping(value = "/edit/{id}")
    public String postRouteEdit(@ModelAttribute Route route, Model model,
                              @PathVariable Long id) throws Exception {
        if (id.equals(route.getId())) {
            routeRepository.save(route);
        } else {
            model.addAttribute("error", "Dados incorretos");
        }
        return "redirect:/route";
    }

    @GetMapping(value = "delete/{id}")
    public String getRunDelete(Model model, @PathVariable Long id) {
        model.addAttribute("operation", "delete");
        model.addAttribute("tittle", "Excluir rota");
        model.addAttribute("botaoOperacao", "Excluir rota");
        Optional<Route> route = routeRepository.findById(id);
        if (route.isPresent()) {
            model.addAttribute("route", route.get());
        }
        return "route/formRoute";
    }

    @PostMapping(value = "delete/{id}")
    public String postRunDelete(@PathVariable Long id, @ModelAttribute Route route) {
        routeRepository.delete(route);
        return "redirect:/route";
    }
}
