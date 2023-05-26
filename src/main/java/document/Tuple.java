package document;

import java.util.Map;

public class Tuple implements Map.Entry<String, String> {
    String key;
    String value;

    public Tuple() {
        this.key = "";
        this.value = "";
    }

    public Tuple(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public String setValue(String value) {
        this.value = value;
        return this.value;
    }
}
