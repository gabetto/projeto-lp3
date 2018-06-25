package com.JavaRunner.JavaRunner.controller;

import com.JavaRunner.JavaRunner.domain.model.Runner;
import com.JavaRunner.JavaRunner.domain.repository.RunnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Optional;

@Controller
@RequestMapping(value = "admin/runner")
public class RunnerController {

    private final RunnerRepository runnerRepository;

    @Autowired
    public RunnerController(RunnerRepository runnerRepository) {
        this.runnerRepository = runnerRepository;
    }

    @GetMapping
    public String runners(Model model) {
        model.addAttribute("title", "Lista de corredores");
        model.addAttribute("runners", runnerRepository.findAll());
        return "runner/index";
    }

    @GetMapping(value = "/add")
    public String getRunnersAdd(Model model) {
        model.addAttribute("operation", "add");
        model.addAttribute("title", "Adicionar corredor");
        model.addAttribute("botaoOperacao", "Adicionar corredor");
        return "runner/crud";
    }

    @PostMapping(value = "/add")
    public String postRunnersAdd(@ModelAttribute Runner runner, Model model) throws Exception {
        HashMap<String, String> errors = runner.findErrors();
        if (errors.isEmpty()) {
            runnerRepository.save(runner.beautify());
            return "redirect:/runner";
        }
        model.addAttribute("errors", errors);
        model.addAttribute("runner", runner);
        return "runner/crud";
    }

    @GetMapping(value = "edit/{id}")
    public String getRunnerEdit(Model model, @PathVariable String id) {
        model.addAttribute("operation", "edit");
        model.addAttribute("title", "Editar corredor");
        model.addAttribute("botaoOperacao", "Editar corredor");
        Optional<Runner> runner = runnerRepository.findById(id);
        runner.ifPresent(runnerOpt -> model.addAttribute("runner", runnerOpt));
        return "runner/crud";
    }

    @PostMapping(value = "edit/{id}")
    public String postRunnerEdit(@ModelAttribute Runner runner, Model model,
                                 @PathVariable String id) throws Exception {
        HashMap<String, String> errors = runner.findErrors();
        if (id.equals(runner.getId())) {
            if (errors.isEmpty()) {
                runnerRepository.save(runner.beautify());
            }
        } else {
            model.addAttribute("error", errors);
        }
        return "redirect:/runner";
    }

    @GetMapping(value = "delete/{id}")
    public String getRunnerDelete(Model model, @PathVariable String id) {
        model.addAttribute("opetarion", "delete");
        model.addAttribute("tittle", "Excluir corredor");
        Optional<Runner> runner = runnerRepository.findById(id);
        if (runner.isPresent()) {
            model.addAttribute("runner", runner.get());
            model.addAttribute("operation", "delete");
            model.addAttribute("botaoOperacao", "Deletar corredor");
        }
        return "runner/crud";
    }

    @PostMapping(value = "delete/{id}")
    public String postRunnerDelete(@PathVariable String id, @ModelAttribute Runner runner) {
        runnerRepository.deleteById(id);
        return "redirect:/runner";
    }
}
