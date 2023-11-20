package hexlet.code;

import java.util.Map;

public class MapSchema extends BaseSchema {
    //required() ограничение, которое не позволяет использовать null в качестве значения. Требуется тип данных Map
    //private boolean required;

    //sizeof() — добавляет ограничение на размер словаря.
    // Количество пар ключ-значений в объекте Map должно быть равно заданному
    private Integer sizeof = null;

    public MapSchema required() {
        super.required = true;
        return this;
    }

    public MapSchema sizeof(Integer size) {
        this.sizeof = size;
        return this;
    }


    public boolean isValid(Map testMap) {
        boolean rez = true;

        if (testMap == null) {
            return !required;
        }

        if (sizeof != null && testMap.size() != sizeof) {
            rez = false;
        }

        return rez;
    }

}
