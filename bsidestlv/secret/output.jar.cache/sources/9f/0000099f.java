package androidx.startup;

import android.util.Log;

/* loaded from: output.jar:androidx/startup/StartupLogger.class */
public final class StartupLogger {
    static final boolean DEBUG = false;
    private static final String TAG = "StartupLogger";

    private StartupLogger() {
    }

    public static void e(String str, Throwable th) {
        Log.e(TAG, str, th);
    }

    public static void i(String str) {
        Log.i(TAG, str);
    }
}