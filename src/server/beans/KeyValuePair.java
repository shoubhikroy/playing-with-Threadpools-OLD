package server.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public class KeyValuePair
{

    private final String key;
    private final String value;

    public KeyValuePair(String key, String value)
    {
        this.key = key;
        this.value = value;
    }

    @JsonProperty("key")
    public String getFirstName()
    {
        return key;
    }

    @JsonProperty("value")
    public String getLastName()
    {
        return value;
    }
}
