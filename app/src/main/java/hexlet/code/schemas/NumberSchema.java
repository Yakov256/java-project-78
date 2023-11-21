package hexlet.code.schemas;

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

    public boolean isValid(int number) {
        boolean rez = true;

        if (positive && number < 0) {
            rez = false;
        }

        if (rangeMin > number || number > rangeMax) {
            rez = false;
        }

        return rez;
    }

    public boolean isValid(String strNumber) {
        //boolean rez = true;

        if (strNumber == null) {
            return !required;
        }
/*
        Integer number = Integer.parseInt(strNumber);

        if (positive && number < 0) {
            rez = false;
        }

        if (rangeMin > number || number > rangeMax) {
            rez = false;
        }

        return rez;*/
        return false;
    }

}
