package androidx.core.database;

import android.database.CursorWindow;
import android.os.Build;

/* loaded from: output.jar:androidx/core/database/CursorWindowCompat.class */
public final class CursorWindowCompat {
    private CursorWindowCompat() {
    }

    public static CursorWindow create(String str, long j) {
        return Build.VERSION.SDK_INT >= 28 ? new CursorWindow(str, j) : new CursorWindow(str);
    }
}