package com.JavaRunner.JavaRunner.controller;

import com.JavaRunner.JavaRunner.domain.model.Rule;
import com.JavaRunner.JavaRunner.domain.repository.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(value = "admin/rule")
public class RuleController {

    @Autowired
    RuleRepository ruleRepository;

    @GetMapping(value = "")
    public String rule(Model model) {
        model.addAttribute("title", "Lista de regras");
        model.addAttribute("rules", ruleRepository.findAll());
        return "rule/listRule";
    }

    @GetMapping(value = "/add")
    public String getAdd(Model model) {
        model.addAttribute("operation", "add");
        model.addAttribute("title", "Adicionar regra");
        model.addAttribute("botaoOperacao", "Adicionar regra");
        return "rule/formRule";
    }

    @PostMapping(value = "/add")
    public String postAdd(@ModelAttribute Rule rule) {
        ruleRepository.save(rule);
        return "redirect:/admin/rule";
    }

    @GetMapping(value = "/edit/{id}")
    public String getEdit(Model model, @PathVariable String id) {
        model.addAttribute("operation", "edit");
        model.addAttribute("title", "Editar regra");
        model.addAttribute("botaoOperacao", "Editar regra");
        Optional<Rule> rule = ruleRepository.findById(id);
        rule.ifPresent(r -> model.addAttribute("rule", r));
        return "rule/formRule";
    }

    @PostMapping(value = "/edit/{id}")
    public String postEdit(@ModelAttribute Rule rule, Model model,
                           @PathVariable String id) throws Exception {
        if (id.equals(rule.getId())) {
            ruleRepository.save(rule);
        } else {
            model.addAttribute("error", "Dados incorretos");
        }
        return "redirect:/admin/rule";
    }

    @GetMapping(value = "/delete/{id}")
    public String getDelete(Model model, @PathVariable String id) {
        model.addAttribute("operation", "delete");
        model.addAttribute("tittle", "Excluir regra");
        model.addAttribute("botaoOperacao", "Excluir regra");
        Optional<Rule> rule = ruleRepository.findById(id);
        rule.ifPresent(r -> model.addAttribute("rule", r));
        return "rule/formRule";
    }

    @PostMapping(value = "/delete/{id}")
    public String postDelete(@PathVariable String id) {
        ruleRepository.deleteById(id);
        return "redirect:/admin/rule";
    }
}
