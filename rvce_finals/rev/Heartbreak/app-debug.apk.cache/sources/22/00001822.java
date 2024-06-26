package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* compiled from: Limit.kt */
@Metadata(m27k = 3, m26mv = {1, 6, 0}, m24xi = 176)
@DebugMetadata(m14c = "kotlinx.coroutines.flow.FlowKt__LimitKt$collectWhile$collector$1", m13f = "Limit.kt", m12i = {0}, m11l = {130}, m10m = "emit", m9n = {"this"}, m8s = {"L$0"})
/* loaded from: classes.dex */
public final class FlowKt__LimitKt$collectWhile$collector$1$emit$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ FlowKt__LimitKt$collectWhile$collector$1 this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FlowKt__LimitKt$collectWhile$collector$1$emit$1(FlowKt__LimitKt$collectWhile$collector$1 flowKt__LimitKt$collectWhile$collector$1, Continuation<? super FlowKt__LimitKt$collectWhile$collector$1$emit$1> continuation) {
        super(continuation);
        this.this$0 = flowKt__LimitKt$collectWhile$collector$1;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.emit(null, this);
    }
}