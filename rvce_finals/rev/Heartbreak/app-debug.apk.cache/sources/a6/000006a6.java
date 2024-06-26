package androidx.core.p003os;

import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.util.SparseArray;
import java.io.Serializable;
import java.util.ArrayList;

/* renamed from: androidx.core.os.BundleCompat */
/* loaded from: classes.dex */
public final class BundleCompat {
    private BundleCompat() {
    }

    public static <T> T getParcelable(Bundle in, String key, Class<T> clazz) {
        if (Build.VERSION.SDK_INT >= 34) {
            return (T) Api33Impl.getParcelable(in, key, clazz);
        }
        T parcelable = (T) in.getParcelable(key);
        if (clazz.isInstance(parcelable)) {
            return parcelable;
        }
        return null;
    }

    public static Parcelable[] getParcelableArray(Bundle in, String key, Class<? extends Parcelable> clazz) {
        if (Build.VERSION.SDK_INT >= 34) {
            return (Parcelable[]) Api33Impl.getParcelableArray(in, key, clazz);
        }
        return in.getParcelableArray(key);
    }

    public static <T> ArrayList<T> getParcelableArrayList(Bundle in, String key, Class<? extends T> clazz) {
        if (Build.VERSION.SDK_INT >= 34) {
            return Api33Impl.getParcelableArrayList(in, key, clazz);
        }
        return in.getParcelableArrayList(key);
    }

    public static <T> SparseArray<T> getSparseParcelableArray(Bundle in, String key, Class<? extends T> clazz) {
        if (Build.VERSION.SDK_INT >= 34) {
            return Api33Impl.getSparseParcelableArray(in, key, clazz);
        }
        return in.getSparseParcelableArray(key);
    }

    @Deprecated
    public static IBinder getBinder(Bundle bundle, String key) {
        return bundle.getBinder(key);
    }

    @Deprecated
    public static void putBinder(Bundle bundle, String key, IBinder binder) {
        bundle.putBinder(key, binder);
    }

    public static <T extends Serializable> T getSerializable(Bundle in, String key, Class<T> clazz) {
        if (Build.VERSION.SDK_INT >= 34) {
            return (T) Api33Impl.getSerializable(in, key, clazz);
        }
        T t = (T) in.getSerializable(key);
        if (clazz.isInstance(t)) {
            return t;
        }
        return null;
    }

    /* renamed from: androidx.core.os.BundleCompat$Api33Impl */
    /* loaded from: classes.dex */
    static class Api33Impl {
        private Api33Impl() {
        }

        static <T> T getParcelable(Bundle in, String key, Class<T> clazz) {
            return (T) in.getParcelable(key, clazz);
        }

        static <T> T[] getParcelableArray(Bundle in, String key, Class<T> clazz) {
            return (T[]) in.getParcelableArray(key, clazz);
        }

        static <T> ArrayList<T> getParcelableArrayList(Bundle in, String key, Class<? extends T> clazz) {
            return in.getParcelableArrayList(key, clazz);
        }

        static <T> SparseArray<T> getSparseParcelableArray(Bundle in, String key, Class<? extends T> clazz) {
            return in.getSparseParcelableArray(key, clazz);
        }

        static <T extends Serializable> T getSerializable(Bundle in, String key, Class<T> clazz) {
            return (T) in.getSerializable(key, clazz);
        }
    }
}