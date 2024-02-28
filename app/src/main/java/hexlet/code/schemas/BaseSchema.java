package hexlet.code.schemas;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Predicate;

public class BaseSchema <T> {
    //required — делает данные обязательными для заполнения. Не null.
    protected boolean required;

    //protected List<Predicate<Object>> checkouts = new ArrayList<>();
    protected Map<String, Predicate<Object>> checkouts = new LinkedHashMap<>();

    public final boolean isValid(Object obj) {
        if (obj == null) {
            return !required;
        }

        /*if (!required) {
            if (!checkouts.get("required").test(obj)) {
                return true;
            }
        }*/
        return checkouts.values().stream().allMatch(x -> x.test(obj));
    }

    /*public final <T> boolean isValid(T t) {
        boolean rez = true;

        //checkouts.values().stream().allMatch(x -> x.test(obj));

        for (Predicate<Object> checkout: checkouts) {
            if (!checkout.test(t)) {
                rez = false;
                break;
            }
        }

        return rez;
    }*/
}
