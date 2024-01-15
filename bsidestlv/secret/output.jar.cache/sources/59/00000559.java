package androidx.core.os;

import android.os.Environment;
import java.io.File;

/* loaded from: output.jar:androidx/core/os/EnvironmentCompat.class */
public final class EnvironmentCompat {
    public static final String MEDIA_UNKNOWN = "unknown";
    private static final String TAG = "EnvironmentCompat";

    private EnvironmentCompat() {
    }

    public static String getStorageState(File file) {
        return Environment.getExternalStorageState(file);
    }
}