package com.ra.controller;

import com.ra.model.entity.Category;
import com.ra.model.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping("/category")
    public String index(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "category/index";
    }

    @GetMapping("/category/add")
    public String add(Model model) {
        model.addAttribute("category", new Category());
        return "category/add";
    }

    @PostMapping("/category/add")
    public String processAdd(@ModelAttribute("category") Category category) {
        categoryService.save(category);
        return "redirect:/";
    }

    @GetMapping("/category/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("category", categoryService.findById(id));
        return "category/edit";
    }

    @PostMapping("/category/edit")
    public String processEdit(@ModelAttribute("category") Category category) {
        categoryService.save(category);
        return "redirect:/";
    }

    @GetMapping("/category/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        categoryService.delete(categoryService.findById(id));
        return "redirect:/";
    }
}
