package com.JavaRunner.JavaRunner.controller;

import com.JavaRunner.JavaRunner.domain.model.Administrator;
import com.JavaRunner.JavaRunner.domain.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(value = "administrator")
public class AdministratorController {

    @Autowired
    AdministratorRepository administratorRepository;

    @GetMapping(value = "")
    public String administrator(Model model){
        model.addAttribute("tittle","Lista de administradores");
        model.addAttribute("administrator", administratorRepository.findAll());
        return "administrator/listAdministrator";
    }

    @GetMapping(value = "/add")
    public String getAdd(Model model){
        model.addAttribute("operation", "add");
        model.addAttribute("tittle", "Adicionar administrador");
        model.addAttribute("botaoOperacao", "Adicionar administrador");
        return "administrator/formAdministrator";
    }

    @PostMapping(value = "/add")
    public String postAdd(Model model, @ModelAttribute Administrator admin){
        model.addAttribute("tittle", "Adicionar administrador");
        administratorRepository.save(admin);
        return "redirect:/administrator";
    }

    @GetMapping(value = "/edit/{id}")
    public String getEdit(Model model, @PathVariable Long id) {
        model.addAttribute("operation", "edit");
        model.addAttribute("title", "Editar administrador");
        model.addAttribute("botaoOperacao", "Editar administrador");
        Optional<Administrator> admin = administratorRepository.findById(id);
        if (admin.isPresent()){
            model.addAttribute("administrator", admin.get());
        }
        return "administrator/formAdministrator";
    }

    @PostMapping(value = "/edit/{id}")
    public String postEdit(@ModelAttribute Administrator admin, Model model,
                              @PathVariable Long id) throws Exception {
        if (id.equals(admin.getId())) {
            administratorRepository.save(admin);
        } else {
            model.addAttribute("error", "Dados incorretos");
        }
        return "redirect:/administrator";
    }

    @GetMapping(value = "/delete/{id}")
    public String getDelete(Model model, @PathVariable Long id) {
        model.addAttribute("operation", "delete");
        model.addAttribute("tittle", "Excluir administrador");
        model.addAttribute("botaoOperacao", "excluit administrador");
        Optional<Administrator> admin = administratorRepository.findById(id);
        if (admin.isPresent()) {
            model.addAttribute("administrator", admin.get());
        }

        return "administrator/formAdministrator";
    }

    @PostMapping(value = "/delete/{id}")
    public String postDelete(@PathVariable Long id, @ModelAttribute Administrator admin) {
        administratorRepository.delete(admin);
        return "redirect:/administrator";
    }

}
