package androidx.core.graphics;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;

@Metadata(m29d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0017\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H\u0086\b¨\u0006\u0005"}, m28d2 = {"setBlendMode", "", "Landroid/graphics/Paint;", "blendModeCompat", "Landroidx/core/graphics/BlendModeCompat;", "core-ktx_release"}, m27k = 2, m26mv = {1, 8, 0}, m24xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* renamed from: androidx.core.graphics.PaintKt */
/* loaded from: classes.dex */
public final class Paint {
    public static final boolean setBlendMode(android.graphics.Paint $this$setBlendMode, BlendModeCompat blendModeCompat) {
        return PaintCompat.setBlendMode($this$setBlendMode, blendModeCompat);
    }
}