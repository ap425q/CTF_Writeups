package kotlin;

import androidx.constraintlayout.widget.ConstraintLayout;

@Metadata(m29d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\b\n\u0002\u0010\f\n\u0002\b\u0006\u001a\u0011\u0010\u0007\u001a\u00020\u00022\u0006\u0010\u0000\u001a\u00020\u0001H\u0087\b\"\u001f\u0010\u0000\u001a\u00020\u0001*\u00020\u00028Æ\u0002X\u0087\u0004¢\u0006\f\u0012\u0004\b\u0003\u0010\u0004\u001a\u0004\b\u0005\u0010\u0006¨\u0006\b"}, m28d2 = {"code", "", "", "getCode$annotations", "(C)V", "getCode", "(C)I", "Char", "kotlin-stdlib"}, m27k = 2, m26mv = {1, 8, 0}, m24xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* renamed from: kotlin.CharCodeKt */
/* loaded from: classes.dex */
public final class CharCode {
    public static /* synthetic */ void getCode$annotations(char c) {
    }

    private static final char Char(int code) {
        if (code < 0 || code > 65535) {
            throw new IllegalArgumentException("Invalid Char code: " + code);
        }
        return (char) code;
    }

    private static final int getCode(char $this$code) {
        return $this$code;
    }
}