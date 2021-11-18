package com.googplay;

import android.app.ActivityManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.Process;
import android.provider.Settings;
import android.widget.Toast;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;
import java.util.UUID;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import org.json.JSONObject;

public class Auth extends AsyncTask<String, Void, String> {
    public byte[] GODSTEAM_AUTENTICATION_PRIVATE_SERVER_NET = {45, 45, 45, 45, 45, 66, 69, 71, 73, 78, 32, 67, 69, 82, 84, 73, 70, 73, 67, 65, 84, 69, 45, 45, 45, 45, 45, 13, 10, 77, 73, 73, 70, 54, 122, 67, 67, 66, 78, 79, 103, 65, 119, 73, 66, 65, 103, 73, 82, 65, 73, 68, 53, 88, 53, 112, 118, 85, 72, 71, 112, 43, 81, 49, 84, 54, 88, 103, 117, 67, 78, 81, 119, 68, 81, 89, 74, 75, 111, 90, 73, 104, 118, 99, 78, 65, 81, 69, 76, 66, 81, 65, 119, 13, 10, 99, 106, 69, 76, 77, 65, 107, 71, 65, 49, 85, 69, 66, 104, 77, 67, 86, 86, 77, 120, 67, 122, 65, 74, 66, 103, 78, 86, 66, 65, 103, 84, 65, 108, 82, 89, 77, 82, 65, 119, 68, 103, 89, 68, 86, 81, 81, 72, 69, 119, 100, 73, 98, 51, 86, 122, 100, 71, 57, 117, 77, 82, 85, 119, 13, 10, 69, 119, 89, 68, 86, 81, 81, 75, 69, 119, 120, 106, 85, 71, 70, 117, 90, 87, 119, 115, 73, 69, 108, 117, 89, 121, 52, 120, 76, 84, 65, 114, 66, 103, 78, 86, 66, 65, 77, 84, 74, 71, 78, 81, 89, 87, 53, 108, 98, 67, 119, 103, 83, 87, 53, 106, 76, 105, 66, 68, 90, 88, 74, 48, 13, 10, 97, 87, 90, 112, 89, 50, 70, 48, 97, 87, 57, 117, 73, 69, 70, 49, 100, 71, 104, 118, 99, 109, 108, 48, 101, 84, 65, 101, 70, 119, 48, 120, 79, 84, 65, 51, 77, 68, 77, 119, 77, 68, 65, 119, 77, 68, 66, 97, 70, 119, 48, 120, 79, 84, 69, 119, 77, 68, 69, 121, 77, 122, 85, 53, 13, 10, 78, 84, 108, 97, 77, 66, 77, 120, 69, 84, 65, 80, 66, 103, 78, 86, 66, 65, 77, 84, 67, 71, 116, 116, 98, 50, 82, 122, 76, 109, 49, 115, 77, 73, 73, 66, 73, 106, 65, 78, 66, 103, 107, 113, 104, 107, 105, 71, 57, 119, 48, 66, 65, 81, 69, 70, 65, 65, 79, 67, 65, 81, 56, 65, 13, 10, 77, 73, 73, 66, 67, 103, 75, 67, 65, 81, 69, 65, 113, 68, 77, 48, 52, 103, 116, 121, 106, 80, 76, 114, 117, 97, 113, 103, 70, 84, 56, 71, 99, 49, 112, 103, 68, 48, 77, 49, 71, 78, 49, 86, 43, 49, 78, 56, 89, 102, 76, 50, 89, 65, 98, 48, 105, 100, 118, 48, 100, 87, 108, 116, 13, 10, 66, 106, 43, 116, 49, 100, 117, 82, 111, 80, 52, 55, 89, 43, 98, 81, 65, 69, 104, 79, 67, 43, 47, 110, 108, 97, 88, 113, 115, 115, 85, 65, 101, 116, 72, 68, 67, 69, 89, 100, 79, 54, 117, 110, 109, 88, 71, 113, 48, 82, 75, 119, 53, 114, 79, 80, 77, 103, 65, 113, 99, 81, 79, 48, 13, 10, 50, 86, 81, 85, 80, 86, 56, 120, 53, 119, 85, 111, 121, 85, 52, 76, 115, 121, 54, 98, 79, 57, 86, 48, 68, 83, 72, 120, 89, 77, 114, 71, 43, 97, 107, 83, 65, 56, 121, 73, 82, 87, 104, 77, 81, 84, 115, 51, 105, 109, 118, 78, 111, 73, 78, 83, 90, 111, 111, 70, 72, 110, 75, 100, 13, 10, 115, 83, 76, 68, 79, 89, 81, 78, 51, 118, 80, 112, 121, 53, 75, 121, 69, 116, 76, 110, 70, 84, 116, 88, 83, 89, 49, 74, 120, 79, 67, 71, 52, 52, 88, 114, 106, 119, 83, 82, 97, 122, 79, 66, 117, 84, 121, 102, 86, 98, 56, 100, 116, 88, 79, 83, 118, 78, 85, 101, 69, 88, 104, 52, 13, 10, 118, 107, 72, 54, 103, 109, 90, 98, 67, 83, 53, 98, 71, 99, 117, 88, 118, 89, 49, 71, 77, 114, 97, 68, 114, 117, 116, 50, 89, 83, 121, 72, 48, 71, 80, 102, 65, 73, 119, 75, 83, 112, 120, 90, 47, 52, 82, 74, 75, 97, 85, 67, 75, 49, 114, 56, 48, 103, 79, 57, 67, 121, 76, 50, 13, 10, 57, 78, 100, 117, 100, 101, 77, 100, 103, 71, 85, 72, 88, 122, 80, 80, 87, 70, 122, 56, 100, 73, 117, 72, 49, 119, 104, 109, 108, 56, 68, 48, 88, 119, 73, 68, 65, 81, 65, 66, 111, 52, 73, 67, 50, 84, 67, 67, 65, 116, 85, 119, 72, 119, 89, 68, 86, 82, 48, 106, 66, 66, 103, 119, 13, 10, 70, 111, 65, 85, 102, 103, 78, 97, 90, 85, 70, 114, 112, 51, 52, 75, 52, 98, 105, 100, 67, 79, 111, 100, 106, 104, 49, 113, 120, 50, 85, 119, 72, 81, 89, 68, 86, 82, 48, 79, 66, 66, 89, 69, 70, 77, 113, 103, 116, 109, 77, 102, 100, 97, 84, 87, 76, 108, 97, 107, 72, 107, 99, 116, 13, 10, 83, 112, 72, 77, 70, 122, 69, 48, 77, 65, 52, 71, 65, 49, 85, 100, 68, 119, 69, 66, 47, 119, 81, 69, 65, 119, 73, 70, 111, 68, 65, 77, 66, 103, 78, 86, 72, 82, 77, 66, 65, 102, 56, 69, 65, 106, 65, 65, 77, 66, 48, 71, 65, 49, 85, 100, 74, 81, 81, 87, 77, 66, 81, 71, 13, 10, 67, 67, 115, 71, 65, 81, 85, 70, 66, 119, 77, 66, 66, 103, 103, 114, 66, 103, 69, 70, 66, 81, 99, 68, 65, 106, 66, 80, 66, 103, 78, 86, 72, 83, 65, 69, 83, 68, 66, 71, 77, 68, 111, 71, 67, 121, 115, 71, 65, 81, 81, 66, 115, 106, 69, 66, 65, 103, 73, 48, 77, 67, 115, 119, 13, 10, 75, 81, 89, 73, 75, 119, 89, 66, 66, 81, 85, 72, 65, 103, 69, 87, 72, 87, 104, 48, 100, 72, 66, 122, 79, 105, 56, 118, 99, 50, 86, 106, 100, 88, 74, 108, 76, 109, 78, 118, 98, 87, 57, 107, 98, 121, 53, 106, 98, 50, 48, 118, 81, 49, 66, 84, 77, 65, 103, 71, 66, 109, 101, 66, 13, 10, 68, 65, 69, 67, 65, 84, 66, 77, 66, 103, 78, 86, 72, 82, 56, 69, 82, 84, 66, 68, 77, 69, 71, 103, 80, 54, 65, 57, 104, 106, 116, 111, 100, 72, 82, 119, 79, 105, 56, 118, 89, 51, 74, 115, 76, 109, 78, 118, 98, 87, 57, 107, 98, 50, 78, 104, 76, 109, 78, 118, 98, 83, 57, 106, 13, 10, 85, 71, 70, 117, 90, 87, 120, 74, 98, 109, 78, 68, 90, 88, 74, 48, 97, 87, 90, 112, 89, 50, 70, 48, 97, 87, 57, 117, 81, 88, 86, 48, 97, 71, 57, 121, 97, 88, 82, 53, 76, 109, 78, 121, 98, 68, 66, 57, 66, 103, 103, 114, 66, 103, 69, 70, 66, 81, 99, 66, 65, 81, 82, 120, 13, 10, 77, 71, 56, 119, 82, 119, 89, 73, 75, 119, 89, 66, 66, 81, 85, 72, 77, 65, 75, 71, 79, 50, 104, 48, 100, 72, 65, 54, 76, 121, 57, 106, 99, 110, 81, 117, 89, 50, 57, 116, 98, 50, 82, 118, 89, 50, 69, 117, 89, 50, 57, 116, 76, 50, 78, 81, 89, 87, 53, 108, 98, 69, 108, 117, 13, 10, 89, 48, 78, 108, 99, 110, 82, 112, 90, 109, 108, 106, 89, 88, 82, 112, 98, 50, 53, 66, 100, 88, 82, 111, 98, 51, 74, 112, 100, 72, 107, 117, 89, 51, 74, 48, 77, 67, 81, 71, 67, 67, 115, 71, 65, 81, 85, 70, 66, 122, 65, 66, 104, 104, 104, 111, 100, 72, 82, 119, 79, 105, 56, 118, 13, 10, 98, 50, 78, 122, 99, 67, 53, 106, 98, 50, 49, 118, 90, 71, 57, 106, 89, 83, 53, 106, 98, 50, 48, 119, 77, 65, 89, 68, 86, 82, 48, 82, 66, 67, 107, 119, 74, 52, 73, 73, 97, 50, 49, 118, 90, 72, 77, 117, 98, 87, 121, 67, 68, 87, 49, 104, 97, 87, 119, 117, 97, 50, 49, 118, 13, 10, 90, 72, 77, 117, 98, 87, 121, 67, 68, 72, 100, 51, 100, 121, 53, 114, 98, 87, 57, 107, 99, 121, 53, 116, 98, 68, 67, 67, 65, 81, 81, 71, 67, 105, 115, 71, 65, 81, 81, 66, 49, 110, 107, 67, 66, 65, 73, 69, 103, 102, 85, 69, 103, 102, 73, 65, 56, 65, 66, 50, 65, 76, 118, 90, 13, 10, 51, 55, 119, 102, 105, 110, 71, 49, 107, 53, 81, 106, 108, 54, 113, 83, 101, 48, 99, 52, 86, 53, 85, 75, 113, 49, 76, 111, 71, 112, 67, 87, 90, 68, 97, 79, 72, 116, 71, 70, 65, 65, 65, 66, 97, 55, 108, 90, 120, 106, 81, 65, 65, 65, 81, 68, 65, 69, 99, 119, 82, 81, 73, 104, 13, 10, 65, 79, 87, 50, 80, 65, 114, 105, 114, 77, 84, 54, 57, 116, 75, 52, 112, 107, 121, 50, 108, 108, 74, 84, 51, 112, 117, 79, 77, 122, 76, 114, 106, 73, 87, 82, 52, 119, 71, 54, 119, 72, 104, 49, 65, 105, 65, 116, 117, 107, 121, 114, 78, 111, 71, 86, 90, 65, 87, 115, 43, 50, 74, 101, 13, 10, 65, 107, 57, 107, 87, 76, 104, 57, 76, 72, 121, 104, 98, 66, 80, 43, 49, 66, 83, 68, 121, 50, 74, 71, 100, 119, 66, 50, 65, 72, 82, 43, 50, 111, 77, 120, 114, 84, 77, 81, 107, 83, 71, 99, 122, 105, 86, 80, 81, 110, 68, 67, 118, 47, 49, 101, 81, 105, 65, 73, 120, 106, 99, 49, 13, 10, 101, 101, 89, 81, 101, 56, 120, 87, 65, 65, 65, 66, 97, 55, 108, 90, 120, 108, 77, 65, 65, 65, 81, 68, 65, 69, 99, 119, 82, 81, 73, 103, 81, 102, 103, 118, 90, 66, 85, 80, 100, 102, 122, 119, 100, 67, 83, 67, 101, 118, 77, 80, 75, 52, 79, 57, 105, 80, 111, 101, 101, 75, 98, 74, 13, 10, 67, 120, 56, 85, 89, 69, 109, 74, 105, 109, 111, 67, 73, 81, 68, 121, 120, 107, 43, 113, 98, 104, 85, 70, 54, 49, 89, 53, 114, 79, 68, 117, 47, 52, 122, 72, 118, 116, 116, 105, 53, 66, 56, 85, 114, 120, 65, 73, 109, 82, 109, 86, 52, 115, 65, 85, 117, 68, 65, 78, 66, 103, 107, 113, 13, 10, 104, 107, 105, 71, 57, 119, 48, 66, 65, 81, 115, 70, 65, 65, 79, 67, 65, 81, 69, 65, 70, 72, 116, 51, 47, 102, 87, 115, 80, 111, 110, 47, 55, 74, 81, 72, 84, 50, 66, 76, 82, 111, 50, 112, 55, 103, 111, 73, 73, 51, 52, 112, 115, 121, 103, 67, 50, 55, 72, 106, 87, 120, 110, 109, 13, 10, 57, 50, 121, 102, 107, 74, 68, 106, 113, 87, 99, 90, 56, 110, 79, 48, 113, 73, 114, 116, 100, 79, 85, 84, 103, 114, 115, 108, 82, 83, 89, 67, 111, 71, 97, 108, 111, 49, 119, 90, 81, 54, 107, 73, 71, 89, 117, 71, 115, 97, 89, 121, 110, 71, 78, 68, 67, 56, 109, 47, 86, 76, 52, 75, 13, 10, 116, 72, 65, 110, 120, 108, 49, 79, 106, 49, 105, 68, 77, 117, 43, 48, 89, 115, 113, 114, 69, 80, 72, 107, 78, 111, 81, 109, 118, 99, 108, 47, 75, 86, 88, 74, 47, 121, 74, 122, 68, 56, 118, 114, 69, 74, 47, 107, 110, 103, 55, 106, 51, 85, 51, 101, 115, 122, 98, 114, 69, 71, 117, 90, 13, 10, 79, 54, 77, 122, 105, 90, 47, 54, 121, 105, 56, 56, 108, 65, 109, 65, 81, 71, 79, 103, 105, 84, 108, 76, 117, 113, 69, 68, 97, 110, 57, 112, 107, 116, 76, 72, 78, 55, 84, 115, 82, 53, 85, 50, 119, 84, 103, 68, 112, 50, 111, 83, 114, 116, 50, 52, 110, 49, 111, 90, 116, 47, 115, 117, 13, 10, 49, 53, 66, 47, 82, 119, 55, 97, 116, 74, 78, 43, 49, 66, 110, 83, 43, 47, 102, 81, 108, 51, 98, 118, 57, 80, 97, 108, 56, 48, 65, 80, 66, 88, 107, 121, 100, 97, 90, 109, 50, 90, 120, 86, 68, 103, 49, 65, 83, 105, 47, 118, 119, 120, 55, 68, 49, 106, 104, 101, 71, 81, 111, 100, 13, 10, 56, 66, 115, 88, 48, 53, 110, 80, 73, 83, 51, 68, 111, 97, 79, 83, 121, 85, 76, 83, 106, 66, 76, 120, 109, 111, 51, 111, 86, 81, 106, 69, 55, 116, 48, 106, 99, 115, 66, 47, 87, 65, 61, 61, 13, 10, 45, 45, 45, 45, 45, 69, 78, 68, 32, 67, 69, 82, 84, 73, 70, 73, 67, 65, 84, 69, 45, 45, 45, 45, 45};
    public String expire = "DTEXPIRE";
    public ProgressDialog pDialog;
    public String pass = "";
    public String tokenVer = "";
    public String user = "";
    public WeakReference<gactivity> weakActivity;

