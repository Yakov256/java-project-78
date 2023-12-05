package hexlet.code.schemas;

import java.util.Objects;
import java.util.function.Predicate;

public final class NumberSchema extends BaseSchema {
    private boolean required;
    //positive — добавляет ограничение на знак числа. Число должно быть положительным
    private boolean positive;
    //range — добавляет допустимый диапазон, в который должно попадать значение числа включая границы
    private int rangeMin = Integer.MIN_VALUE;
    private int rangeMax = Integer.MAX_VALUE;

    Predicate<Integer> isValidP;

    public NumberSchema required() {
        this.required = true;

        System.out.println("required - super.checkouts.add(n -> n != null)");
        super.checkouts.add(n -> n != null);
        return this;
    }

    public NumberSchema positive() {
        this.positive = true;

        System.out.println("positive() - super.checkouts.add(n -> (Integer) n >= 0);");
        super.checkouts.add(n -> (Integer) n >= 0);
        return this;
    }

    public NumberSchema range(int min, int max) {
        this.rangeMin = min;
        this.rangeMax = max;

        super.checkouts.add(n -> rangeMin > (Integer) n || (Integer) n > rangeMax);
        return this;
    }

    /*public boolean isValid(int number) {
        boolean rez = true;

        if (positive && number <= 0) {
            rez = false;
        }

        if (rangeMin > number || number > rangeMax) {
            rez = false;
        }

        return rez;
    }*/

    /*public boolean isValid(String strNumber) {
        if (strNumber == null) {
            return !required;
        }
        return false;
    }*/

    public Predicate<Integer> getIsValidP() {
        return isValidP;
    }

    public NumberSchema() {
        isValidP = number -> {
            boolean rez = true;
            System.out.println("isValidP:NumberSchema");

            if (positive && number <= 0) {
                rez = false;
            }

            if (rangeMin > number || number > rangeMax) {
                rez = false;
            }

            return rez;
        };
    }
}
