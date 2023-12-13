package hexlet.code.schemas;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class BaseSchema {
    //required — делает данные обязательными для заполнения. Не null.
    protected boolean required;

    protected List<Predicate<Object>> checkouts = new ArrayList<>();

    public final <T> boolean isValid(T t) {

        boolean rez = true;
        //System.out.println("- isValid - checking started");
        //System.out.println("" + t + " - " + (t instanceof Map));

        if (required || t instanceof Map) {
            for (Predicate<Object> checkout: checkouts) {
                if (!checkout.test(t)) {
                    rez = false;
                    break;
                }
            }
        } else {
            if (!checkouts.get(0).test(t)) {
                rez = false;
            }
        }


        //System.out.println("- isValid - checking complite: " + rez);
        return rez;
    }

}
