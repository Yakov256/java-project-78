package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class NumberSchemaTest {

    @Test
    void numberSchemaPositiveTest() {
        Validator v = new Validator();
        NumberSchema nSchema = v.number();

        assertTrue(nSchema.isValid(-10));
        assertFalse(nSchema.positive().isValid(-10));
        assertTrue(nSchema.isValid(10));
        assertTrue(nSchema.positive().isValid(null)); // true
        // Потому что ранее мы вызвали метод positive()
        assertFalse(nSchema.isValid(-10)); // false
        //  Ноль — не положительное число
        assertFalse(nSchema.isValid(0)); // false
    }

    @Test
    void numberSchemaTest() {
        Validator v = new Validator();
        NumberSchema schema = v.number();

        // Пока не вызван метод required(), null считается валидным
        assertTrue(schema.isValid(null)); // true

        schema.required();
        assertFalse(schema.isValid(null)); // false
        assertFalse(schema.isValid("5")); // false
        assertTrue(schema.isValid(10)); // true
    }

    @Test
    void numberSchemaRangeTest() {
        Validator v = new Validator();
        NumberSchema schema = v.number();
        schema.range(5, 10);

        assertTrue(schema.isValid(5)); // true
        assertTrue(schema.isValid(9)); // true
        assertTrue(schema.isValid(10)); // true
        assertFalse(schema.isValid(4)); // false
        assertFalse(schema.isValid(11)); // false
    }

}
