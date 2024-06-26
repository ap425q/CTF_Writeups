package androidx.core.p003os;

import android.os.BadParcelableException;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.SparseArray;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: androidx.core.os.ParcelCompat */
/* loaded from: classes.dex */
public final class ParcelCompat {
    public static boolean readBoolean(Parcel in) {
        return in.readInt() != 0;
    }

    public static void writeBoolean(Parcel out, boolean value) {
        out.writeInt(value ? 1 : 0);
    }

    public static <T> void readList(Parcel in, List<? super T> outVal, ClassLoader loader, Class<T> clazz) {
        if (Build.VERSION.SDK_INT >= 34) {
            Api33Impl.readList(in, outVal, loader, clazz);
        } else {
            in.readList(outVal, loader);
        }
    }

    public static <T> ArrayList<T> readArrayList(Parcel in, ClassLoader loader, Class<? extends T> clazz) {
        if (Build.VERSION.SDK_INT >= 34) {
            return Api33Impl.readArrayList(in, loader, clazz);
        }
        return in.readArrayList(loader);
    }

    public static <T> Object[] readArray(Parcel in, ClassLoader loader, Class<T> clazz) {
        if (Build.VERSION.SDK_INT >= 34) {
            return Api33Impl.readArray(in, loader, clazz);
        }
        return in.readArray(loader);
    }

    public static <T> SparseArray<T> readSparseArray(Parcel in, ClassLoader loader, Class<? extends T> clazz) {
        if (Build.VERSION.SDK_INT >= 34) {
            return Api33Impl.readSparseArray(in, loader, clazz);
        }
        return in.readSparseArray(loader);
    }

    public static <K, V> void readMap(Parcel in, Map<? super K, ? super V> outVal, ClassLoader loader, Class<K> clazzKey, Class<V> clazzValue) {
        if (Build.VERSION.SDK_INT >= 34) {
            Api33Impl.readMap(in, outVal, loader, clazzKey, clazzValue);
        } else {
            in.readMap(outVal, loader);
        }
    }

    public static <K, V> HashMap<K, V> readHashMap(Parcel in, ClassLoader loader, Class<? extends K> clazzKey, Class<? extends V> clazzValue) {
        if (Build.VERSION.SDK_INT >= 34) {
            return Api33Impl.readHashMap(in, loader, clazzKey, clazzValue);
        }
        return in.readHashMap(loader);
    }

    public static <T extends Parcelable> T readParcelable(Parcel in, ClassLoader loader, Class<T> clazz) {
        if (Build.VERSION.SDK_INT >= 34) {
            return (T) Api33Impl.readParcelable(in, loader, clazz);
        }
        T parcelable = (T) in.readParcelable(loader);
        if (parcelable != null && !clazz.isInstance(parcelable)) {
            throw new BadParcelableException("Parcelable " + parcelable.getClass() + " is not a subclass of required class " + clazz.getName() + " provided in the parameter");
        }
        return parcelable;
    }

    public static <T> Parcelable.Creator<T> readParcelableCreator(Parcel in, ClassLoader loader, Class<T> clazz) {
        if (Build.VERSION.SDK_INT >= 34) {
            return Api33Impl.readParcelableCreator(in, loader, clazz);
        }
        return (Parcelable.Creator<T>) Api30Impl.readParcelableCreator(in, loader);
    }

    @Deprecated
    public static <T> T[] readParcelableArray(Parcel in, ClassLoader loader, Class<T> clazz) {
        if (Build.VERSION.SDK_INT >= 34) {
            return (T[]) Api33Impl.readParcelableArray(in, loader, clazz);
        }
        Parcelable[] parcelables = in.readParcelableArray(loader);
        if (clazz.isAssignableFrom(Parcelable.class)) {
            return (T[]) parcelables;
        }
        T[] typedParcelables = (T[]) ((Object[]) Array.newInstance((Class<?>) clazz, parcelables.length));
        for (int i = 0; i < parcelables.length; i++) {
            try {
                typedParcelables[i] = clazz.cast(parcelables[i]);
            } catch (ClassCastException e) {
                throw new BadParcelableException("Parcelable at index " + i + " is not a subclass of required class " + clazz.getName() + " provided in the parameter");
            }
        }
        return typedParcelables;
    }

