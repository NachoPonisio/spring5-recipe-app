package guru.springframework.services;

import guru.springframework.domain.Recipe;
import guru.springframework.repositories.RecipeRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    public void getAllRecipes() {
        Recipe recipe = new Recipe();
        HashSet<Recipe> recipesData = new HashSet<>();
        recipesData.add(recipe);

        when(recipeService.getAllRecipes()).thenReturn(recipesData);

        Set<Recipe> returnedData = recipeService.getAllRecipes();
        assertEquals(returnedData.size(), 1);
        verify(recipeRepository, times(1)).findAll();
        verify(recipeRepository, never()).findById(anyLong());

    }


    @Test
    public void findById() {
        Recipe recipeReturn = new Recipe();
        recipeReturn.setId(1L);

        when(recipeService.findById(1L)).thenReturn(recipeReturn);

        Recipe returnedData = recipeService.findById(1L);

        assertNotNull("Null object returned", recipeReturn);
        verify(recipeRepository, times(1)).findById(1L);
        verify(recipeRepository, never()).findAll();

    }
}