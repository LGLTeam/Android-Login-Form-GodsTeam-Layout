package com.googplay;

import android.content.Context;
import android.content.Intent;

public class BTLoginActivity {
    public static void BBLogger(Context context) {
        try {
            context.startActivity(new Intent(context, Class.forName("com.googplay.gactivity")));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
