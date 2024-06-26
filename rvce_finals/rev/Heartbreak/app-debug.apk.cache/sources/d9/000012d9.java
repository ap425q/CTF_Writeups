package kotlin;

/* compiled from: D8$$SyntheticClass */
/* loaded from: classes.dex */
public final /* synthetic */ class UByte$$ExternalSyntheticBackport3 {
    /* renamed from: m */
    public static /* synthetic */ long m17m(long j, long j2) {
        if (j2 < 0) {
            return (j ^ Long.MIN_VALUE) < (j2 ^ Long.MIN_VALUE) ? 0L : 1L;
        } else if (j >= 0) {
            return j / j2;
        } else {
            long j3 = ((j >>> 1) / j2) << 1;
            return j3 + (((j - (j3 * j2)) ^ Long.MIN_VALUE) < (j2 ^ Long.MIN_VALUE) ? 0 : 1);
        }
    }
}