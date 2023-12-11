package hexlet.code.schemas;

//import java.util.function.Predicate;

public final class NumberSchema extends BaseSchema {
    //private boolean required;
    //positive — добавляет ограничение на знак числа. Число должно быть положительным
    private boolean positive;
    //range — добавляет допустимый диапазон, в который должно попадать значение числа включая границы
    private int rangeMin = Integer.MIN_VALUE;
    private int rangeMax = Integer.MAX_VALUE;

    public NumberSchema required() {
        required = true;

        super.checkouts.add(n -> {
            if (n == null) {
                return !required;
            } else {
                return n instanceof Number;
            }
        });
        return this;
    }

    public NumberSchema positive() {
        this.positive = true;

        super.checkouts.add(n -> {
            boolean rez = true;
            if (n == null) {
                if (required) {
                    return false;
                } else if (positive) {
                    return true;
                }
            } else {
                if (n instanceof Number) {
                    rez = (Integer) n > 0;
                } else {
                    rez = false;
                }
            }

            return rez;
        });
        return this;
    }

    public NumberSchema range(int min, int max) {
        this.rangeMin = min;
        this.rangeMax = max;

        super.checkouts.add(n -> rangeMin <= (Integer) n && (Integer) n <= rangeMax);
        return this;
    }

}
