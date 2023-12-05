package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class BaseSchema {
    //required — делает данные обязательными для заполнения. Не null.
    protected boolean required;

    protected List<Predicate<Object>> checkouts = new ArrayList<>();

    /*public boolean isValid(Object testObject) {
        if (testObject == null) {
            return !required;
        }
        return true;
    }*/

    //Predicate<BaseSchema> isValidP;

    public final <T> boolean isValid(T t) {
        //return isValidP.test(t);
        //return getIsValidP().isValidP.test(t);

        boolean rez = true;
        for (Predicate<Object> checkout: checkouts) {
            if (!checkout.test(t)) {
                rez = false;
            }
            //System.out.println("" + checkout + " - " + checkout.equals(t));
        }

        return rez;
    }

    /*private BaseSchema getIsValidP() {
        return (BaseSchema) isValidP;
    }*/

    /*public BaseSchema() {
        isValidP = t -> {
            return true;
        };
    }*/

}
