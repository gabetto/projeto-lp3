package com.JavaRunner.JavaRunner.controller;

import com.JavaRunner.JavaRunner.domain.model.Kit;
import com.JavaRunner.JavaRunner.domain.repository.KitRepository;
import com.JavaRunner.JavaRunner.domain.repository.RaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(value = "kit")
public class KitController {

    @Autowired
    KitRepository kitRepository;

    @Autowired
    RaceRepository raceRepository;

    @GetMapping(value = "")
    public String kit(Model model){
        model.addAttribute("tittle","Lista de kits");
        model.addAttribute("kits", kitRepository.findAll());
        return "kit/listKit";
    }

    @GetMapping(value = "/add")
    public String getKitAdd(Model model){
        model.addAttribute("operation", "add");
        model.addAttribute("tittle", "Adicionar kit");
        model.addAttribute("botaoOperacao", "Adicionar kit");
        model.addAttribute("corridas",raceRepository.findAll());
        return "kit/formKit";
    }

    @PostMapping(value = "/add")
    public String postKitAdd(Model model, @ModelAttribute Kit kit){
        model.addAttribute("tittle", "Adicionar kit");
        kitRepository.save(kit);
        return "redirect:/kit";
    }

    @GetMapping(value = "/edit/{id}")
    public String getKitEdit(Model model, @PathVariable String id) {
        model.addAttribute("operation", "edit");
        model.addAttribute("title", "Editar kit");
        model.addAttribute("botaoOperacao", "Editar kit");
        Optional<Kit> kit = kitRepository.findById(id);
        if (kit.isPresent()){
            model.addAttribute("kit", kit.get());
        }
        return "kit/formKit";
    }

    @PostMapping(value = "/edit/{id}")
    public String postKitEdit(@ModelAttribute Kit kit, Model model,
                              @PathVariable String id) throws Exception {
        if (id.equals(kit.getId())) {
            kitRepository.save(kit);
        } else {
            model.addAttribute("error", "Dados incorretos");
        }
        return "redirect:/kit";
    }

    @GetMapping(value = "/delete/{id}")
    public String getKitDelete(Model model, @PathVariable String id) {
        model.addAttribute("operation", "delete");
        model.addAttribute("tittle", "Excluir kit");
        model.addAttribute("botaoOperacao", "Excluir kit");
        Optional<Kit> kit = kitRepository.findById(id);
        if (kit.isPresent()) {
            model.addAttribute("kit", kit.get());
        }

        return "kit/formKit";
    }

    @PostMapping(value = "/delete/{id}")
    public String postKitDelete(@PathVariable String id, @ModelAttribute Kit kit) {
        kitRepository.delete(kit);
        return "redirect:/kit";
    }

}
