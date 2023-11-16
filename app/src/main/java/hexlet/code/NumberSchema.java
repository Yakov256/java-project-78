package hexlet.code;

public class NumberSchema {

    //required() — добавляет в схему ограничение, которое не позволяет использовать null в качестве значения
    private boolean required;
    //positive() — добавляет ограничение на знак числа. Число должно быть положительным
    private boolean positive;
    //range() — добавляет допустимый диапазон, в который должно попадать значение числа включая границы
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

    public boolean isValid(Integer numberObject) {
        if (numberObject == null) {
            return !required;
        }

        int number = numberObject;

        if (positive && number < 0) {
            return false;
        }

        if (number > rangeMax) {
            return false;
        }

        if (number < rangeMin) {
            return false;
        }

        return true;
    }

}
