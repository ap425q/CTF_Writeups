package androidx.core.text;

import android.text.TextUtils;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;

@Metadata(m29d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0000\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0086\b\u001a\r\u0010\u0003\u001a\u00020\u0004*\u00020\u0002H\u0086\b¨\u0006\u0005"}, m28d2 = {"isDigitsOnly", "", "", "trimmedLength", "", "core-ktx_release"}, m27k = 2, m26mv = {1, 8, 0}, m24xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* renamed from: androidx.core.text.CharSequenceKt */
/* loaded from: classes.dex */
public final class CharSequence {
    public static final boolean isDigitsOnly(java.lang.CharSequence $this$isDigitsOnly) {
        return TextUtils.isDigitsOnly($this$isDigitsOnly);
    }

    public static final int trimmedLength(java.lang.CharSequence $this$trimmedLength) {
        return TextUtils.getTrimmedLength($this$trimmedLength);
    }
}