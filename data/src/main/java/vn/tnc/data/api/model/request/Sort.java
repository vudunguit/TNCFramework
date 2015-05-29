package vn.tnc.data.api.model.request;

/**
 * Created by USER on 5/25/2015.
 */
public enum  Sort {
    VIRAL("viral"),
    TIME("time");

    private final String value;

    Sort(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
