package guru.springframework.controllers;


import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.services.IngredientService;
import guru.springframework.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Slf4j
@Controller
public class IngredientController {

    private final RecipeService recipeService;

    private final IngredientService ingredientService;

    @Autowired
    public IngredientController(RecipeService recipeService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    @GetMapping
    @RequestMapping("/recipe/{id}/ingredients")
    public String getRecipeById(Model model, @PathVariable String id){
        log.debug("Getting ingredients list for recipe id " + id);

        RecipeCommand command = recipeService.findCommandById(Long.valueOf(id));

        model.addAttribute("recipe", command);

        return "recipe/ingredients/list";

    }

    @RequestMapping(method = RequestMethod.GET, path = "/recipe/{recipeId}/ingredients/{id}/show")
    public String getIngredientByRecipeIdAndId(@PathVariable("recipeId") Long recipeId,
                                               @PathVariable("id") Long ingredientId,
                                                Model model){
        IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(recipeId, ingredientId);

        model.addAttribute("ingredient", ingredientCommand);

        return "recipe/ingredients/show";


        }
}

