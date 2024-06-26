package androidx.core.text;

import android.text.TextUtils;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;

@Metadata(m29d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\r\u0010\u0000\u001a\u00020\u0001*\u00020\u0001H\u0086\b¨\u0006\u0002"}, m28d2 = {"htmlEncode", "", "core-ktx_release"}, m27k = 2, m26mv = {1, 8, 0}, m24xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* renamed from: androidx.core.text.StringKt */
/* loaded from: classes.dex */
public final class String {
    public static final java.lang.String htmlEncode(java.lang.String $this$htmlEncode) {
        return TextUtils.htmlEncode($this$htmlEncode);
    }
}