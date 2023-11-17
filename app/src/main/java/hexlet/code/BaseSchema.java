package hexlet.code;

public class BaseSchema {
    //required() — делает данные обязательными для заполнения. Не null и не пустуя строка.
    protected boolean required;

    /*public BaseSchema required() {
        this.required = true;
        return this;
    }*/

    public boolean isValid(Object testObject) {
        if (testObject == null) {
            return !required;
        }
        return true;
    }

}