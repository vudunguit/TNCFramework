package vn.tnc.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by md101 on 5/8/15.
 */
public class PreferencesUtils {

    private Context mContext;
    private String prefsDataName;

    public PreferencesUtils(Context context, String prefsDataName) {
        this.mContext = context;
        this.prefsDataName = prefsDataName;
    }

    private SharedPreferences getSharePreferences() {
        return mContext.getSharedPreferences(prefsDataName, Context.MODE_PRIVATE);
    }

    private SharedPreferences.Editor getPreferencesEditor() {
        SharedPreferences prefs = getSharePreferences();
        SharedPreferences.Editor editor = prefs.edit();
        return editor;
    }

    public boolean clearSharePreferences() {
        return getSharePreferences().edit().clear().commit();
    }

    public boolean removeKey(String key) {
        return getSharePreferences().edit().remove(key).commit();
    }

    public boolean setStringValue(String key, String value) {
        return getPreferencesEditor().putString(key, value).commit();
    }

    public String getStringValue(String key) {
        return getSharePreferences().getString(key, null);
    }

    public boolean setIntValue(String key, int value) {
        return getPreferencesEditor().putInt(key, value).commit();
    }

    public int getIntValue(Context context, String key) {
        return getSharePreferences().getInt(key, -1);
    }

    public boolean setLongValue(String key, long value) {
        return getPreferencesEditor().putLong(key, value).commit();
    }

    public long getLongValue(String key) {
        return getSharePreferences().getLong(key, -1L);
    }

    public boolean getBooleanValue(String key) {
        return getSharePreferences().getBoolean(key, false);
    }

    public boolean setBooleanValue(String key, boolean value) {
        return getPreferencesEditor().putBoolean(key, value).commit();
    }
}
