package ra.btss19.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ra.btss19.entity.Category;
import ra.btss19.service.ICategoryService;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = {"/","/Category"})
public class CategoryController {
    @Autowired
    private ICategoryService categoryService;
    @GetMapping
    public String categoryHome(Model model){
        List<Category> categories = categoryService.getCategory();
        model.addAttribute("category",categories);
        return "/categoryHome";
    }
    @GetMapping("/createCategory")
    public String createCategory(Model model) {
        model.addAttribute("category", new Category());
        return "/createCategory";
    }

    @PostMapping("/saveCategory")
    public String actionCreateCategory(@Valid @ModelAttribute("category") Category category, BindingResult result, Model model) {
        if (result.hasErrors())
        {
            model.addAttribute("category", category);
            return "/createCategory";
        }
        categoryService.insertCategory(category);
        return "redirect:/";
    }
    @GetMapping("/deleteCategory/{id}")
    public String deleteCategory(@PathVariable("id") Integer id) {
        categoryService.deleteCategory(id);
        return "redirect:/";
    }
    @GetMapping("//editCategory/{id}")
    public String editCategory(@PathVariable int id, Model model) {
        model.addAttribute("category", categoryService.getCategoryById(id));
        return "/editCategory";
    }
    @PostMapping("/updateCategory")
    public String updateCategory(Category category) {
        categoryService.updateCategory(category);
        return "redirect:/";
    }
    @GetMapping("/viewCategory/{id}")
    public String viewCategory(@PathVariable int id, Model model) {
        model.addAttribute("category", categoryService.getCategoryById(id));
        return "/viewCategory";
    }
}
