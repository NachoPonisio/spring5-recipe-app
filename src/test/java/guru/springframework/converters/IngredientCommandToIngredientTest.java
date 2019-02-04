package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Executable;
import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientCommandToIngredientTest {

    private final Long ID = new Long(1L);
    private final String DESCRIPTION = "description";
    private final BigDecimal AMOUNT = new BigDecimal(1);

    UnitOfMeasureCommandToUnitOfMeasure uomConverter;
    IngredientCommandToIngredient converter;

    @Before
    public void setUp() throws Exception {
        uomConverter = new UnitOfMeasureCommandToUnitOfMeasure();
        converter = new IngredientCommandToIngredient(uomConverter);
    }

    @Test
    public void testEmptyObject() throws Exception {
        //given

        //when
        IngredientCommand source = new IngredientCommand();
        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
        source.setUnitOfMeasure(unitOfMeasureCommand);

        //then
        assertNotNull(converter.convert(source));
    }

    @Test
    public void testNullObject() throws Exception{
        //given

        //when
        IngredientCommand source = null;

        //then
        assertNull(converter.convert(source));
    }

    @Test
    public void convert() throws Exception{
        //given
        UnitOfMeasureCommand unitOfMeasureCommand = new UnitOfMeasureCommand();
        unitOfMeasureCommand.setId(ID);
        unitOfMeasureCommand.setDescription(DESCRIPTION);

        IngredientCommand source = new IngredientCommand();
        source.setId(ID);
        source.setAmount(AMOUNT);
        source.setDescription(DESCRIPTION);
        source.setUnitOfMeasure(unitOfMeasureCommand);


        //when
        Ingredient ingredient = converter.convert(source);


        //then
        assertEquals(ID, ingredient.getId());
        assertEquals(DESCRIPTION, ingredient.getDescription());
        assertEquals(AMOUNT, ingredient.getAmount());
        assertEquals(ID, ingredient.getUom().getId());
        assertEquals(DESCRIPTION, ingredient.getUom().getDescription());


    }
}