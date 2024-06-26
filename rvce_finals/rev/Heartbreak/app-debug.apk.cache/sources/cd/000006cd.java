package androidx.core.p003os;

import android.os.Process;
import android.os.UserHandle;
import java.lang.reflect.Method;

/* renamed from: androidx.core.os.ProcessCompat */
/* loaded from: classes.dex */
public final class ProcessCompat {
    private ProcessCompat() {
    }

    public static boolean isApplicationUid(int uid) {
        return Api24Impl.isApplicationUid(uid);
    }

    /* renamed from: androidx.core.os.ProcessCompat$Api24Impl */
    /* loaded from: classes.dex */
    static class Api24Impl {
        private Api24Impl() {
        }

        static boolean isApplicationUid(int uid) {
            return Process.isApplicationUid(uid);
        }
    }

    /* renamed from: androidx.core.os.ProcessCompat$Api19Impl */
    /* loaded from: classes.dex */
    static class Api19Impl {
        private static Method sMethodUserHandleIsAppMethod;
        private static boolean sResolved;
        private static final Object sResolvedLock = new Object();

        private Api19Impl() {
        }

        static boolean isApplicationUid(int uid) {
            try {
                synchronized (sResolvedLock) {
                    if (!sResolved) {
                        sResolved = true;
                        sMethodUserHandleIsAppMethod = UserHandle.class.getDeclaredMethod("isApp", Integer.TYPE);
                    }
                }
                if (sMethodUserHandleIsAppMethod != null) {
                    Boolean result = (Boolean) sMethodUserHandleIsAppMethod.invoke(null, Integer.valueOf(uid));
                    if (result == null) {
                        throw new NullPointerException();
                    }
                    return result.booleanValue();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return true;
        }
    }
}