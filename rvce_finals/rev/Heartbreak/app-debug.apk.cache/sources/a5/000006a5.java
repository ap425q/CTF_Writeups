package androidx.core.p003os;

import android.util.Size;
import android.util.SizeF;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;

@Metadata(m29d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÃ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0007J\"\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\fH\u0007¨\u0006\r"}, m28d2 = {"Landroidx/core/os/BundleApi21ImplKt;", "", "()V", "putSize", "", "bundle", "Landroid/os/Bundle;", "key", "", "value", "Landroid/util/Size;", "putSizeF", "Landroid/util/SizeF;", "core-ktx_release"}, m27k = 1, m26mv = {1, 8, 0}, m24xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* renamed from: androidx.core.os.BundleApi21ImplKt */
/* loaded from: classes.dex */
final class Bundle {
    public static final Bundle INSTANCE = new Bundle();

    private Bundle() {
    }

    @JvmStatic
    public static final void putSize(android.os.Bundle bundle, String key, Size value) {
        bundle.putSize(key, value);
    }

    @JvmStatic
    public static final void putSizeF(android.os.Bundle bundle, String key, SizeF value) {
        bundle.putSizeF(key, value);
    }
}