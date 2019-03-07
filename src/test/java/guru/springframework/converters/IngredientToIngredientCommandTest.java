package guru.springframework.converters;

import guru.springframework.commands.IngredientCommand;
import guru.springframework.domain.Ingredient;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class IngredientToIngredientCommandTest {

    private final Long ID_VALUE = new Long(1L);
    private final String DESCRIPTION = "description";
    private final BigDecimal AMOUNT = new BigDecimal(1);

    IngredientToIngredientCommand converter;
    UnitOfMeasureToUnitOfMeasureCommand uomConverter;

    @Before
    public void setUp() throws Exception {
        uomConverter = new UnitOfMeasureToUnitOfMeasureCommand();
        converter = new IngredientToIngredientCommand(uomConverter);
    }

    @Test
    public void testNullObject() throws Exception{
        //given

        //when
        Ingredient source = null;

        //then
        assertNull(converter.convert(source));

    }

    @Test
    public void testEmptyObject() throws Exception{
        //given
        UnitOfMeasure uom  = new UnitOfMeasure();

        //when
        Ingredient source = new Ingredient();
        source.setUom(uom);

        //then
        assertNotNull(converter.convert(source));
    }


    @Test
    public void convert() throws Exception {
        //given
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(ID_VALUE);
        uom.setDescription(DESCRIPTION);

        Ingredient source  = new Ingredient();
        source.setId(ID_VALUE);
        source.setDescription(DESCRIPTION);
        source.setAmount(AMOUNT);
        source.setUom(uom);

        //when
        IngredientCommand ingredientCommand = converter.convert(source);

        //then
        assertEquals(ID_VALUE, ingredientCommand.getId());
        assertEquals(DESCRIPTION, ingredientCommand.getDescription());
        assertEquals(AMOUNT, ingredientCommand.getAmount());
        assertEquals(ID_VALUE, ingredientCommand.getUom().getId());
        assertEquals(DESCRIPTION, ingredientCommand.getUom().getDescription());


    }
}