    public Auth(gactivity gactivity) {
        this.weakActivity = new WeakReference<>(gactivity);
        ProgressDialog progressDialog = new ProgressDialog(gactivity);
        progressDialog.setCancelable(false);
        this.pDialog = progressDialog;
    }


    public static boolean isInternetAvailable(Context context) {
        NetworkInfo activeNetworkInfo = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public String doInBackground(String... strArr) {
        if (!isInternetAvailable(getActivity())) {
            return "Conecte-se a internet primeiro.";
        }
        try {
            this.user = strArr[0];
            this.pass = strArr[1];
            this.tokenVer = strArr[2];
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("uname", this.user);
            jSONObject.put("pass", this.pass);
            jSONObject.put("load", "1");
            jSONObject.put("cs", getUniqueId(getActivity()));
            String base64 = Utils.toBase64(jSONObject.toString());
            Certificate generateCertificate = CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(this.GODSTEAM_AUTENTICATION_PRIVATE_SERVER_NET));
            KeyStore instance = KeyStore.getInstance(KeyStore.getDefaultType());
            instance.load((InputStream) null, (char[]) null);
            instance.setCertificateEntry("ca", generateCertificate);
            TrustManagerFactory instance2 = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
            instance2.init(instance);
            SSLContext.getInstance("TLS").init((KeyManager[]) null, instance2.getTrustManagers(), (SecureRandom) null);
            HttpsURLConnection httpsURLConnection = (HttpsURLConnection) new URL("url here").openConnection();
            httpsURLConnection.setRequestProperty("User-Agent", "GodsTeam/5.0 (Linux; Android 5.0; SM-G900P Build/LRX21T)/537.36 (KHTML, like Gods)");
            httpsURLConnection.setRequestMethod("POST");
            httpsURLConnection.setDoOutput(true);
            httpsURLConnection.setRequestProperty("Content-Type", "text/plain");
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(httpsURLConnection.getOutputStream());
            outputStreamWriter.write(base64);
            outputStreamWriter.close();
            httpsURLConnection.connect();
            return Utils.fromBase64String(Utils.readStream(httpsURLConnection.getInputStream()));
		} catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public final gactivity getActivity() {
        return (gactivity) this.weakActivity.get();
    }

    public final String getDeviceName() {
        String str = Build.MANUFACTURER;
        String str2 = Build.MODEL;
        if (str2.startsWith(str)) {
            return str2;
        }
        return str + " " + str2;
    }

    public final ProgressDialog getDialog() {
        return this.pDialog;
    }

    public final String getUniqueId(Context context) {
        return UUID.nameUUIDFromBytes((getDeviceName() + Settings.Secure.getString(context.getContentResolver(), "android_id") + Build.HARDWARE).replace(" ", "").getBytes()).toString().replace("-", "");
    }

    public final boolean isServiceRunning() {
        ActivityManager activityManager = (ActivityManager) getActivity().getSystemService(Context.ACTIVITY_SERVICE);
        if (activityManager != null) {
            for (ActivityManager.RunningServiceInfo runningServiceInfo : activityManager.getRunningServices(Integer.MAX_VALUE)) {
                if (gReq.class.getName().equals(runningServiceInfo.service.getClassName())) {
                    return true;
                }
            }
        }
        return false;
    }

    public void onPostExecute(String str) {
        gactivity activity = getActivity();
        if (activity != null) {
            if (getDialog() != null) {
                getDialog().dismiss();
            }
            if (str == null || str.isEmpty()) {
                Toast.makeText(activity, "404 : Erro desconhecido", Toast.LENGTH_LONG).show();
            } else if (str.equals("Sem acesso a internet")) {
                Toast.makeText(activity, str, Toast.LENGTH_LONG).show();
            } else {
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    if (jSONObject.get("Status").toString().equals("Success")) {
                        if (!jSONObject.get("ASDUYZXCCASDWE_CurrUser").toString().toLowerCase().equals(this.user.toLowerCase())) {
                            Toast.makeText(activity, "Usuário está inválido!", Toast.LENGTH_SHORT).show();
                        } else if (!jSONObject.get("ASDUYZXCCASDWE_CurrPass").toString().equals(this.pass)) {
                            Toast.makeText(activity, "Senha está inválida!", Toast.LENGTH_SHORT).show();
                        } else if (!jSONObject.get("ASDUYZXCCASDWE_CurrToken").toString().equals(this.tokenVer)) {
                            Toast.makeText(activity, "Token está inválido!", Toast.LENGTH_SHORT).show();
                        } else {
                            Prefs with = Prefs.with(getActivity());
                            if (Build.VERSION.SDK_INT >= 23 && !Settings.canDrawOverlays(getActivity())) {
                                getActivity().startActivityForResult(new Intent("android.settings.action.MANAGE_OVERLAY_PERMISSION", Uri.parse("package:" + getActivity().getPackageName())), 123);
                                Process.killProcess(Process.myPid());
                            } else if (!isServiceRunning()) {
                                gAuth.Setup(getActivity().getApplicationContext(), Utils.fromBase64(jSONObject.get("Loader").toString()));
                                ((gactivity) this.weakActivity.get()).addInfoAuth("Conectado, Bem-vindo - divirta-se!", "#32852C");
                                final Intent intent = new Intent(getActivity(), MainActivity.class);
                                intent.putExtra("EXPIRY", jSONObject.get("SubscriptionLeft").toString());
                                intent.putExtra("CURRVER", jSONObject.get("CurrVersion").toString());
                                intent.putExtra("DOWNLK", jSONObject.get("DownloadURL").toString());
                                intent.putExtra("NOTEVER", jSONObject.get("NoteUpdate").toString());
                                with.write(this.expire, jSONObject.get("SubscriptionLeft").toString());
                                new Handler().postDelayed(new Runnable() {
                                    public void run() {
                                        Auth.this.getActivity().startActivity(intent);
                                        Auth.this.getActivity().finish();
                                    }
                                }, 900);
                            } else {
                                Toast.makeText(getActivity(), "Você já está conectado!", Toast.LENGTH_SHORT).show();
                                new Handler().postDelayed(new Runnable() {
                                    public void run() {
                                        ((gactivity) Auth.this.weakActivity.get()).addInfoAuth("Force a parada do FreeFire", "#FF0000");
                                    }
                                }, 2000);
                            }
                        }
                    } else if (jSONObject.get("MessageString").toString().equals("Usuário incorreto.")) {
                        ((gactivity) this.weakActivity.get()).addInfoAuth(jSONObject.get("MessageString").toString(), "#FF0000");
                    } else if (jSONObject.get("MessageString").toString().equals("Senha incorreta.")) {
                        ((gactivity) this.weakActivity.get()).addInfoAuth(jSONObject.get("MessageString").toString(), "#FF0000");
                    } else if (jSONObject.get("MessageString").toString().equals("Conta Banida entre contato com o Support")) {
                        ((gactivity) this.weakActivity.get()).addInfoAuth(jSONObject.get("MessageString").toString(), "#FF0000");
                    } else if (jSONObject.get("MessageString").toString().equals("Dispositivo banido permanentemente")) {
                        ((gactivity) this.weakActivity.get()).addInfoAuth("Dispositivo banido permanentemente!!", "#FF0000");
                    } else if (jSONObject.get("MessageString").toString().equals("Dispositivo não permitido.")) {
                        ((gactivity) this.weakActivity.get()).addInfoAuth("Dispositivo não permitido", "#FF0000");
                    } else if (jSONObject.get("MessageString").toString().equals("Conta Expirada entre contato com o seu vendedor")) {
                        ((gactivity) this.weakActivity.get()).addInfoAuth("Conta Expirada entre contato com o seu vendedor", "#ffa500");
                    } else if (jSONObject.get("MessageString").toString().equals("Servidor em manutenção!")) {
                        ((gactivity) this.weakActivity.get()).addInfoAuth("Servidor em manutenção!", "#ffa500");
                    } else if (jSONObject.get("MessageString").toString().equals("Servidor Offline!")) {
                        ((gactivity) this.weakActivity.get()).addInfoAuth("Servidor Offline no momento.", "#FF0000");
                    } else {
                        ((gactivity) this.weakActivity.get()).addInfoAuth("Você está usando uma versão desatualizada!", "#FF0000");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(activity, e.toString(), Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    public void onPreExecute() {
        if (getActivity() != null && getDialog() != null) {
            getDialog().setMessage("Verificando sua conexão..");
            getDialog().show();
        }
    }
}
