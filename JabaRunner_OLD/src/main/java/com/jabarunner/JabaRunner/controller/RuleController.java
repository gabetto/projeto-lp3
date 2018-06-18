package com.jabarunner.JabaRunner.controller;

import com.jabarunner.JabaRunner.domain.model.Rule;
import com.jabarunner.JabaRunner.domain.repository.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(value = "rule")
public class RuleController {

    @Autowired
    RuleRepository ruleRepository;

    @GetMapping(value = "")
    public String rule(Model model){
        model.addAttribute("tittle","Lista de regras");
        model.addAttribute("rule", ruleRepository.findAll());
        return "rule/listRule";
    }

    @GetMapping(value = "/add")
    public String getAdd(Model model){
        model.addAttribute("operation", "add");
        model.addAttribute("tittle", "Adicionar regra");
        model.addAttribute("botaoOperacao", "Adicionar regra");
        return "rule/formRule";
    }

    @PostMapping(value = "/add")
    public String postAdd(Model model, @ModelAttribute Rule rule){
        model.addAttribute("tittle", "Adicionar rule");
        ruleRepository.save(rule);
        return "redirect:/rule";
    }

    @GetMapping(value = "/edit/{id}")
    public String getEdit(Model model, @PathVariable Long id) {
        model.addAttribute("operation", "edit");
        model.addAttribute("title", "Editar rule");
        model.addAttribute("botaoOperacao", "Editar rule");
        Optional<Rule> rule= ruleRepository.findById(id);
        if (rule.isPresent()){
            model.addAttribute("rule", rule.get());
        }
        return "rule/formRule";
    }

    @PostMapping(value = "/edit/{id}")
    public String postEdit(@ModelAttribute Rule rule, Model model,
                           @PathVariable Long id) throws Exception {
        if (id.equals(rule.getId())) {
            ruleRepository.save(rule);
        } else {
            model.addAttribute("error", "Dados incorretos");
        }
        return "redirect:/rule";
    }

    @GetMapping(value = "/delete/{id}")
    public String getDelete(Model model, @PathVariable Long id) {
        model.addAttribute("operation", "delete");
        model.addAttribute("tittle", "Excluir rule");
        model.addAttribute("botaoOperacao", "excluit rule");
        Optional<Rule> rule = ruleRepository.findById(id);
        if (rule.isPresent()) {
            model.addAttribute("rule", rule.get());
        }

        return "rule/formRule";
    }

    @PostMapping(value = "/delete/{id}")
    public String postDelete(@PathVariable Long id, @ModelAttribute Rule rule) {
        ruleRepository.delete(rule);
        return "redirect:/rule";
    }
}
