package hexlet.code;

import java.util.Map;

public class MapSchema extends BaseSchema {
    //required() ограничение, которое не позволяет использовать null в качестве значения. Требуется тип данных Map
    //private boolean required;

    //sizeof() — добавляет ограничение на размер словаря.
    // Количество пар ключ-значений в объекте Map должно быть равно заданному
    private Integer sizeof = null;

    private Map<String, BaseSchema> schemas;

    public MapSchema required() {
        super.required = true;
        return this;
    }

    public MapSchema sizeof(Integer size) {
        this.sizeof = size;
        return this;
    }

    public void shape(Map<String, BaseSchema> newSchemas) {
        this.schemas = newSchemas;
    }

    private boolean deepIsValid(Map testMap) {
        boolean rez = true;

        for (Map.Entry<String, BaseSchema> entry : schemas.entrySet()) {
            //System.out.println("ID =  " + entry.getKey() + " День недели = " + entry.getValue());
            if (testMap.containsKey(entry.getKey())) {
                System.out.println("Пороверяем: " + entry.getKey() + " - " + testMap.get(entry.getKey()));
                System.out.println("isValid: " + entry.getValue().isValid(testMap.get(entry.getKey())));
            }


        }

        return rez;
    }

    public boolean isValid(Map testMap) {
        boolean rez = true;

        if (testMap == null) {
            return !required;
        }

        if (schemas != null) {
            rez = deepIsValid(testMap);
        }

        if (sizeof != null && testMap.size() != sizeof) {
            rez = false;
        }

        return rez;
    }

}
