package com.googplay;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class gReq extends Service {
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void onCreate() {
        super.onCreate();
        gAuth.Init(this, this);
    }

    public void onDestroy() {
        gAuth.Destroy();
        super.onDestroy();
    }

    public void onTaskRemoved(Intent intent) {
        stopSelf();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        super.onTaskRemoved(intent);
    }
}
