package com.jabarunner.JabaRunner.controller;

import com.jabarunner.JabaRunner.domain.model.Runner;
import com.jabarunner.JabaRunner.domain.repository.RunnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
@RequestMapping(value = "runners")
public class RunnerController {

    @Autowired
    private RunnerRepository runnerRepository;


    @GetMapping
    public String runners(Model model) {
        model.addAttribute("operation", "list");
        model.addAttribute("tittle", "Lista de corredores");
        model.addAttribute("runners", runnerRepository.findAll());
        return "runner/runners";
    }

    @GetMapping(value = "add")
    public String getRunnersAdd(Model model) {
        model.addAttribute("operation", "add");
        model.addAttribute("tittle", "Adicionar corredor");
        return "runner/runners";
    }

    @PostMapping(value = "add")
    public String postRunnersAdd(Model model, @ModelAttribute Runner runner) {
        model.addAttribute("tittle", "Adicionar corredor");
        runnerRepository.save(runner);
        return "redirect:/runners";
    }

    @GetMapping(value = "edit/{id}")
    public String getRunnerEdit(Model model, @PathVariable Long id) {
        model.addAttribute("operation", "edit");
        model.addAttribute("title", "Editar corredor");
        Optional<Runner> runner = runnerRepository.findById(id);
        if (runner.isPresent()) {
            model.addAttribute("corredor", runner.get());
        }
        return "runner/runners";
    }

    @PostMapping(value = "edit/{id}")
    public String postRunnerEdit(@ModelAttribute Runner runner, Model model,
                                 @PathVariable Long id) throws Exception {
        if (id.equals(runner.getId())) {
            runnerRepository.save(runner);
        } else {
            model.addAttribute("error", "Dados incorretos");
        }
        return "redirect:/runners";
    }

    @GetMapping(value = "delete/{id}")
    public String getRunnerDelete(Model model, @PathVariable Long id) {
        model.addAttribute("opetarion", "delete");
        model.addAttribute("tittle", "Excluir corredor");
        Optional<Runner> runner = runnerRepository.findById(id);
        if (runner.isPresent()) {
            model.addAttribute("corredor", runner.get());
        }

        return "runner/runners.html";
    }

    @PostMapping(value = "delete/{id}")
    public String postRunnerDelete(@PathVariable Long id, @ModelAttribute Runner runner) {
        runnerRepository.delete(runner);
        return "redirect:/runners";
    }
}
