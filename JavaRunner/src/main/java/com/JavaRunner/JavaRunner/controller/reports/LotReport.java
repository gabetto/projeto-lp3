package com.JavaRunner.JavaRunner.controller.reports;

import com.JavaRunner.JavaRunner.domain.repository.LotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("report/lot")
public class LotReport {

    @Autowired
    LotRepository repository;

    @GetMapping
    public String reportAll(Model model) {
        model.addAttribute("count", repository.count());
        model.addAttribute("list", repository.findAll());
        return "report/lot";
    }
}
