package androidx.core.graphics.drawable;

import android.graphics.Bitmap;
import android.net.Uri;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;

@Metadata(m29d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0000\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0087\b\u001a\r\u0010\u0003\u001a\u00020\u0001*\u00020\u0002H\u0087\b\u001a\r\u0010\u0003\u001a\u00020\u0001*\u00020\u0004H\u0087\b\u001a\r\u0010\u0003\u001a\u00020\u0001*\u00020\u0005H\u0087\b¨\u0006\u0006"}, m28d2 = {"toAdaptiveIcon", "Landroid/graphics/drawable/Icon;", "Landroid/graphics/Bitmap;", "toIcon", "Landroid/net/Uri;", "", "core-ktx_release"}, m27k = 2, m26mv = {1, 8, 0}, m24xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* renamed from: androidx.core.graphics.drawable.IconKt */
/* loaded from: classes.dex */
public final class Icon {
    public static final android.graphics.drawable.Icon toAdaptiveIcon(Bitmap $this$toAdaptiveIcon) {
        return android.graphics.drawable.Icon.createWithAdaptiveBitmap($this$toAdaptiveIcon);
    }

    public static final android.graphics.drawable.Icon toIcon(Bitmap $this$toIcon) {
        return android.graphics.drawable.Icon.createWithBitmap($this$toIcon);
    }

    public static final android.graphics.drawable.Icon toIcon(Uri $this$toIcon) {
        return android.graphics.drawable.Icon.createWithContentUri($this$toIcon);
    }

    public static final android.graphics.drawable.Icon toIcon(byte[] $this$toIcon) {
        return android.graphics.drawable.Icon.createWithData($this$toIcon, 0, $this$toIcon.length);
    }
}