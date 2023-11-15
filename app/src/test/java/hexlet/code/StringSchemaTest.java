package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class StringSchemaTest {

    @Test
    void containsTest() {
        StringSchema sSchema1 = new StringSchema();
        assertTrue(sSchema1.isValid());
        sSchema1.contains("123");
        assertFalse(sSchema1.isValid());
    }

}
