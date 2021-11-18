package com.googplay;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.widget.Toast;

public class MainActivity extends Activity {
    public String GameActivity = "com.dts.freefireth.FFMainActivity";

    public final boolean isServiceRunning() {
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        if (activityManager != null) {
            for (ActivityManager.RunningServiceInfo runningServiceInfo : activityManager.getRunningServices(Integer.MAX_VALUE)) {
                if (gReq.class.getName().equals(runningServiceInfo.service.getClassName())) {
                    return true;
                }
            }
        }
        return false;
    }

    @SuppressLint("ResourceType")
    public void onActivityResult(int i, int i2, Intent intent) {
        if (i == 123 && Build.VERSION.SDK_INT >= 23 && !Settings.canDrawOverlays(this)) {
            Toast.makeText(this, "Please allow this permission, so " + getString(2131427367) + " could be drawn.", Toast.LENGTH_LONG).show();
        }
    }

    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        getWindow().setFlags(1024, 1024);
        if (Build.VERSION.SDK_INT >= 23 && !Settings.canDrawOverlays(this)) {
            startActivityForResult(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + getPackageName())), 123);
        }
        if (!getIntent().getStringExtra("CURRVER").equals("5.1")) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Há uma nova versão disponível.");
            builder.setMessage("Versão atual: 5.1\nNova versão: " + getIntent().getStringExtra("CURRVER") + "\n\nNotes da nova versão:\n" + getIntent().getStringExtra("NOTEVER") + "\n\nCaso não queira usar o menu clique em Iniciar sem Menu");
            builder.setPositiveButton("Download", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent("android.intent.action.VIEW");
                    intent.setData(Uri.parse(MainActivity.this.getIntent().getStringExtra("DOWNLK")));
                    MainActivity.this.startActivity(intent);
                }
            });
            builder.setNegativeButton("Iniciar sem menu", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialogInterface, int i) {
                    MainActivity.this.finish();
                }
            });
            builder.setCancelable(false);
            builder.show();
            return;
        }
        startPatcher();
        try {
            startActivity(new Intent(this, Class.forName(this.GameActivity)));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void onDestroy() {
        super.onDestroy();
    }

    public final void startFloater() {
        if (!isServiceRunning()) {
            new Handler().postDelayed(new Runnable() {
                public void run() {
                    MainActivity mainActivity = MainActivity.this;
                    mainActivity.startService(new Intent(mainActivity.getApplicationContext(), gReq.class));
                }
            }, 10000);
            finish();
        }
    }

    public void startPatcher() {
        if (Build.VERSION.SDK_INT < 23 || Settings.canDrawOverlays(this)) {
            startFloater();
            return;
        }
        startActivityForResult(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + getPackageName())), 123);
    }
}
