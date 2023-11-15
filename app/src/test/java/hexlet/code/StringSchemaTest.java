package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class StringSchemaTest {

    @Test
    void containsTest() {
        StringSchema sSchema1 = new StringSchema();
        assertTrue(sSchema1.isValid(null));
        sSchema1.contains("123");
        assertFalse(sSchema1.isValid(null));
        assertTrue(sSchema1.contains("wh").isValid("what does the fox say"));
    }

    @Test
    void minLengthTest() {
        StringSchema sSchema2 = new StringSchema();
        assertTrue(sSchema2.isValid(""));
        assertTrue(sSchema2.minLength(5).isValid("123456"));
        assertFalse(sSchema2.minLength(7).isValid("123456"));
    }

    @Test
    void requiredTest() {
        StringSchema sSchema3 = new StringSchema();
        assertTrue(sSchema3.isValid(null));
        assertFalse(sSchema3.required().isValid(""));
    }

}
