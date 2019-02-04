package guru.springframework.converters;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMeasureToUnitOfMeasureCommandTest {

    private static final Long ID_VALUE = new Long(1L);
    private static final String DESCRIPTION = "description";

    UnitOfMeasureToUnitOfMeasureCommand converter;

    @Before
    public void setUp(){
        converter = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    public void testNullObject(){
        UnitOfMeasure source = null;
        UnitOfMeasureCommand command = converter.convert(source);

        assertNull(command);
    }


    @Test
    public void testEmptyObject(){
        UnitOfMeasure source = new UnitOfMeasure();
        UnitOfMeasureCommand command = converter.convert(source);

        assertNotNull(command);
    }

    @Test
    public void testConvert(){
        UnitOfMeasure source = new UnitOfMeasure();
        source.setId(ID_VALUE);
        source.setDescription(DESCRIPTION);

        UnitOfMeasureCommand unitOfMeasureCommand = converter.convert(source);

        assertEquals(ID_VALUE, unitOfMeasureCommand.getId());
        assertEquals(DESCRIPTION, unitOfMeasureCommand.getDescription());
    }
}