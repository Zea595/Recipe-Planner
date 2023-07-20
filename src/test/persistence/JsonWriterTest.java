package persistence;

import org.junit.jupiter.api.Test;
import model.Week;
import model.persistance.*;

import java.io.IOException;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest {
    @Test
    void testWriterInvalidFile() {
        try {
            Week w = new Week();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
        }
    }

    @Test
    void testWriterWeek() {
        try {
            Week w = new Week();
            w.generateRandomWeekSchedule();
            JsonWriter writer = new JsonWriter("./data/testWriterNewWeek.json");
            writer.open();
            writer.write(w);
            writer.close();

            // The rest has to be tested by observing the json file as week is generated randomly.
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


}
