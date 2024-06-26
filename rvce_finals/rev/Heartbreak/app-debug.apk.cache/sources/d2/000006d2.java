package androidx.core.p003os;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.functions.Functions;
import kotlin.jvm.internal.InlineMarker;

@Metadata(m29d1 = {"\u0000\u0012\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a*\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0005H\u0087\b¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, m28d2 = {"trace", "T", "sectionName", "", "block", "Lkotlin/Function0;", "(Ljava/lang/String;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "core-ktx_release"}, m27k = 2, m26mv = {1, 8, 0}, m24xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* renamed from: androidx.core.os.TraceKt */
/* loaded from: classes.dex */
public final class Trace {
    @Deprecated(message = "Use androidx.tracing.Trace instead", replaceWith = @ReplaceWith(expression = "trace(sectionName, block)", imports = {"androidx.tracing.trace"}))
    public static final <T> T trace(String sectionName, Functions<? extends T> functions) {
        android.os.Trace.beginSection(sectionName);
        try {
            return functions.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            android.os.Trace.endSection();
            InlineMarker.finallyEnd(1);
        }
    }
}