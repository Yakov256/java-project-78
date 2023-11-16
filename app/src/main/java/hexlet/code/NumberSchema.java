package hexlet.code;

public class NumberSchema {

    //required() — добавляет в схему ограничение, которое не позволяет использовать null в качестве значения
    private boolean required;
    //positive() — добавляет ограничение на знак числа. Число должно быть положительным
    private boolean positive;
    //range() — добавляет допустимый диапазон, в который должно попадать значение числа включая границы
    private double rangeMin;
    private double rangeMax;


}
