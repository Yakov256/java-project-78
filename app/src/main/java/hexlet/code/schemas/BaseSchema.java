package hexlet.code.schemas;

public class BaseSchema {
    //required — делает данные обязательными для заполнения. Не null.
    protected boolean required;

    /*public boolean isValid(Object testObject) {
        if (testObject == null) {
            return !required;
        }
        return true;
    }*/

}
