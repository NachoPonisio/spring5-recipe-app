package guru.springframework.converters;

import guru.springframework.commands.NotesCommand;
import guru.springframework.domain.Notes;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesCommandToNotesTest {

    private final Long ID_VALUE = new Long(1L);
    private final String RECIPE_NOTES = "recipe notes";

    private NotesCommandToNotes converter;

    @Before
    public void setUp() throws Exception {
        converter = new NotesCommandToNotes();
    }

    @Test
    public void testNullObject() throws Exception {
        //given

        //when
        NotesCommand notesCommand = null;

        //then
        assertNull(converter.convert(notesCommand));

    }


    @Test
    public void testEmptyObject() throws Exception{
        //given

        //when
        NotesCommand notesCommand = new NotesCommand();

        //then
        assertNotNull(converter.convert(notesCommand));
    }

    @Test
    public void convert() {
        //given
        NotesCommand notesCommand = new NotesCommand();
        notesCommand.setId(ID_VALUE);
        notesCommand.setRecipeNotes(RECIPE_NOTES);

        //when
        Notes result = converter.convert(notesCommand);

        //then
        assertEquals(ID_VALUE, result.getId());
        assertEquals(RECIPE_NOTES, result.getRecipeNotes());

    }
}