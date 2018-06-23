package com.JavaRunner.JavaRunner.controller.auth;

import com.JavaRunner.JavaRunner.domain.model.Administrator;
import com.JavaRunner.JavaRunner.domain.repository.AdministratorRepository;
import com.JavaRunner.JavaRunner.utils.validations.Salt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
        model.addAttribute("tittle", "Adicionar administrador");
        model.addAttribute("botaoOperacao", "Adicionar administrador");
        return "administrator/register";
    }

    @PostMapping(value = "register")
    public String doRegister(@ModelAttribute Administrator admin, HttpServletRequest req, Model model) throws
            Exception {
        HashMap<String, String> errors = admin.findErrors();
        if (errors.isEmpty()) {
            Administrator register = repository.save(admin
                    .setPassword(BCrypt.hashpw(admin.getPassword(), Salt.saltAdmin))
                    .beautify());
            req.getSession().setAttribute("admin", register);
            return "redirect:/admin";
        }
        model.addAttribute("errors", errors);
        model.addAttribute("administrator", admin);
        return "administrator/register";
    }


    @PostMapping
    public String doLogin(@RequestParam String login, @RequestParam String password, HttpServletRequest request) {
        String hashed = BCrypt.hashpw(password, Salt.saltAdmin);
        Optional<Administrator> admin = repository.findByLoginAndPassword(login, hashed);
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
