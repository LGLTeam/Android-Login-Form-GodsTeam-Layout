package com.googplay;

import android.content.Context;
import android.content.SharedPreferences;

public class Prefs {
    public static Prefs prefsInstance;
    public SharedPreferences sharedPreferences;

    public Prefs(Context context) {
        Context applicationContext = context.getApplicationContext();
        this.sharedPreferences = applicationContext.getSharedPreferences(context.getPackageName() + "_preferences", 0);
    }

    public static Prefs with(Context context) {
        if (prefsInstance == null) {
            prefsInstance = new Prefs(context);
        }
        return prefsInstance;
    }

    public String read(String str, String str2) {
        return this.sharedPreferences.getString(str, str2);
    }

    public void write(String str, String str2) {
        this.sharedPreferences.edit().putString(str, str2).apply();
    }
}
