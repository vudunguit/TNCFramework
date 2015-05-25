package vn.tnc.data.api.model.request;

/**
 * Created by USER on 5/25/2015.
 */
public enum  Section {
    HOT("hot"),
    TOP("top"),
    USER("user");

    public final String value;

    Section(String value){
        this.value = value;
    }


    @Override
    public String toString() {
        return value;
    }
}
