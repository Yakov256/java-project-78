package hexlet.code;

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
    void numberSchemaRequiredTest() {
        Validator v = new Validator();
        NumberSchema nSchema = v.number();

        assertTrue(nSchema.isValid(null));
        assertFalse(nSchema.required().isValid(null));
    }

    @Test
    void numberSchemaRangeTest() {
        Validator v = new Validator();
        NumberSchema nSchema = v.number();

        assertTrue(nSchema.isValid(-100));
        assertFalse(nSchema.range(-15, 50).isValid(-100));
        assertFalse(nSchema.range(-15, 50).isValid(60));
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
/*
    @Test
    void apSchemaDeepIsValidTest() {

        Validator v = new Validator();
        MapSchema schema = v.map();

// shape позволяет описывать валидацию для значений каждого ключа объекта Map
// Создаем набор схем для проверки каждого ключа проверяемого объекта
// Для значения каждого ключа - своя схема
        Map<String, BaseSchema> schemas = new HashMap<>();

// Определяем схемы валидации для значений свойств "name" и "age"
// Имя должно быть строкой, обязательно для заполнения
        schemas.put("name", v.string().required());
// Возраст должен быть положительным числом
        schemas.put("age", v.number().positive());

// Настраиваем схему `MapSchema`
// Передаем созданный набор схем в метод shape()
        schema.shape(schemas);

// Проверяем объекты
        Map<String, Object> human1 = new HashMap<>();
        human1.put("name", "Kolya");
        human1.put("age", 100);
        schema.isValid(human1); // true

        Map<String, Object> human2 = new HashMap<>();
        human2.put("name", "Maya");
        human2.put("age", null);
        schema.isValid(human2); // true

        Map<String, Object> human3 = new HashMap<>();
        human3.put("name", "");
        human3.put("age", null);
        schema.isValid(human3); // false

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "Valya");
        human4.put("age", -5);
        schema.isValid(human4); // false

    }
*/
}
