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
        //System.out.println("NumberSchema - required");
        required = true;
        return this;
    }

    public NumberSchema positive() {
        this.positive = true;
        super.checkouts.add(n -> {
            boolean rez;

            if (n instanceof Number) {
                rez = (Integer) n > 0;
            } else {
                rez = !required;
                //rez = false;
            }

            //System.out.println("NumberSchema - positive: " + rez);
            //System.out.println("this.positive: " + this.positive);
            //System.out.println("n:" + n);
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

    public NumberSchema() {
        super.checkouts.add(n -> (n instanceof Number) || !required);
    }
}
