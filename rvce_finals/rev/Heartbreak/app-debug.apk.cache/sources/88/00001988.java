package kotlinx.coroutines.selects;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: WhileSelect.kt */
@Metadata(m27k = 3, m26mv = {1, 6, 0}, m24xi = 176)
@DebugMetadata(m14c = "kotlinx.coroutines.selects.WhileSelectKt", m13f = "WhileSelect.kt", m12i = {0}, m11l = {37}, m10m = "whileSelect", m9n = {"builder"}, m8s = {"L$0"})
/* loaded from: classes.dex */
public final class WhileSelectKt$whileSelect$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;

    /* JADX INFO: Access modifiers changed from: package-private */
    public WhileSelectKt$whileSelect$1(Continuation<? super WhileSelectKt$whileSelect$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return WhileSelect.whileSelect(null, this);
    }
}