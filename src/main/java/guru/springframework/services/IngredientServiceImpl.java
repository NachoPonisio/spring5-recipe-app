package guru.springframework.services;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.RecipeCommand;
import guru.springframework.converters.IngredientToIngredientCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService {

    private final RecipeRepository recipeRepository;
    private final IngredientToIngredientCommand ingredientToIngredientCommand;

    @Autowired
    public IngredientServiceImpl(RecipeRepository recipeRepository, IngredientToIngredientCommand ingredientToIngredientCommand) {
        this.recipeRepository = recipeRepository;
        this.ingredientToIngredientCommand = ingredientToIngredientCommand;
    }


    @Override
    public IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId) {

        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if (!recipeOptional.isPresent()){
            log.error("Could not find recipe with recipeId = " + recipeId.toString());
            return null;
        }

        Optional<IngredientCommand> ingredientCommandOptional = recipeOptional.get().getIngredients().stream()
                .filter(i -> i.getId().equals(ingredientId))
                .map(j -> ingredientToIngredientCommand.convert(j))
                .findAny();

        if (!ingredientCommandOptional.isPresent()){
            log.error("Could not find ingredient with ingredientId = " + ingredientId.toString());
            return null;
        }

        return ingredientCommandOptional.get();

    }
}
