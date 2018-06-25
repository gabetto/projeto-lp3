package com.JavaRunner.JavaRunner.controller;

import com.JavaRunner.JavaRunner.domain.model.Administrator;
import com.JavaRunner.JavaRunner.domain.repository.AdministratorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
        model.addAttribute("title", "Lista de administradores");
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
                           @PathVariable String id, HttpServletRequest request) throws Exception {
        Administrator administrator = (Administrator) request.getSession().getAttribute("admin");
        if (administrator.getId().equals(id)) {
            HashMap<String, String> errors = admin.findErrors();
            if (id.equals(admin.getId())) {
                if (errors.isEmpty()) {
                    administratorRepository.save(admin.beautify());
                    return "redirect:/admin";
                }
            }
            model.addAttribute("errors", errors);
            model.addAttribute("administrator", admin);
        }
        model.addAttribute("operation", "edit");
        model.addAttribute("title", "Editar administrador");
        model.addAttribute("botaoOperacao", "Editar administrador");
        model.addAttribute("erro", "Você não pode excluir a conta de outro administrador");
        return "administrator/crud";
    }

    @GetMapping(value = "/delete/{id}")
    public String getDelete(Model model, @PathVariable String id) {
        model.addAttribute("operation", "delete");
        model.addAttribute("title", "Excluir administrador");
        model.addAttribute("botaoOperacao", "excluir administrador");
        Optional<Administrator> admin = administratorRepository.findById(id);
        admin.ifPresent(administrator -> model.addAttribute("administrator", administrator));
        return "administrator/crud";
    }

    @PostMapping(value = "/delete/{id}")
    public String postDelete(@PathVariable String id, HttpServletRequest request, Model model) {
        Administrator adminReq = (Administrator) request.getSession().getAttribute("admin");
        if (adminReq.getId().equals(id)) {
            administratorRepository.deleteById(id);
            request.getSession().removeAttribute("admin");
            return "redirect:/";
        }
        model.addAttribute("operation", "delete");
        model.addAttribute("title", "Excluir administrador");
        model.addAttribute("botaoOperacao", "excluir administrador");
        model.addAttribute("erro", "Você não pode excluir a conta de outro administrador");
        return "administrator/crud";
    }
}
