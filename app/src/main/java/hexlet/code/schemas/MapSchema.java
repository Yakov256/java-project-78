package hexlet.code.schemas;

import java.util.Map;
//import java.util.Objects;

public final class MapSchema extends BaseSchema {

    //sizeof — добавляет ограничение на размер словаря.
    // Количество пар ключ-значений в объекте Map должно быть равно заданному
    private Integer sizeof = null;

    private Map<String, BaseSchema> schemas;

    public MapSchema required() {
        super.required = true;
        return this;
    }

    public MapSchema sizeof(Integer size) {
        this.sizeof = size;

        super.checkouts.add(m -> (((Map) m).size() == sizeof));
        return this;
    }


    public void shape(Map<String, BaseSchema> newSchemas) {

        super.checkouts.add(m -> {
            //System.out.println("BaseSchema - shape");
            if (schemas == null) {
                return false;
            }

            for (Map.Entry<String, BaseSchema> entry : schemas.entrySet()) {
                if (((Map) m).containsKey(entry.getKey())) {
                    //System.out.println("--------------------------------------");
                    //System.out.println("entry.getValue() - " + entry.getValue()
                    // + " required: " + entry.getValue().required);
                    //System.out.println("entry.getKey() - " + entry.getKey());
                    //System.out.println("m - " + m + " check: " + (entry.getValue().isValid(m)));
                    //if (!entry.getValue().isValid(((Map) m).get(entry.getKey()))) {
                    if (!entry.getValue().isValid(((Map) m).get(entry.getKey()))) {
                        return false;
                    }
                }
            }

            return true;
        });
        this.schemas = newSchemas;
    }

    public MapSchema() {
        super.checkouts.add(m -> {
            //System.out.println("MapSchema - required check: " + (m instanceof Map<?, ?> || !required));
            return (m instanceof Map<?, ?> || !required);
        });
    }
}
