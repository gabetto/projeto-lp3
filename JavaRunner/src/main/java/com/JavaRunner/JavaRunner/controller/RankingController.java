package com.JavaRunner.JavaRunner.controller;

import com.JavaRunner.JavaRunner.domain.model.Ranking;
import com.JavaRunner.JavaRunner.domain.repository.RankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(value = "admin/ranking")
public class RankingController {

    @Autowired
    RankingRepository rankingRepository;

    @GetMapping
    public String ranking(Model model) {
        model.addAttribute("tittle", "Lista de rankings");
        model.addAttribute("rankings", rankingRepository.findAll());
        return "ranking/listRanking";
    }

    @GetMapping(value = "/add")
    public String getAdd(Model model) {
        model.addAttribute("operation", "add");
        model.addAttribute("tittle", "Adicionar ranking");
        model.addAttribute("botaoOperacao", "Adicionar ranking");
        return "ranking/formRanking";
    }

    @PostMapping(value = "/add")
    public String postAdd(Model model, @ModelAttribute Ranking ranking) {
        model.addAttribute("tittle", "Adicionar ranking");
        rankingRepository.save(ranking);
        return "redirect:/admin/ranking";
    }

    @GetMapping(value = "/edit/{id}")
    public String getEdit(Model model, @PathVariable String id) {
        model.addAttribute("operation", "edit");
        model.addAttribute("title", "Editar ranking");
        model.addAttribute("botaoOperacao", "Editar ranking");
        Optional<Ranking> ranking = rankingRepository.findById(id);
        ranking.ifPresent(r -> model.addAttribute("ranking", r));
        return "ranking/formRanking";
    }

    @PostMapping(value = "/edit/{id}")
    public String postEdit(@ModelAttribute Ranking ranking, Model model, @PathVariable String id) {
        if (id.equals(ranking.getId())) {
            rankingRepository.save(ranking);
        } else {
            model.addAttribute("error", "Dados incorretos");
        }
        return "redirect:/admin/ranking";
    }

    @GetMapping(value = "/delete/{id}")
    public String getDelete(Model model, @PathVariable String id) {
        model.addAttribute("operation", "delete");
        model.addAttribute("title", "Excluir ranking");
        model.addAttribute("botaoOperacao", "Excluir ranking");
        Optional<Ranking> ranking = rankingRepository.findById(id);
        ranking.ifPresent(r -> model.addAttribute("ranking", r));
        return "ranking/formRanking";
    }

    @PostMapping(value = "/delete/{id}")
    public String postDelete(@PathVariable String id) {
        rankingRepository.deleteById(id);
        return "redirect:/admin/ranking";
    }
}
