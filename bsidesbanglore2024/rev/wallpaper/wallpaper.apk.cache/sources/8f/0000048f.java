package androidx.core.graphics.drawable;

import android.graphics.Color;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m20d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0087\b\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0003H\u0086\b¨\u0006\u0004"}, m19d2 = {"toDrawable", "Landroid/graphics/drawable/ColorDrawable;", "Landroid/graphics/Color;", "", "core-ktx_release"}, m18k = 2, m17mv = {1, 5, 1}, m15xi = 48)
/* renamed from: androidx.core.graphics.drawable.ColorDrawableKt */
/* loaded from: classes.dex */
public final class ColorDrawable {
    public static final android.graphics.drawable.ColorDrawable toDrawable(int $this$toDrawable) {
        return new android.graphics.drawable.ColorDrawable($this$toDrawable);
    }

    public static final android.graphics.drawable.ColorDrawable toDrawable(Color $this$toDrawable) {
        Intrinsics.checkNotNullParameter($this$toDrawable, "<this>");
        return new android.graphics.drawable.ColorDrawable($this$toDrawable.toArgb());
    }
}