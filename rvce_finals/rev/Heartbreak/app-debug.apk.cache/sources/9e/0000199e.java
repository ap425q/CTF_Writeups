package kotlinx.coroutines.sync;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Semaphore.kt */
@Metadata(m27k = 3, m26mv = {1, 6, 0}, m24xi = 176)
@DebugMetadata(m14c = "kotlinx.coroutines.sync.SemaphoreKt", m13f = "Semaphore.kt", m12i = {0, 0}, m11l = {85}, m10m = "withPermit", m9n = {"$this$withPermit", "action"}, m8s = {"L$0", "L$1"})
/* loaded from: classes.dex */
public final class SemaphoreKt$withPermit$1<T> extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;

    /* JADX INFO: Access modifiers changed from: package-private */
    public SemaphoreKt$withPermit$1(Continuation<? super SemaphoreKt$withPermit$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return SemaphoreKt.withPermit(null, null, this);
    }
}