package kotlinx.coroutines.flow;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlinx.coroutines.flow.FlowKt__EmittersKt$transform$1;

/* compiled from: Emitters.kt */
@Metadata(m27k = 3, m26mv = {1, 6, 0}, m24xi = 176)
@DebugMetadata(m14c = "kotlinx.coroutines.flow.FlowKt__EmittersKt$transform$1$1", m13f = "Emitters.kt", m12i = {}, m11l = {42}, m10m = "emit", m9n = {}, m8s = {})
/* loaded from: classes.dex */
public final class FlowKt__EmittersKt$transform$1$1$emit$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ FlowKt__EmittersKt$transform$1.C09111<T> this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public FlowKt__EmittersKt$transform$1$1$emit$1(FlowKt__EmittersKt$transform$1.C09111<? super T> c09111, Continuation<? super FlowKt__EmittersKt$transform$1$1$emit$1> continuation) {
        super(continuation);
        this.this$0 = c09111;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.emit(null, this);
    }
}