    public static <T> Parcelable[] readParcelableArrayTyped(Parcel in, ClassLoader loader, Class<T> clazz) {
        if (Build.VERSION.SDK_INT >= 34) {
            return (Parcelable[]) Api33Impl.readParcelableArray(in, loader, clazz);
        }
        return in.readParcelableArray(loader);
    }

    public static <T> List<T> readParcelableList(Parcel in, List<T> list, ClassLoader cl, Class<T> clazz) {
        if (Build.VERSION.SDK_INT >= 34) {
            return Api33Impl.readParcelableList(in, list, cl, clazz);
        }
        return Api29Impl.readParcelableList(in, list, cl);
    }

    public static <T extends Serializable> T readSerializable(Parcel in, ClassLoader loader, Class<T> clazz) {
        if (Build.VERSION.SDK_INT >= 33) {
            return (T) Api33Impl.readSerializable(in, loader, clazz);
        }
        return (T) in.readSerializable();
    }

    private ParcelCompat() {
    }

    /* renamed from: androidx.core.os.ParcelCompat$Api29Impl */
    /* loaded from: classes.dex */
    static class Api29Impl {
        private Api29Impl() {
        }

        static <T extends Parcelable> List<T> readParcelableList(Parcel in, List<T> list, ClassLoader cl) {
            return in.readParcelableList(list, cl);
        }
    }

    /* renamed from: androidx.core.os.ParcelCompat$Api30Impl */
    /* loaded from: classes.dex */
    static class Api30Impl {
        private Api30Impl() {
        }

        static Parcelable.Creator<?> readParcelableCreator(Parcel in, ClassLoader loader) {
            return in.readParcelableCreator(loader);
        }
    }

    /* renamed from: androidx.core.os.ParcelCompat$Api33Impl */
    /* loaded from: classes.dex */
    static class Api33Impl {
        private Api33Impl() {
        }

        static <T extends Serializable> T readSerializable(Parcel in, ClassLoader loader, Class<T> clazz) {
            return (T) in.readSerializable(loader, clazz);
        }

        static <T extends Parcelable> T readParcelable(Parcel in, ClassLoader loader, Class<T> clazz) {
            return (T) in.readParcelable(loader, clazz);
        }

        static <T> Parcelable.Creator<T> readParcelableCreator(Parcel in, ClassLoader loader, Class<T> clazz) {
            return in.readParcelableCreator(loader, clazz);
        }

        static <T> T[] readParcelableArray(Parcel in, ClassLoader loader, Class<T> clazz) {
            return (T[]) in.readParcelableArray(loader, clazz);
        }

        static <T> List<T> readParcelableList(Parcel in, List<T> list, ClassLoader cl, Class<T> clazz) {
            return in.readParcelableList(list, cl, clazz);
        }

        static <T> void readList(Parcel in, List<? super T> outVal, ClassLoader loader, Class<T> clazz) {
            in.readList(outVal, loader, clazz);
        }

        static <T> ArrayList<T> readArrayList(Parcel in, ClassLoader loader, Class<? extends T> clazz) {
            return in.readArrayList(loader, clazz);
        }

        static <T> T[] readArray(Parcel in, ClassLoader loader, Class<T> clazz) {
            return (T[]) in.readArray(loader, clazz);
        }

        static <T> SparseArray<T> readSparseArray(Parcel in, ClassLoader loader, Class<? extends T> clazz) {
            return in.readSparseArray(loader, clazz);
        }

        static <K, V> void readMap(Parcel in, Map<? super K, ? super V> outVal, ClassLoader loader, Class<K> clazzKey, Class<V> clazzValue) {
            in.readMap(outVal, loader, clazzKey, clazzValue);
        }

        static <V, K> HashMap<K, V> readHashMap(Parcel in, ClassLoader loader, Class<? extends K> clazzKey, Class<? extends V> clazzValue) {
            return in.readHashMap(loader, clazzKey, clazzValue);
        }
    }
}