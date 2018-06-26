package com.JavaRunner.JavaRunner.controller.auth;

import com.JavaRunner.JavaRunner.domain.model.Administrator;
import com.JavaRunner.JavaRunner.domain.repository.AdministratorRepository;
import com.google.common.hash.Hashing;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Optional;

@Controller
@RequestMapping(value = "/auth/admin")
public class AdminAuth {

    private AdministratorRepository repository;

    @Autowired
    public AdminAuth(AdministratorRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String requestLogin() {
        return "auth/admin";
    }

    @GetMapping(value = "register")
    public String requestRegister(Model model) {
        model.addAttribute("operation", "add");
        model.addAttribute("title", "Adicionar administrador");
        model.addAttribute("botaoOperacao", "Adicionar administrador");
        return "administrator/register";
    }

    @PostMapping(value = "register")
    public String doRegister(@ModelAttribute Administrator admin, HttpServletRequest req, Model model) throws
            Exception {
        if (repository.count() == 0) {
            HashMap<String, String> errors = admin.findErrors();
            if (errors.isEmpty()) {
                Administrator register = repository.save(admin
                        .setPassword(Hashing.sha256()
                                .hashString(admin.getPassword(), StandardCharsets.UTF_8).toString())
                        .beautify());
                req.getSession().setAttribute("admin", register);
                return "redirect:/admin";
            }
            model.addAttribute("errors", errors);
            model.addAttribute("administrator", admin);
            model.addAttribute("operation", "add");
            model.addAttribute("title", "Adicionar administrador");
            model.addAttribute("botaoOperacao", "Adicionar administrador");
        }
        return "administrator/register";
    }


    @PostMapping
    public String doLogin(@RequestParam String login, @RequestParam String password, HttpServletRequest request) {
        Optional<Administrator> admin = repository.findByLoginAndPassword(login,
                Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString());
        if (admin.isPresent()) {
            request.setAttribute("admin", admin.get());
            return "redirect:/admin";
        }
        return "auth/admin";
    }

    @GetMapping(value = "/logout")
    public String doLogout(HttpServletRequest request) {
        request.removeAttribute("admin");
        return "redirect:/";
    }

}
