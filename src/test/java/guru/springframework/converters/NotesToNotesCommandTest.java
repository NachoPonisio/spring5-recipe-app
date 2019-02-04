package guru.springframework.converters;

import guru.springframework.commands.NotesCommand;
import guru.springframework.domain.Notes;
import org.apache.logging.log4j.message.LoggerNameAwareMessage;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotesToNotesCommandTest {

    private final Long ID_VALUE = new Long(1L);
    private final String DESCRIPTION = "description";

    private NotesToNotesCommand converter;

    @Before
    public void setUp() throws Exception {
        converter = new NotesToNotesCommand();
    }


    @Test
    public void testNullObject() throws Exception{
        //given

        //when
        Notes source = null;

        //then
        assertNull(converter.convert(source));
    }

    @Test
    public void testEmptyObject() throws Exception{
        //given

        //when
        Notes notes = new Notes();

        //then
        assertNotNull(converter.convert(notes));
    }


    @Test
    public void convert() throws Exception{
        //given
        Notes notes = new Notes();
        notes.setId(ID_VALUE);
        notes.setRecipeNotes(DESCRIPTION);

        //when
        NotesCommand notesCommand = converter.convert(notes);

        //then
        assertEquals(ID_VALUE, notesCommand.getId());
        assertEquals(DESCRIPTION, notesCommand.getRecipeNotes());

    }
}