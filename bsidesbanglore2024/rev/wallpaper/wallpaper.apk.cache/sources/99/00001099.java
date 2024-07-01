package kotlin.text;

import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m20d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000e\n\u0002\u0010\f\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0000¨\u0006\u0003"}, m19d2 = {"titlecaseImpl", "", "", "kotlin-stdlib"}, m18k = 2, m17mv = {1, 5, 1})
/* renamed from: kotlin.text._OneToManyTitlecaseMappingsKt */
/* loaded from: classes.dex */
public final class _OneToManyTitlecaseMappings {
    public static final String titlecaseImpl(char $this$titlecaseImpl) {
        String valueOf = String.valueOf($this$titlecaseImpl);
        if (valueOf == null) {
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        String uppercase = valueOf.toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(uppercase, "(this as java.lang.Strin….toUpperCase(Locale.ROOT)");
        if (uppercase.length() > 1) {
            if ($this$titlecaseImpl == 329) {
                return uppercase;
            }
            char charAt = uppercase.charAt(0);
            if (uppercase != null) {
                String substring = uppercase.substring(1);
                Intrinsics.checkNotNullExpressionValue(substring, "(this as java.lang.String).substring(startIndex)");
                if (substring != null) {
                    String lowerCase = substring.toLowerCase(Locale.ROOT);
                    Intrinsics.checkNotNullExpressionValue(lowerCase, "(this as java.lang.Strin….toLowerCase(Locale.ROOT)");
                    return String.valueOf(charAt) + lowerCase;
                }
                throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
            }
            throw new NullPointerException("null cannot be cast to non-null type java.lang.String");
        }
        return String.valueOf(Character.toTitleCase($this$titlecaseImpl));
    }
}