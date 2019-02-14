package guru.springframework.services;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.converters.RecipeCommandToRecipe;
import guru.springframework.converters.RecipeToRecipeCommand;
import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    RecipeToRecipeCommand recipeConverter;

    @Mock
    RecipeRepository recipeRepository;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository, recipeConverter);
    }

    @Test
    public void getAllRecipes() {
        RecipeCommand recipe = new RecipeCommand();
        HashSet<RecipeCommand> recipesData = new HashSet<>();
        recipesData.add(recipe);

        when(recipeService.getAllRecipes()).thenReturn(recipesData);

        Set<RecipeCommand> returnedData = recipeService.getAllRecipes();
        assertEquals(returnedData.size(), 1);
        verify(recipeRepository, times(1)).findAll();
        verify(recipeRepository, never()).findById(anyLong());

    }


    @Test
    public void findById() throws Exception{
        RecipeCommand recipe = new RecipeCommand();
        recipe.setId(1L);
        Optional<RecipeCommand> recipeOptional = Optional.of(recipe);

        when(recipeService.findById(anyLong())).thenReturn(recipeOptional.get());

        RecipeCommand returnedData = recipeService.findById(1L);

        assertNotNull("Null object returned", returnedData);
        verify(recipeRepository, times(1)).findById(1L);
        verify(recipeRepository, never()).findAll();

    }
}