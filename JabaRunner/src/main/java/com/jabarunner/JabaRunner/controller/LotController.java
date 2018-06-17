package com.jabarunner.JabaRunner.controller;


import com.jabarunner.JabaRunner.domain.model.Lot;
import com.jabarunner.JabaRunner.domain.repository.LotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
public class LotController {

    @Autowired
    LotRepository lotRepository;

    @GetMapping(value = "")
    public String lot(Model model){
        model.addAttribute("operation", "list");
        model.addAttribute("tittle","Lista de lotes");
        model.addAttribute("lot", lotRepository.findAll());
        return "lots";
    }

    @GetMapping(value = "add")
    public String getLotAdd(Model model){
        model.addAttribute("operation", "add");
        model.addAttribute("tittle", "Adicionar lote");
        return("lots");
    }

    @PostMapping(value = "add")
    public String postLotAdd(Model model, @ModelAttribute Lot lot){
        model.addAttribute("tittle", "Adicionar lote");
        lotRepository.save(lot);
        return "redirect:/lots";
    }

    @GetMapping(value = "edit/{id}")
    public String getLotEdit(Model model, @PathVariable Long id) {
        model.addAttribute("operation", "edit");
        model.addAttribute("title", "Editar lote");
        Optional<Lot> lot = lotRepository.findById(id);
        if (lot.isPresent()){
            model.addAttribute("lot", lot.get());
        }
        return "lots";
    }

    @PostMapping(value = "edit/{id}")
    public String postLotEdit(@ModelAttribute Lot lot, Model model,
                                @PathVariable Long id) throws Exception {
        if (id.equals(lot.getId())) {
            lotRepository.save(lot);
        } else {
            model.addAttribute("error", "Dados incorretos");
        }
        return "redirect:/lot";
    }

    @GetMapping(value = "delete/{id}")
    public String getLotDelete(Model model, @PathVariable Long id) {
        model.addAttribute("operation", "delete");
        model.addAttribute("tittle", "Excluir lote");
        Optional<Lot> lot = lotRepository.findById(id);
        if (lot.isPresent()) {
            model.addAttribute("race", lot.get());
        }

        return "lots";
    }

    @PostMapping(value = "delete/{id}")
    public String postLotDelete(@PathVariable Long id, @ModelAttribute Lot lot) {
        lotRepository.delete(lot);
        return "redirect:/lot";
    }
}
