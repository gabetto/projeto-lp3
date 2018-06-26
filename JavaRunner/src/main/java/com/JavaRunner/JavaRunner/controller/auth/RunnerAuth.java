package com.JavaRunner.JavaRunner.controller.auth;

import com.JavaRunner.JavaRunner.domain.model.Runner;
import com.JavaRunner.JavaRunner.domain.repository.RunnerRepository;
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
@RequestMapping(value = "/auth/runner")
public class RunnerAuth {

    private RunnerRepository repository;

    @Autowired
    public RunnerAuth(RunnerRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String requestLogin() {
        return "auth/runner";
    }

    @GetMapping(value = "register")
    public String requestRegister(Model model) {
        model.addAttribute("operation", "add");
        model.addAttribute("title", "Novo discípulo de Tonhão, O Flash Acadêmico");
        model.addAttribute("botaoOperacao", "Adicionar administrador");
        return "runner/register";
    }

    @PostMapping(value = "register")
    @ModelAttribute
    public String doRegister(Runner runner, HttpServletRequest req, Model model) throws Exception {
        System.out.println(runner);
        HashMap<String, String> errors = runner.findErrors();
        if (errors.isEmpty()) {
            Runner register = repository.save(runner.setPassword(
                    Hashing.sha256().hashString(runner.getPassword(), StandardCharsets.UTF_8).toString())
                    .beautify());
            req.getSession().setAttribute("runner", register);
            return "redirect:/runner";
        }
        model.addAttribute("errors", errors);
        model.addAttribute("runner", runner);
        return "runner/register";
    }

    @PostMapping
    public String doLogin(@RequestParam String email, @RequestParam String password, HttpServletRequest request) {
        String hashed = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
        Optional<Runner> runner = repository.findByEmailAndPassword(email, hashed);
        if (runner.isPresent()) {
            request.setAttribute("runner", runner.get());
            return "redirect:/runner";
        }
        return "auth/runner";
    }

    @GetMapping(value = "/logout")
    public String doLogout(HttpServletRequest request) {
        request.getSession().removeAttribute("runner");
        return "redirect:/";
    }

}
