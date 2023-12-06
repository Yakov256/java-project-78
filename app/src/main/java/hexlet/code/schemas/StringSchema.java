package hexlet.code.schemas;

public final class StringSchema extends BaseSchema {
    //minLength() — добавляет в схему ограничение минимальной длины для строки. Строка должна быть равна или длиннее.
    private int minLength;
    //contains() — добавляет в схему ограничение по содержимому строки. Строка должна содержать определённую подстроку
    private String contains = null;

    public StringSchema required() {
        super.required = true;

        super.checkouts.add(s -> {
            if (required) {
                if (s == null) {
                    return !required;
                } else {
                    return !s.equals("");
                }
            }
            return true;
        });
        return this;
    }

    public StringSchema minLength(int strMinLength) {
        this.minLength = strMinLength;

        super.checkouts.add(s -> s.toString().length() >= minLength);
        return this;
    }

    public StringSchema contains(String includeString) {
        this.contains = includeString;

        super.checkouts.add(s -> {
            if (s == null) {
                return false;
            } else {
                return (s.toString().contains(contains));
            }
        });
        return this;
    }

}
