package persistence;

import org.junit.jupiter.api.Test;
import model.persistance.JsonReader;
import model.Week;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Week w = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
        }
    }


    @Test
    void testReaderGeneralWorkRoom() {
        JsonReader reader = new JsonReader("./data/testReadFullWeek.json");
        try {
            Week w = reader.read();
            assertEquals("CHINESE", w.getSchedule()[0].getTheme());
            assertEquals("Friday-2020-10-30", w.getSchedule()[0].getDate());
            assertEquals("Meat Dumplings", w.getSchedule()[0].getRecipeName());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }

    }
}
