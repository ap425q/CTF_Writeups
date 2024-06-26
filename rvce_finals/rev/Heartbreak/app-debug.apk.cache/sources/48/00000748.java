package androidx.core.util;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;

@Metadata(m29d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001c\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u0003¨\u0006\u0004"}, m28d2 = {"asAndroidXConsumer", "Landroidx/core/util/Consumer;", "T", "Lkotlin/coroutines/Continuation;", "core-ktx_release"}, m27k = 2, m26mv = {1, 8, 0}, m24xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* renamed from: androidx.core.util.AndroidXConsumerKt */
/* loaded from: classes.dex */
public final class AndroidXConsumer {
    public static final <T> Consumer<T> asAndroidXConsumer(Continuation<? super T> continuation) {
        return new AndroidXContinuationConsumer(continuation);
    }
}