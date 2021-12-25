package entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.vertx.core.json.JsonObject;
import io.vertx.core.json.jackson.DatabindCodec;
import lombok.val;

import java.util.Map;

public abstract class VertxEntity {
    private VertxEntity(){}

    public abstract JsonObject toJson();

    public JsonObject toJson(boolean skipNull) {
        if (skipNull) {
            val mapper = DatabindCodec.mapper().copy().setSerializationInclusion(JsonInclude.Include.NON_NULL);
            val map = mapper.convertValue(this, Map.class);
            return new JsonObject(map);
        } else {
            return JsonObject.mapFrom(this);
        }
    }
}
