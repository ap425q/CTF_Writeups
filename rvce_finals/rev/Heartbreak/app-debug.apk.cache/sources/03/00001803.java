package kotlinx.coroutines.flow;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Emitters.kt */
@Metadata(m27k = 3, m26mv = {1, 6, 0}, m24xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(m14c = "kotlinx.coroutines.flow.FlowKt__EmittersKt", m13f = "Emitters.kt", m12i = {0}, m11l = {216}, m10m = "invokeSafely$FlowKt__EmittersKt", m9n = {"cause"}, m8s = {"L$0"})
/* loaded from: classes.dex */
public final class FlowKt__EmittersKt$invokeSafely$1<T> extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;

    /* JADX INFO: Access modifiers changed from: package-private */
    public FlowKt__EmittersKt$invokeSafely$1(Continuation<? super FlowKt__EmittersKt$invokeSafely$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object invokeSafely$FlowKt__EmittersKt;
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        invokeSafely$FlowKt__EmittersKt = Emitters.invokeSafely$FlowKt__EmittersKt(null, null, null, this);
        return invokeSafely$FlowKt__EmittersKt;
    }
}