package com.JavaRunner.JavaRunner.controller.reports;

import com.JavaRunner.JavaRunner.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("report/product")
public class ProductReport {
    @Autowired
    ProductRepository repository;

    @GetMapping
    public String reportAll(Model model) {
        model.addAttribute("count", repository.count());
        model.addAttribute("list", repository.findAll());
        return "report/product";
    }
}
