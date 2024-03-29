package hexlet.code.schemas;

import java.util.Map;
//import java.util.Objects;

public final class MapSchema extends BaseSchema {

    //sizeof — добавляет ограничение на размер словаря.
    // Количество пар ключ-значений в объекте Map должно быть равно заданному
    private Integer sizeof = null;

    private Map<String, BaseSchema> schemas;

    /*public MapSchema() {
        super.checkouts.put(m -> {
            //return ("required", m instanceof Map<?, ?> || !required);
            return ("required", m -> (instanceof Map<?, ?> || !required));
        });
    }*/

    public MapSchema() {
        super.checkouts.put("isMap", m -> (m == null) || (m instanceof Map<?, ?>));
    }

    public MapSchema required() {
        super.required = true;
        return this;
    }

    public MapSchema sizeof(Integer size) {
        this.sizeof = size;

        //super.checkouts.add(m -> (((Map) m).size() == size));
        super.checkouts.put("sizeof", m -> (((Map) m).size() == sizeof));
        return this;
    }

    public void shape(Map<String, BaseSchema> newSchemas) {

        super.checkouts.put("shape", m -> {
            if (schemas == null) {
                return false;
            }

            for (Map.Entry<String, BaseSchema> entry : schemas.entrySet()) {
                if (((Map) m).containsKey(entry.getKey())) {
                    if (!entry.getValue().isValid(((Map) m).get(entry.getKey()))) {
                        return false;
                    }
                }
            }
            return true;
        });
        this.schemas = newSchemas;
    }

}
