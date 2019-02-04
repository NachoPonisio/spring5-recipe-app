package guru.springframework.converters;

import guru.springframework.commands.UnitOfMeasureCommand;
import guru.springframework.domain.UnitOfMeasure;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UnitOfMeasureCommandToUnitOfMeasureTest {

    private static final Long ID = new Long(1L);
    private static final String DESCRIPTION = "description";

    UnitOfMeasureCommandToUnitOfMeasure converter;

    @Before
    public void setUp() throws Exception {
        converter = new UnitOfMeasureCommandToUnitOfMeasure();
    }

    @Test
    public void testEmptyObject() throws Exception {

        UnitOfMeasureCommand source = new UnitOfMeasureCommand();
        UnitOfMeasure result = converter.convert(source);

        assertNotNull(result);

    }

    @Test
    public void testConvert() throws Exception {
        UnitOfMeasureCommand source = new UnitOfMeasureCommand();
        source.setDescription(DESCRIPTION);
        source.setId(ID);

       UnitOfMeasure unitOfMeasure = converter.convert(source);

       assertEquals(ID, unitOfMeasure.getId());
       assertEquals(DESCRIPTION, unitOfMeasure.getDescription());

    }
}