package hexlet.code;

public class StringSchema extends BaseSchema {
    //minLength() — добавляет в схему ограничение минимальной длины для строки. Строка должна быть равна или длиннее.
    private int minLength;
    //contains() — добавляет в схему ограничение по содержимому строки. Строка должна содержать определённую подстроку
    private String contains = null;

    //String testString = "";

    public StringSchema required() {
        super.required = true;
        return this;
    }

    public StringSchema minLength(int strMinLength) {
        this.minLength = strMinLength;
        return this;
    }

    public StringSchema contains(String includeString) {
        this.contains = includeString;
        return this;
    }

    public boolean isValid(String str) {
        boolean rez = true;

        if (str == null) {
            return !(required || contains != null);
        }

        if (required && str.equals("")) {
            rez = false;
        }

        if (str.length() < minLength) {
            rez = false;
        }

        if (contains != null && !(str.contains(contains))) {
            rez = false;
        }

        return rez;
    }

}
