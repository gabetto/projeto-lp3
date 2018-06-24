package com.JavaRunner.JavaRunner.controller;

import com.JavaRunner.JavaRunner.domain.model.Product;
import com.JavaRunner.JavaRunner.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(value = "product")
public class ProductController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping(value = "")
    public String product(Model model){
        model.addAttribute("tittle","Lista de Produtos");
        model.addAttribute("products", productRepository.findAll());
        return "product/listProduct";
    }

    @GetMapping(value = "/add")
    public String getProductAdd(Model model){
        model.addAttribute("operation", "add");
        model.addAttribute("tittle", "Adicionar Produto");
        model.addAttribute("botaoOperacao", "Adicionar produto");
        return "product/formProduct";
    }

    @PostMapping(value = "/add")
    public String postProductAdd(Model model, @ModelAttribute Product product){
        model.addAttribute("tittle", "Adicionar produto");
        productRepository.save(product);
        return "redirect:/product";
    }

    @GetMapping(value = "/edit/{id}")
    public String getProductEdit(Model model, @PathVariable String id) {
        model.addAttribute("operation", "edit");
        model.addAttribute("title", "Editar product");
        model.addAttribute("botaoOperacao", "Editar produto");
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()){
            model.addAttribute("product", product.get());
        }
        return "product/formProduct";
    }

    @PostMapping(value = "/edit/{id}")
    public String postProductEdit(@ModelAttribute Product product, Model model,
                              @PathVariable String id) throws Exception {
        if (id.equals(product.getId())) {
            productRepository.save(product);
        } else {
            model.addAttribute("error", "Dados incorretos");
        }
        return "redirect:/product";
    }

    @GetMapping(value = "/delete/{id}")
    public String getProductDelete(Model model, @PathVariable String id) {
        model.addAttribute("operation", "delete");
        model.addAttribute("tittle", "Excluir produto");
        model.addAttribute("botaoOperacao", "excluit produto");
        Optional<Product> product = productRepository.findById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
        }

        return "product/formProduct";
    }

    @PostMapping(value = "delete/{id}")
    public String postProductDelete(@PathVariable String id, @ModelAttribute Product product) {
        productRepository.delete(product);
        return "redirect:/product";
    }
}
