package guru.springframework.controllers;

import guru.springframework.services.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecipeController {

    private RecipeService recipeService;


    @Autowired
    public RecipeController(RecipeService recipeService) {
        this.setRecipeService(recipeService);
    }

    @RequestMapping("/recipe/show/{id}")
    private String showById(@PathVariable String id, Model model){

        model.addAttribute("recipe", recipeService.findById(new Long(id)));

        return "recipe/show";

    }




    public RecipeService getRecipeService() {
        return recipeService;
    }

    public void setRecipeService(RecipeService recipeService) {
        this.recipeService = recipeService;
    }
}
