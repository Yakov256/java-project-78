package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class SchemaTest {

    @Test
    void stringSchemaContainsTest() {
        Validator v = new Validator();
        StringSchema sSchema1 = v.string();

        assertTrue(sSchema1.isValid(null));
        sSchema1.contains("123");
        assertFalse(sSchema1.isValid(null));
        assertTrue(sSchema1.contains("wh").isValid("what does the fox say"));
    }

    @Test
    void stringSchemaMinLengthTest() {
        Validator v = new Validator();
        StringSchema sSchema2 = v.string();

        assertTrue(sSchema2.isValid(""));
        assertTrue(sSchema2.minLength(5).isValid("123456"));
        assertFalse(sSchema2.minLength(7).isValid("123456"));
    }

    @Test
    void stringSchemaRequiredTest() {
        Validator v = new Validator();
        StringSchema sSchema3 = v.string();

        assertTrue(sSchema3.isValid(null));
        assertFalse(sSchema3.required().isValid(""));
    }

    @Test
    void numberSchemaPositiveTest() {
        Validator v = new Validator();
        NumberSchema nSchema = v.number();

        assertTrue(nSchema.isValid(-10));
        assertFalse(nSchema.positive().isValid(-10));
    }

    @Test
    void numberSchemaTest() {
        Validator v = new Validator();
        NumberSchema schema = v.number();

        // Пока не вызван метод required(), null считается валидным
        assertTrue(schema.isValid(null)); // true
        assertTrue(schema.positive().isValid(null)); // true

        schema.required();

        assertFalse(schema.isValid(null)); // false
        assertFalse(schema.isValid("5")); // false
        assertTrue(schema.isValid(10)); // true

        // Потому что ранее мы вызвали метод positive()
        assertFalse(schema.isValid(-10)); // false
        //  Ноль — не положительное число
        assertFalse(schema.isValid(0)); // false

        schema.range(5, 10);

        assertTrue(schema.isValid(5)); // true
        assertTrue(schema.isValid(10)); // true
        assertFalse(schema.isValid(4)); // false
        assertFalse(schema.isValid(11)); // false
    }

    @Test
    void mapSchemaRequiredTest() {
        Validator v = new Validator();
        MapSchema mSchema = v.map();

        assertTrue(mSchema.isValid(null));
        assertFalse(mSchema.required().isValid(null));
    }

    @Test
    void mapSchemaSizeofTest() {
        Validator v = new Validator();
        MapSchema mSchema = v.map();

        Map<String, String> testMap = new HashMap<>();
        testMap.put("key1", "value1");
        testMap.put("key2", "value2");
        assertTrue(mSchema.isValid(testMap));
        assertFalse(mSchema.sizeof(3).isValid(testMap));
        assertTrue(mSchema.sizeof(2).isValid(testMap));
    }

    @Test
    void apSchemaDeepIsValidTest() {

        Validator v = new Validator();
        MapSchema schema = v.map();

        Map<String, BaseSchema> schemas = new HashMap<>();
        schemas.put("name", v.string().required());
        schemas.put("age", v.number().positive());
        schema.shape(schemas);

        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", 100);
        assertTrue(schema.isValid(human1));

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        assertTrue(schema.isValid(human2));

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        assertFalse(schema.isValid(human3));

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", -5);
        assertFalse(schema.isValid(human4));

    }

}
