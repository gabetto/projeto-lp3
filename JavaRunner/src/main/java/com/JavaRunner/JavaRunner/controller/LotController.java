package com.JavaRunner.JavaRunner.controller;


import com.JavaRunner.JavaRunner.domain.model.Lot;
import com.JavaRunner.JavaRunner.domain.repository.LotRepository;
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
        model.addAttribute("tittle","Lista de lotes");
        model.addAttribute("lot", lotRepository.findAll());
        return "lot/listLots";
    }

    @GetMapping(value = "/add")
    public String getLotAdd(Model model){
        model.addAttribute("operation", "add");
        model.addAttribute("tittle", "Adicionar lote");
        model.addAttribute("botaoOperacao", "Adicionar lote");
        return "lot/formLot";
    }

    @PostMapping(value = "/add")
    public String postLotAdd(Model model, @ModelAttribute Lot lot){
        model.addAttribute("tittle", "Adicionar lote");
        lotRepository.save(lot);
        return "redirect:/lot";
    }

    @GetMapping(value = "/edit/{id}")
    public String getLotEdit(Model model, @PathVariable Long id) {
        model.addAttribute("operation", "edit");
        model.addAttribute("title", "Editar lote");
        model.addAttribute("botaoOperacao", "Editar lote");
        Optional<Lot> lot = lotRepository.findById(id);
        if (lot.isPresent()){
            model.addAttribute("lot", lot.get());
        }
        return "lot/formLot";
    }

    @PostMapping(value = "/edit/{id}")
    public String postLotEdit(@ModelAttribute Lot lot, Model model,
                                @PathVariable Long id) throws Exception {
        if (id.equals(lot.getId())) {
            lotRepository.save(lot);
        } else {
            model.addAttribute("error", "Dados incorretos");
        }
        return "redirect:/lot";
    }

    @GetMapping(value = "/delete/{id}")
    public String getLotDelete(Model model, @PathVariable Long id) {
        model.addAttribute("operation", "delete");
        model.addAttribute("tittle", "Excluir lote");
        model.addAttribute("botaoOperacao", "excluit lote");
        Optional<Lot> lot = lotRepository.findById(id);
        if (lot.isPresent()) {
            model.addAttribute("lot", lot.get());
        }

        return "lot/formLot";
    }

    @PostMapping(value = "/delete/{id}")
    public String postLotDelete(@PathVariable Long id, @ModelAttribute Lot lot) {
        lotRepository.delete(lot);
        return "redirect:/lot";
    }
}
