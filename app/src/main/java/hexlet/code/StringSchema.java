package hexlet.code;

public class StringSchema {


    //required() — делает данные обязательными для заполнения. Не null и не пустуя строка.
    private boolean required;

    //minLength() — добавляет в схему ограничение минимальной длины для строки. Строка должна быть равна или длиннее.
    private int minLength;
    //contains() — добавляет в схему ограничение по содержимому строки. Строка должна содержать определённую подстроку
    private String contains;

    String testString = "";

    public StringSchema required() {
        this.required = true;
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

    public boolean isValid(Object strObject) {
        if (required && strObject == null) {
            return false;
        }

        String str = "";
        if (strObject != null) {
            str = strObject.toString();
        }

        if (required && str.equals("")) {
            return false;
        }

        if (minLength > 0 && str.length() < minLength) {
            return false;
        }

        if (contains != null && !(str.contains(contains))) {
            return false;
        }

        return true;
    }



}
