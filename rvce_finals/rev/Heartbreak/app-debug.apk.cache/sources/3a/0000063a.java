package androidx.core.graphics.drawable;

import android.content.res.Resources;
import android.graphics.Bitmap;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;

@Metadata(m29d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0086\b¨\u0006\u0005"}, m28d2 = {"toDrawable", "Landroid/graphics/drawable/BitmapDrawable;", "Landroid/graphics/Bitmap;", "resources", "Landroid/content/res/Resources;", "core-ktx_release"}, m27k = 2, m26mv = {1, 8, 0}, m24xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* renamed from: androidx.core.graphics.drawable.BitmapDrawableKt */
/* loaded from: classes.dex */
public final class BitmapDrawable {
    public static final android.graphics.drawable.BitmapDrawable toDrawable(Bitmap $this$toDrawable, Resources resources) {
        return new android.graphics.drawable.BitmapDrawable(resources, $this$toDrawable);
    }
}