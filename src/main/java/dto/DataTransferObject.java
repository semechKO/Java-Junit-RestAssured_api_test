package dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import serializer.JsonSerializer;

public abstract class DataTransferObject {

    @Override
    public String toString() {
        return JsonSerializer.getJson(this, JsonInclude.Include.NON_NULL);
    }

    /**
     * Return string with custom json-inclusion (if we need to include null-fields, for example)
     * @return - json with custom inclusion
     */
    public String toStringWithCustomInclusion() {
        return JsonSerializer.getJson(this, JsonInclude.Include.USE_DEFAULTS);
    }
}
