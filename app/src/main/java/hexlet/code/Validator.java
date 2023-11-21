package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import hexlet.code.schemas.MapSchema;
import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;

public final class Validator {
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
