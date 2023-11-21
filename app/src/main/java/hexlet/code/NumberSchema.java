package hexlet.code;

public class NumberSchema extends BaseSchema {
    private boolean required;
    //positive — добавляет ограничение на знак числа. Число должно быть положительным
    private boolean positive;
    //range — добавляет допустимый диапазон, в который должно попадать значение числа включая границы
    private int rangeMin = Integer.MIN_VALUE;
    private int rangeMax = Integer.MAX_VALUE;

    public NumberSchema required() {
        this.required = true;
        return this;
    }

    public NumberSchema positive() {
        this.positive = true;
        return this;
    }

    public NumberSchema range(int min, int max) {
        this.rangeMin = min;
        this.rangeMax = max;
        return this;
    }

    public boolean isValid(Integer number) {
        boolean rez = true;

        if (number == null) {
            return !required;
        }

        if (positive && number < 0) {
            rez = false;
        }

        if (rangeMin < number || number > rangeMax) {
            rez = false;
        }

        return rez;
    }

}
