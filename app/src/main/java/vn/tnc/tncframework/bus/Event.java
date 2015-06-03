package vn.tnc.tncframework.bus;

/**
 * Created by md101 on 5/31/15.
 */
public enum  Event {
    USER_DETAIL;

    public Object extras;

    public Event withExtras(Object extras){
        this.extras = extras;
        return this;
    }

}
