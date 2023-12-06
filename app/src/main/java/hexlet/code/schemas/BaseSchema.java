package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BaseSchema {
    //required — делает данные обязательными для заполнения. Не null.
    protected boolean required;

    protected List<Predicate<Object>> checkouts = new ArrayList<>();

    /*public void required(){
        required = true;
    }*/

    public final <T> boolean isValid(T t) {

        boolean rez = true;
        for (Predicate<Object> checkout: checkouts) {
            if (!checkout.test(t)) {
                rez = false;
            }
        }

        return rez;
    }

}
