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

        super.checkouts.add(m -> m != null && m instanceof Map<?, ?>);
        return this;
    }

    public MapSchema sizeof(Integer size) {
        this.sizeof = size;

        super.checkouts.add(m -> (m != null && ((Map) m).size() == sizeof));
        return this;
    }


    public void shape(Map<String, BaseSchema> newSchemas) {

        super.checkouts.add(m -> {
            if (m == null || schemas == null) {
                return false;
            }

            for (Map.Entry<String, BaseSchema> entry : schemas.entrySet()) {
                //System.out.println(entry.getKey() + ":" + entry.getValue());
                if (((Map) m).containsKey(entry.getKey())) {
                    //((Map) m).get(entry.getKey())
                    if (!entry.getValue().isValid(((Map) m).get(entry.getKey()))) {
                        return false;
                    }
                }

            }

            return true;
        });
        this.schemas = newSchemas;
    }

    /*
    private boolean commonIsValid(BaseSchema bs, Object testObject) {
        boolean isValid = true;

        if (bs instanceof StringSchema) {
            StringSchema ss = (StringSchema) bs;
            if (!ss.isValid((String) testObject)) {
                isValid = false;
            }

        } else if (bs instanceof NumberSchema) {
            NumberSchema ns = (NumberSchema) bs;
            if (testObject == null) {
                if (!ns.isValid((String) testObject)) {
                    isValid = false;
                }
            } else {
                if (!ns.isValid((int) testObject)) {
                    isValid = false;
                }
            }

        } else if (bs instanceof MapSchema) {
            MapSchema ms = (MapSchema) bs;
            if (!ms.isValid((Map) testObject)) {
                isValid = false;
            }
        }

        return isValid;
    }

    private boolean deepIsValid(Map testMap) {
        boolean isValid = true;

        for (Map.Entry<String, BaseSchema> entry : schemas.entrySet()) {

            if (!commonIsValid(entry.getValue(), testMap.get(entry.getKey()))) {
                isValid = false;
                break;
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
    */

}
