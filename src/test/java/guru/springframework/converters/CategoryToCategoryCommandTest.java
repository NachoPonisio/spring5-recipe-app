package guru.springframework.converters;

import guru.springframework.commands.CategoryCommand;
import guru.springframework.domain.Category;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CategoryToCategoryCommandTest {

    private static final Long ID_VALUE = new Long(1L);
    private static final String DESCRIPTION = "adescription";

    CategoryToCategoryCommand converter;

    @Before
    public void setup(){
        converter = new CategoryToCategoryCommand();
    }

    @Test
    public void testNullObject() throws Exception{
        //given


        //when
        Category category = null;

        //then
        assertNull(converter.convert(category));

    }


    @Test
    public void testEmptyObject() throws Exception{
        //given

        //when
        Category category = new Category();
        //then
        assertNotNull(converter.convert(category));
    }

    @Test
    public void convert(){
        //given
        Category category = new Category();
        category.setDescription(DESCRIPTION);
        category.setId(ID_VALUE);

        //when
        CategoryCommand categoryCommand = converter.convert(category);

        //then
        assertEquals(ID_VALUE, categoryCommand.getId());
        assertEquals(DESCRIPTION, categoryCommand.getDescription());

    }
}