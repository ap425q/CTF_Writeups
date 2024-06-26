package kotlin;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes.dex */
public final /* synthetic */ class UByte$$ExternalSyntheticBackport2 {
    /* renamed from: m */
    public static /* synthetic */ long m18m(long j, long j2) {
        if (j2 < 0) {
            return (j ^ Long.MIN_VALUE) < (j2 ^ Long.MIN_VALUE) ? j : j - j2;
        } else if (j >= 0) {
            return j % j2;
        } else {
            long j3 = j - ((((j >>> 1) / j2) << 1) * j2);
            if ((j3 ^ Long.MIN_VALUE) < (j2 ^ Long.MIN_VALUE)) {
                j2 = 0;
            }
            return j3 - j2;
        }
    }
}