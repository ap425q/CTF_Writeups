package androidx.core.util;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m20d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0006\n\u0002\u0010\u0007\n\u0002\u0010\n\n\u0002\u0010\u000e\n\u0000\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0087\b\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0003H\u0087\b\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0004H\u0087\b\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0005H\u0087\b¨\u0006\u0006"}, m19d2 = {"toHalf", "Landroid/util/Half;", "", "", "", "", "core-ktx_release"}, m18k = 2, m17mv = {1, 5, 1}, m15xi = 48)
/* renamed from: androidx.core.util.HalfKt */
/* loaded from: classes.dex */
public final class Half {
    public static final android.util.Half toHalf(short $this$toHalf) {
        android.util.Half valueOf = android.util.Half.valueOf($this$toHalf);
        Intrinsics.checkNotNullExpressionValue(valueOf, "valueOf(this)");
        return valueOf;
    }

    public static final android.util.Half toHalf(float $this$toHalf) {
        android.util.Half valueOf = android.util.Half.valueOf($this$toHalf);
        Intrinsics.checkNotNullExpressionValue(valueOf, "valueOf(this)");
        return valueOf;
    }

    public static final android.util.Half toHalf(double $this$toHalf) {
        float $this$toHalf$iv = (float) $this$toHalf;
        android.util.Half valueOf = android.util.Half.valueOf($this$toHalf$iv);
        Intrinsics.checkNotNullExpressionValue(valueOf, "valueOf(this)");
        return valueOf;
    }

    public static final android.util.Half toHalf(String $this$toHalf) {
        Intrinsics.checkNotNullParameter($this$toHalf, "<this>");
        android.util.Half valueOf = android.util.Half.valueOf($this$toHalf);
        Intrinsics.checkNotNullExpressionValue(valueOf, "valueOf(this)");
        return valueOf;
    }
}