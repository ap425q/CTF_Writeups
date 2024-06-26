package kotlinx.coroutines.flow.internal;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.sync.Semaphore;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Merge.kt */
@Metadata(m29d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0004H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, m28d2 = {"<anonymous>", "", "T", "inner", "Lkotlinx/coroutines/flow/Flow;", "emit", "(Lkotlinx/coroutines/flow/Flow;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;"}, m27k = 3, m26mv = {1, 6, 0}, m24xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class ChannelFlowMerge$collectTo$2<T> implements FlowCollector {
    final /* synthetic */ SendingCollector<T> $collector;
    final /* synthetic */ Job $job;
    final /* synthetic */ ProducerScope<T> $scope;
    final /* synthetic */ Semaphore $semaphore;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Multi-variable type inference failed */
    public ChannelFlowMerge$collectTo$2(Job job, Semaphore semaphore, ProducerScope<? super T> producerScope, SendingCollector<T> sendingCollector) {
        this.$job = job;
        this.$semaphore = semaphore;
        this.$scope = producerScope;
        this.$collector = sendingCollector;
    }

    @Override // kotlinx.coroutines.flow.FlowCollector
    public /* bridge */ /* synthetic */ Object emit(Object value, Continuation $completion) {
        return emit((Flow) ((Flow) value), (Continuation<? super Unit>) $completion);
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0025  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x002d  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0039  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object emit(kotlinx.coroutines.flow.Flow<? extends T> r10, kotlin.coroutines.Continuation<? super kotlin.Unit> r11) {
        /*
            r9 = this;
            boolean r0 = r11 instanceof kotlinx.coroutines.flow.internal.ChannelFlowMerge$collectTo$2$emit$1
            if (r0 == 0) goto L14
            r0 = r11
            kotlinx.coroutines.flow.internal.ChannelFlowMerge$collectTo$2$emit$1 r0 = (kotlinx.coroutines.flow.internal.ChannelFlowMerge$collectTo$2$emit$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L14
            int r11 = r0.label
            int r11 = r11 - r2
            r0.label = r11
            goto L19
        L14:
            kotlinx.coroutines.flow.internal.ChannelFlowMerge$collectTo$2$emit$1 r0 = new kotlinx.coroutines.flow.internal.ChannelFlowMerge$collectTo$2$emit$1
            r0.<init>(r9, r11)
        L19:
            r11 = r0
            java.lang.Object r0 = r11.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r11.label
            switch(r2) {
                case 0: goto L39;
                case 1: goto L2d;
                default: goto L25;
            }
        L25:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L2d:
            java.lang.Object r10 = r11.L$1
            kotlinx.coroutines.flow.Flow r10 = (kotlinx.coroutines.flow.Flow) r10
            java.lang.Object r1 = r11.L$0
            kotlinx.coroutines.flow.internal.ChannelFlowMerge$collectTo$2 r1 = (kotlinx.coroutines.flow.internal.ChannelFlowMerge$collectTo$2) r1
            kotlin.ResultKt.throwOnFailure(r0)
            goto L55
        L39:
            kotlin.ResultKt.throwOnFailure(r0)
            r2 = r9
            kotlinx.coroutines.Job r3 = r2.$job
            if (r3 == 0) goto L44
            kotlinx.coroutines.JobKt.ensureActive(r3)
        L44:
            kotlinx.coroutines.sync.Semaphore r3 = r2.$semaphore
            r11.L$0 = r2
            r11.L$1 = r10
            r4 = 1
            r11.label = r4
            java.lang.Object r3 = r3.acquire(r11)
            if (r3 != r1) goto L54
            return r1
        L54:
            r1 = r2
        L55:
            kotlinx.coroutines.channels.ProducerScope<T> r2 = r1.$scope
            r3 = r2
            kotlinx.coroutines.CoroutineScope r3 = (kotlinx.coroutines.CoroutineScope) r3
            kotlinx.coroutines.flow.internal.ChannelFlowMerge$collectTo$2$1 r2 = new kotlinx.coroutines.flow.internal.ChannelFlowMerge$collectTo$2$1
            kotlinx.coroutines.flow.internal.SendingCollector<T> r4 = r1.$collector
            kotlinx.coroutines.sync.Semaphore r5 = r1.$semaphore
            r6 = 0
            r2.<init>(r10, r4, r5, r6)
            r6 = r2
            kotlin.jvm.functions.Function2 r6 = (kotlin.jvm.functions.Function2) r6
            r7 = 3
            r8 = 0
            r4 = 0
            r5 = 0
            kotlinx.coroutines.BuildersKt.launch$default(r3, r4, r5, r6, r7, r8)
            kotlin.Unit r2 = kotlin.Unit.INSTANCE
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.coroutines.flow.internal.ChannelFlowMerge$collectTo$2.emit(kotlinx.coroutines.flow.Flow, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* compiled from: Merge.kt */
    @Metadata(m29d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H\u008a@"}, m28d2 = {"<anonymous>", "", "T", "Lkotlinx/coroutines/CoroutineScope;"}, m27k = 3, m26mv = {1, 6, 0}, m24xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    @DebugMetadata(m14c = "kotlinx.coroutines.flow.internal.ChannelFlowMerge$collectTo$2$1", m13f = "Merge.kt", m12i = {}, m11l = {69}, m10m = "invokeSuspend", m9n = {}, m8s = {})
    /* renamed from: kotlinx.coroutines.flow.internal.ChannelFlowMerge$collectTo$2$1 */
    /* loaded from: classes.dex */
    public static final class C09701 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ SendingCollector<T> $collector;
        final /* synthetic */ Flow<T> $inner;
        final /* synthetic */ Semaphore $semaphore;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        /* JADX WARN: Multi-variable type inference failed */
        C09701(Flow<? extends T> flow, SendingCollector<T> sendingCollector, Semaphore semaphore, Continuation<? super C09701> continuation) {
            super(2, continuation);
            this.$inner = flow;
            this.$collector = sendingCollector;
            this.$semaphore = semaphore;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C09701(this.$inner, this.$collector, this.$semaphore, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C09701) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object $result) {
            Throwable th;
            C09701 c09701;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            switch (this.label) {
                case 0:
                    ResultKt.throwOnFailure($result);
                    try {
                        this.label = 1;
                        if (this.$inner.collect(this.$collector, this) == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                        c09701 = this;
                        c09701.$semaphore.release();
                        return Unit.INSTANCE;
                    } catch (Throwable th2) {
                        th = th2;
                        c09701 = this;
                        c09701.$semaphore.release();
                        throw th;
                    }
                case 1:
                    c09701 = this;
                    try {
                        ResultKt.throwOnFailure($result);
                        c09701.$semaphore.release();
                        return Unit.INSTANCE;
                    } catch (Throwable th3) {
                        th = th3;
                        c09701.$semaphore.release();
                        throw th;
                    }
                default:
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
        }
    }
}