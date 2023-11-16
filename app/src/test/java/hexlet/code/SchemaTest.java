package hexlet.code;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class SchemaTest {

    @Test
    void StringSchemaContainsTest() {
        StringSchema sSchema1 = new StringSchema();
        assertTrue(sSchema1.isValid(null));
        sSchema1.contains("123");
        assertFalse(sSchema1.isValid(null));
        assertTrue(sSchema1.contains("wh").isValid("what does the fox say"));
    }

    @Test
    void StringSchemaMinLengthTest() {
        StringSchema sSchema2 = new StringSchema();
        assertTrue(sSchema2.isValid(""));
        assertTrue(sSchema2.minLength(5).isValid("123456"));
        assertFalse(sSchema2.minLength(7).isValid("123456"));
    }

    @Test
    void StringSchemaRequiredTest() {
        StringSchema sSchema3 = new StringSchema();
        assertTrue(sSchema3.isValid(null));
        assertFalse(sSchema3.required().isValid(""));
    }


    @Test
    void NumberSchemaPositiveTest() {
        NumberSchema nSchema = new NumberSchema();
        assertTrue(nSchema.isValid(-10));
        assertFalse(nSchema.positive().isValid(-10));
    }

    @Test
    void NumberSchemaRequiredTest() {
        NumberSchema nSchema = new NumberSchema();
        assertTrue(nSchema.isValid(null));
        assertFalse(nSchema.required().isValid(null));
    }


}
