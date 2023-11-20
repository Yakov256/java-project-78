package hexlet.code;

public class Validator {
    //private StringSchema sSchema;
    //private NumberSchema nSchema;
    private BaseSchema schema;

    public StringSchema string() {
        return (StringSchema) schema;
    }

    public NumberSchema number() {
        return (NumberSchema) schema;
    }

    public MapSchema map() {
        return (MapSchema) schema;
    }

}
