package kotlinx.coroutines.channels;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: AbstractChannel.kt */
@Metadata(m27k = 3, m26mv = {1, 6, 0}, m24xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(m14c = "kotlinx.coroutines.channels.AbstractChannel", m13f = "AbstractChannel.kt", m12i = {}, m11l = {633}, m10m = "receiveCatching-JP2dKIU", m9n = {}, m8s = {})
/* loaded from: classes.dex */
public final class AbstractChannel$receiveCatching$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ AbstractChannel<E> this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AbstractChannel$receiveCatching$1(AbstractChannel<E> abstractChannel, Continuation<? super AbstractChannel$receiveCatching$1> continuation) {
        super(continuation);
        this.this$0 = abstractChannel;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        Object mo1724receiveCatchingJP2dKIU = this.this$0.mo1724receiveCatchingJP2dKIU(this);
        return mo1724receiveCatchingJP2dKIU == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? mo1724receiveCatchingJP2dKIU : ChannelResult.m1731boximpl(mo1724receiveCatchingJP2dKIU);
    }
}