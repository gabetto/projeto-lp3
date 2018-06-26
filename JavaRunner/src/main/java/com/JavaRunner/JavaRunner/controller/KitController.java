package com.JavaRunner.JavaRunner.controller;

import com.JavaRunner.JavaRunner.controller.requests.KitResponse;
import com.JavaRunner.JavaRunner.domain.model.Kit;
import com.JavaRunner.JavaRunner.domain.model.Product;
import com.JavaRunner.JavaRunner.domain.repository.KitRepository;
import com.JavaRunner.JavaRunner.domain.repository.ProductRepository;
import com.JavaRunner.JavaRunner.domain.repository.RaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "admin/kit")
public class KitController {

    @Autowired
    KitRepository kitRepository;
    @Autowired
    RaceRepository raceRepository;
    @Autowired
    ProductRepository products;

    @GetMapping
    public String kit(Model model) {
        model.addAttribute("tittle", "Lista de kits");
        List<KitResponse> kits = new ArrayList<>();
        for (Kit k : kitRepository.findAll()) {
            Double price = 0.0;
            for (Product p : products.findAllByKit(k)) {
                price += p.getPrice();
            }
            kits.add(new KitResponse(k, price));
        }
        model.addAttribute("kits", kits);
        return "kit/listKit";
    }

    @GetMapping(value = "/add")
    public String getKitAdd(Model model) {
        model.addAttribute("operation", "add");
        model.addAttribute("title", "Adicionar kit");
        model.addAttribute("botaoOperacao", "Adicionar kit");
        model.addAttribute("corridas", raceRepository.findAllByOrderByDataDesc());
        return "kit/formKit";
    }

    @PostMapping(value = "/add")
    public String postKitAdd(@ModelAttribute Kit kit) {
        kitRepository.save(kit);
        return "redirect:/admin/kit";
    }

    @GetMapping(value = "/edit/{id}")
    public String getKitEdit(Model model, @PathVariable String id) {
        model.addAttribute("operation", "edit");
        model.addAttribute("title", "Editar kit");
        model.addAttribute("botaoOperacao", "Editar kit");
        Optional<Kit> kit = kitRepository.findById(id);
        kit.ifPresent(kit1 -> model.addAttribute("kit", kit1));
        return "kit/formKit";
    }

    @PostMapping(value = "/edit/{id}")
    public String postKitEdit(@ModelAttribute Kit kit, Model model, @PathVariable String id) {
        if (id.equals(kit.getId())) {
            kitRepository.save(kit);
        } else {
            model.addAttribute("error", "Dados incorretos");
        }
        return "redirect:/admin/kit";
    }

    @GetMapping(value = "/delete/{id}")
    public String getKitDelete(Model model, @PathVariable String id) {
        model.addAttribute("operation", "delete");
        model.addAttribute("tittle", "Excluir kit");
        model.addAttribute("botaoOperacao", "Excluir kit");
        Optional<Kit> kit = kitRepository.findById(id);
        kit.ifPresent(kit1 -> model.addAttribute("kit", kit1));
        return "kit/formKit";
    }

    @PostMapping(value = "/delete/{id}")
    public String postKitDelete(@PathVariable String id, @ModelAttribute Kit kit) {
        kitRepository.delete(kit);
        return "redirect:/admin/kit";
    }

}
