package vn.tnc.core.utils;

import com.squareup.otto.Bus;

import javax.inject.Inject;


/**
 * Created by USER on 5/25/2015.
 */
public class OttoBus {
    private static Bus BUS = new Bus();

    @Inject
    public OttoBus(){

    }

    public void register(Object o){
        BUS.register(o);
    }

    public void unregister(Object o){
        BUS.unregister(o);
    }

    public void post(Object o){
        BUS.post(o);
    }
}
