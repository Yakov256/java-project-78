package hexlet.code;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class SchemaTest {

    @Test
    void stringSchemaContainsTest() {
        StringSchema sSchema1 = new StringSchema();
        assertTrue(sSchema1.isValid(null));
        sSchema1.contains("123");
        assertFalse(sSchema1.isValid(null));
        assertTrue(sSchema1.contains("wh").isValid("what does the fox say"));
    }

    @Test
    void stringSchemaMinLengthTest() {
        StringSchema sSchema2 = new StringSchema();
        assertTrue(sSchema2.isValid(""));
        assertTrue(sSchema2.minLength(5).isValid("123456"));
        assertFalse(sSchema2.minLength(7).isValid("123456"));
    }

    @Test
    void stringSchemaRequiredTest() {
        StringSchema sSchema3 = new StringSchema();
        assertTrue(sSchema3.isValid(null));
        assertFalse(sSchema3.required().isValid(""));
    }


    @Test
    void numberSchemaPositiveTest() {
        NumberSchema nSchema = new NumberSchema();
        assertTrue(nSchema.isValid(-10));
        assertFalse(nSchema.positive().isValid(-10));
    }

    @Test
    void numberSchemaRequiredTest() {
        NumberSchema nSchema = new NumberSchema();
        assertTrue(nSchema.isValid(null));
        assertFalse(nSchema.required().isValid(null));
    }

    @Test
    void numberSchemaRangeTest() {
        NumberSchema nSchema = new NumberSchema();
        assertTrue(nSchema.isValid(-100));
        assertFalse(nSchema.range(-15, 50).isValid(-100));
        assertFalse(nSchema.range(-15, 50).isValid(60));
    }

    @Test
    void mapSchemaRequiredTest() {
        MapSchema mSchema = new MapSchema();
        assertTrue(mSchema.isValid(null));
        assertFalse(mSchema.required().isValid(null));
    }

    void mapSchemaSizeofTest() {
        MapSchema mSchema = new MapSchema();
        Map<String, String> testMap = new HashMap<>();
        testMap.put("key1", "value1");
        testMap.put("key2", "value2");
        assertTrue(mSchema.isValid(testMap));
        assertFalse(mSchema.sizeof(3).isValid(testMap));
        assertTrue(mSchema.sizeof(2).isValid(testMap));
    }

}
