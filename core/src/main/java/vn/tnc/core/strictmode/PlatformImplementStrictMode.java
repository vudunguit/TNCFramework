package vn.tnc.core.strictmode;

/**
 * Created by USER on 5/19/2015.
 */
public class PlatformImplementStrictMode {

    public static IStrictMode getStrictMode(){
        if(Platform.SUPPORTS_HONEYCOMB){
            return new HoneycombStrictMode();
        } else if(Platform.SUPPORTS_GINGERBREAD){
            return new LegacyStrictMode();
        } else{
            return null;
        }
    }
}
