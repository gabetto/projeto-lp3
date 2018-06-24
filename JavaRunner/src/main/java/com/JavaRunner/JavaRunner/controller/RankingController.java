package com.JavaRunner.JavaRunner.controller;

import com.JavaRunner.JavaRunner.domain.model.Ranking;
import com.JavaRunner.JavaRunner.domain.repository.RankingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(value = "ranking")
public class RankingController {

    @Autowired
    RankingRepository rankingRepository;

    @GetMapping(value = "")
    public String ranking(Model model){
        model.addAttribute("tittle","Lista de rankings");
        model.addAttribute("rankings", rankingRepository.findAll());
        return "ranking/listRanking";
    }

    @GetMapping(value = "/add")
    public String getAdd(Model model){
        model.addAttribute("operation", "add");
        model.addAttribute("tittle", "Adicionar ranking");
        model.addAttribute("botaoOperacao", "Adicionar ranking");
        return "ranking/formRanking";
    }

    @PostMapping(value = "/add")
    public String postAdd(Model model, @ModelAttribute Ranking ranking){
        model.addAttribute("tittle", "Adicionar ranking");
        rankingRepository.save(ranking);
        return "redirect:/ranking";
    }

    @GetMapping(value = "/edit/{id}")
    public String getEdit(Model model, @PathVariable String id) {
        model.addAttribute("operation", "edit");
        model.addAttribute("title", "Editar ranking");
        model.addAttribute("botaoOperacao", "Editar ranking");
        Optional<Ranking> ranking = rankingRepository.findById(id);
        if (ranking.isPresent()){
            model.addAttribute("ranking", ranking.get());
        }
        return "ranking/formRanking";
    }

    @PostMapping(value = "/edit/{id}")
    public String postEdit(@ModelAttribute Ranking ranking, Model model,
                           @PathVariable Long id) throws Exception {
        if (id.equals(ranking.getId())) {
            rankingRepository.save(ranking);
        } else {
            model.addAttribute("error", "Dados incorretos");
        }
        return "redirect:/ranking";
    }

    @GetMapping(value = "/delete/{id}")
    public String getDelete(Model model, @PathVariable String id) {
        model.addAttribute("operation", "delete");
        model.addAttribute("tittle", "Excluir ranking");
        model.addAttribute("botaoOperacao", "excluit ranking");
        Optional<Ranking> ranking = rankingRepository.findById(id);
        if (ranking.isPresent()) {
            model.addAttribute("ranking", ranking.get());
        }

        return "ranking/formRanking";
    }

    @PostMapping(value = "/delete/{id}")
    public String postDelete(@PathVariable Long id, @ModelAttribute Ranking ranking) {
        rankingRepository.delete(ranking);
        return "redirect:/ranking";
    }

}
