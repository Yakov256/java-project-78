package hexlet.code.schemas;

//import java.util.function.Predicate;

public final class NumberSchema extends BaseSchema {
    //private boolean required;
    //positive — добавляет ограничение на знак числа. Число должно быть положительным
    private boolean positive;
    //range — добавляет допустимый диапазон, в который должно попадать значение числа включая границы
    private int rangeMin = Integer.MIN_VALUE;
    private int rangeMax = Integer.MAX_VALUE;

    /*public NumberSchema() {
        super.checkouts.add(n -> (n instanceof Number) || !required);
    }*/

    public NumberSchema() {
        super.checkouts.put("isNumber", n -> (n == null) || (n instanceof Integer));
    }

    public NumberSchema required() {
        required = true;
        return this;
    }

    public NumberSchema positive() {
        this.positive = true;
        super.checkouts.put("positive", n -> {
            boolean rez;

            if (n instanceof Number) {
                rez = (Integer) n > 0;
            } else {
                rez = !required;
            }

            return rez;
        });
        return this;
    }
//
    public NumberSchema range(int min, int max) {
        this.rangeMin = min;
        this.rangeMax = max;

        super.checkouts.put("range", n -> rangeMin <= (Integer) n && (Integer) n <= rangeMax);
        return this;
    }

}
