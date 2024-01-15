package androidx.core.os;

import android.os.Parcel;

/* loaded from: output.jar:androidx/core/os/ParcelCompat.class */
public final class ParcelCompat {
    private ParcelCompat() {
    }

    public static boolean readBoolean(Parcel parcel) {
        return parcel.readInt() != 0;
    }

    public static void writeBoolean(Parcel parcel, boolean z) {
        parcel.writeInt(z ? 1 : 0);
    }
}