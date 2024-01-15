package androidx.core.provider;

import android.os.Handler;
import android.os.Looper;

/* loaded from: output.jar:androidx/core/provider/CalleeHandler.class */
class CalleeHandler {
    private CalleeHandler() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Handler create() {
        return Looper.myLooper() == null ? new Handler(Looper.getMainLooper()) : new Handler();
    }
}