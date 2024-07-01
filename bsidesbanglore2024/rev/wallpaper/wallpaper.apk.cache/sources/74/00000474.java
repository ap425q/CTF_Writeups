package androidx.core.graphics;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m20d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0017\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0086\b¨\u0006\u0005"}, m19d2 = {"setBlendMode", "", "Landroid/graphics/Paint;", "blendModeCompat", "Landroidx/core/graphics/BlendModeCompat;", "core-ktx_release"}, m18k = 2, m17mv = {1, 5, 1}, m15xi = 48)
/* renamed from: androidx.core.graphics.PaintKt */
/* loaded from: classes.dex */
public final class Paint {
    public static final boolean setBlendMode(android.graphics.Paint $this$setBlendMode, BlendModeCompat blendModeCompat) {
        Intrinsics.checkNotNullParameter($this$setBlendMode, "<this>");
        return PaintCompat.setBlendMode($this$setBlendMode, blendModeCompat);
    }
}