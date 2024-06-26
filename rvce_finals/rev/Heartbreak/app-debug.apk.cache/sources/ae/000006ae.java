package androidx.core.p003os;

import android.os.Environment;
import java.io.File;

/* renamed from: androidx.core.os.EnvironmentCompat */
/* loaded from: classes.dex */
public final class EnvironmentCompat {
    @Deprecated
    public static final String MEDIA_UNKNOWN = "unknown";

    public static String getStorageState(File path) {
        return Api21Impl.getExternalStorageState(path);
    }

    private EnvironmentCompat() {
    }

    /* renamed from: androidx.core.os.EnvironmentCompat$Api21Impl */
    /* loaded from: classes.dex */
    static class Api21Impl {
        private Api21Impl() {
        }

        static String getExternalStorageState(File path) {
            return Environment.getExternalStorageState(path);
        }
    }
}