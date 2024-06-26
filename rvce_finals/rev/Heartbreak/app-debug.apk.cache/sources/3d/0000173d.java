package kotlinx.coroutines.channels;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Deprecated.kt */
@Metadata(m27k = 3, m26mv = {1, 6, 0}, m24xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(m14c = "kotlinx.coroutines.channels.ChannelsKt__DeprecatedKt", m13f = "Deprecated.kt", m12i = {0}, m11l = {404}, m10m = "any", m9n = {"$this$consume$iv"}, m8s = {"L$0"})
/* loaded from: classes.dex */
public final class ChannelsKt__DeprecatedKt$any$1<E> extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ChannelsKt__DeprecatedKt$any$1(Continuation<? super ChannelsKt__DeprecatedKt$any$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object any;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        any = Deprecated.any(null, this);
        return any;
    }
}