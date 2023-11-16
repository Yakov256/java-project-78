package hexlet.code;

public class Validator {
    private StringSchema sSchema;
    private NumberSchema nSchema;

    public StringSchema string() {
        return sSchema;
    }

    public NumberSchema number() {
        return nSchema;
    }

}
