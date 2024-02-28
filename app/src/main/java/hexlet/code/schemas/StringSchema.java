package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {
    //minLength() — добавляет в схему ограничение минимальной длины для строки. Строка должна быть равна или длиннее.
    private int minLength;
    //contains() — добавляет в схему ограничение по содержимому строки. Строка должна содержать определённую подстроку
    private String contains = null;

    /*public StringSchema() {
        super.checkouts.add(s -> {
            if (required) {
                if (s == null) {
                    return false;
                } else {
                    return !s.equals("");
                }
            }
            return true;
        });
    }*/

    public StringSchema() {
        super.checkouts.put("isString", s -> (s == null) || (s.getClass() == String.class));
    }

    public StringSchema required() {
        super.required = true;
        return this;
    }

    public StringSchema minLength(int strMinLength) {
        this.minLength = strMinLength;
        super.checkouts.put("minLength", s -> {
            if (s instanceof String) {
                return s.toString().length() >= minLength;
            }
            return false;
        });
        return this;
    }

    public StringSchema contains(String includeString) {
        this.contains = includeString;

        super.checkouts.put("contains", s -> {
            if (!(s instanceof String) || contains == null) {
                return false;
            }
            return (s.toString().contains(contains));
        });
        return this;
    }

}
