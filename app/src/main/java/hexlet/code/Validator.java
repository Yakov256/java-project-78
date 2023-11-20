package hexlet.code;

public class Validator {
    //private StringSchema sSchema;
    //private NumberSchema nSchema;
    private BaseSchema schema;

    public StringSchema string() {
        StringSchema strSchema = new StringSchema();
        schema = strSchema;
        return strSchema;
    }

    public NumberSchema number() {
        NumberSchema numberSchema = new NumberSchema();
        schema = numberSchema;
        return numberSchema;
    }

    public MapSchema map() {
        MapSchema mapSchema = new MapSchema();
        schema = mapSchema;
        return mapSchema;
    }

}
