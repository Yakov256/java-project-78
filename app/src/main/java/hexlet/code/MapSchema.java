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
        boolean isValid = true;

        for (Map.Entry<String, BaseSchema> entry : schemas.entrySet()) {

            if (testMap.containsKey(entry.getKey())) {
                //System.out.println("Пороверяем: " + entry.getKey() + " - " + testMap.get(entry.getKey()));

                if (entry.getValue() instanceof StringSchema) {
                    StringSchema ss = (StringSchema) entry.getValue();
                    //String str = (String) testMap.get(entry.getKey());
                    if (!ss.isValid((String) testMap.get(entry.getKey()))) {
                        isValid = false;
                    }

                    //System.out.println("Value: " + ss.isValid((String) testMap.get(entry.getKey())));
                } else if (entry.getValue() instanceof NumberSchema) {
                    NumberSchema ns = (NumberSchema) entry.getValue();
                    if (!ns.isValid((Integer) testMap.get(entry.getKey()))) {
                        isValid = false;
                    }
                    //Integer number = (Integer) testMap.get(entry.getKey());
                    //System.out.println("Value: " + ns.isValid((Integer) testMap.get(entry.getKey())));
                } /*else if (entry.getValue() instanceof MapSchema) {
                    MapSchema ms = (MapSchema) entry.getValue();
                    System.out.println("Value: " + ms.isValid((MapSchema) testMap.get(entry.getKey())));
                }*/

            }

        }

        return isValid;
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
