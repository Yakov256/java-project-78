package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema {

    //sizeof — добавляет ограничение на размер словаря.
    // Количество пар ключ-значений в объекте Map должно быть равно заданному
    private Integer sizeof = null;

    private Map<String, BaseSchema> schemas;

    public MapSchema required() {
        super.required = true;

        super.checkouts.add(m -> m != null);
        return this;
    }

    public MapSchema sizeof(Integer size) {
        this.sizeof = size;

        //super.checkouts.add(m -> m );
        return this;
    }

    public void shape(Map<String, BaseSchema> newSchemas) {
        this.schemas = newSchemas;
    }

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
            /*
            if (entry.getValue() instanceof StringSchema) {
                StringSchema ss = (StringSchema) entry.getValue();
                if (!ss.isValid((String) testMap.get(entry.getKey()))) {
                    isValid = false;
                }

            } else if (entry.getValue() instanceof NumberSchema) {
                NumberSchema ns = (NumberSchema) entry.getValue();
                if (!ns.isValid((Integer) testMap.get(entry.getKey()))) {
                    isValid = false;
                }

            } else if (entry.getValue() instanceof MapSchema) {
                MapSchema ms = (MapSchema) entry.getValue();
                if (!ms.isValid((Map) testMap.get(entry.getKey()))) {
                    isValid = false;
                }
            }*/

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
