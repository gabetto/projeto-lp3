package com.JavaRunner.JavaRunner.controller;

import com.JavaRunner.JavaRunner.domain.model.Administrator;
import com.JavaRunner.JavaRunner.domain.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

@Controller
@RequestMapping(value = "admin")
public class AdministratorController {

    private AdministratorRepository administratorRepository;

    @Autowired
    public AdministratorController(AdministratorRepository administratorRepository) {
        this.administratorRepository = administratorRepository;
    }

    @GetMapping
    public String administrator(Model model) {
        model.addAttribute("tittle", "Lista de administradores");
        model.addAttribute("administrators", administratorRepository.findAll());
        return "administrator/index";
    }

    @GetMapping(value = "/add")
    public String getAdd(Model model) {
        model.addAttribute("operation", "add");
        model.addAttribute("tittle", "Adicionar administrador");
        model.addAttribute("botaoOperacao", "Adicionar administrador");
        return "administrator/crud";
    }

    @PostMapping(value = "/add")
    public String postAdd(Model model, @ModelAttribute Administrator admin) throws Exception {
        model.addAttribute("title", "Adicionar administrador");
        HashMap<String, String> errors = admin.findErrors();
        if (errors.isEmpty()) {
            administratorRepository.save(admin.beautify());
            return "redirect:/admin";
        }
        model.addAttribute("errors", errors);
        model.addAttribute("administrator", admin);
        return "administrator/crud";
    }

    @GetMapping(value = "/edit/{id}")
    public String getEdit(Model model, @PathVariable String id) {
        model.addAttribute("operation", "edit");
        model.addAttribute("title", "Editar administrador");
        model.addAttribute("botaoOperacao", "Editar administrador");
        Optional<Administrator> admin = administratorRepository.findById(id);
        admin.ifPresent(administrator -> {
            try {
                model.addAttribute("administrator", administrator);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        return "administrator/crud";
    }

    @PostMapping(value = "/edit/{id}")
    public String postEdit(@ModelAttribute Administrator admin, Model model,
                           @PathVariable String id) throws Exception {
        HashMap<String, String> errors = admin.findErrors();
        if (id.equals(admin.getId())) {
            if (errors.isEmpty()) {
                administratorRepository.save(admin.beautify());
                return "redirect:/admin";
            }
        }
        model.addAttribute("errors", errors);
        model.addAttribute("administrator", admin);
        return "administrator/crud";
    }

    @GetMapping(value = "/delete/{id}")
    public String getDelete(Model model, @PathVariable String id) {
        model.addAttribute("operation", "delete");
        model.addAttribute("tittle", "Excluir administrador");
        model.addAttribute("botaoOperacao", "excluir administrador");
        Optional<Administrator> admin = administratorRepository.findById(id);
        admin.ifPresent(administrator -> model.addAttribute("administrator", administrator));
        return "administrator/crud";
    }

    @PostMapping(value = "/delete/{id}")
    public String postDelete(@PathVariable String id) {
        administratorRepository.deleteById(id);
        return "redirect:/admin";
    }
}
