package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: Add missing generic type declarations: [R] */
/* compiled from: _Sequences.kt */
@Metadata(m20d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u0004H\u008a@¢\u0006\u0004\b\u0005\u0010\u0006"}, m19d2 = {"<anonymous>", "", "T", "R", "Lkotlin/sequences/SequenceScope;", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m18k = 3, m17mv = {1, 5, 1})
@DebugMetadata(m11c = "kotlin.sequences.SequencesKt___SequencesKt$runningFold$1", m10f = "_Sequences.kt", m9i = {0, 1}, m8l = {2115, 2119}, m7m = "invokeSuspend", m6n = {"$this$sequence", "$this$sequence"}, m5s = {"L$0", "L$0"})
/* loaded from: classes.dex */
public final class SequencesKt___SequencesKt$runningFold$1<R> extends RestrictedSuspendLambda implements Function2<SequenceScope<? super R>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Object $initial;
    final /* synthetic */ Function2 $operation;
    final /* synthetic */ Sequence $this_runningFold;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    int label;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SequencesKt___SequencesKt$runningFold$1(Sequence sequence, Object obj, Function2 function2, Continuation continuation) {
        super(2, continuation);
        this.$this_runningFold = sequence;
        this.$initial = obj;
        this.$operation = function2;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkNotNullParameter(completion, "completion");
        SequencesKt___SequencesKt$runningFold$1 sequencesKt___SequencesKt$runningFold$1 = new SequencesKt___SequencesKt$runningFold$1(this.$this_runningFold, this.$initial, this.$operation, completion);
        sequencesKt___SequencesKt$runningFold$1.L$0 = obj;
        return sequencesKt___SequencesKt$runningFold$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object obj, Continuation<? super Unit> continuation) {
        return ((SequencesKt___SequencesKt$runningFold$1) create(obj, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object $result) {
        SequencesKt___SequencesKt$runningFold$1 sequencesKt___SequencesKt$runningFold$1;
        SequenceScope $this$sequence;
        Object accumulator;
        SequenceScope $this$sequence2;
        Iterator it;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        switch (this.label) {
            case 0:
                ResultKt.throwOnFailure($result);
                sequencesKt___SequencesKt$runningFold$1 = this;
                $this$sequence = (SequenceScope) sequencesKt___SequencesKt$runningFold$1.L$0;
                Object obj = sequencesKt___SequencesKt$runningFold$1.$initial;
                sequencesKt___SequencesKt$runningFold$1.L$0 = $this$sequence;
                sequencesKt___SequencesKt$runningFold$1.label = 1;
                if ($this$sequence.yield(obj, sequencesKt___SequencesKt$runningFold$1) == coroutine_suspended) {
                    return coroutine_suspended;
                }
                accumulator = sequencesKt___SequencesKt$runningFold$1.$initial;
                $this$sequence2 = $this$sequence;
                it = sequencesKt___SequencesKt$runningFold$1.$this_runningFold.iterator();
                break;
            case 1:
                sequencesKt___SequencesKt$runningFold$1 = this;
                $this$sequence = (SequenceScope) sequencesKt___SequencesKt$runningFold$1.L$0;
                ResultKt.throwOnFailure($result);
                accumulator = sequencesKt___SequencesKt$runningFold$1.$initial;
                $this$sequence2 = $this$sequence;
                it = sequencesKt___SequencesKt$runningFold$1.$this_runningFold.iterator();
                break;
            case 2:
                sequencesKt___SequencesKt$runningFold$1 = this;
                it = (Iterator) sequencesKt___SequencesKt$runningFold$1.L$2;
                accumulator = sequencesKt___SequencesKt$runningFold$1.L$1;
                $this$sequence2 = (SequenceScope) sequencesKt___SequencesKt$runningFold$1.L$0;
                ResultKt.throwOnFailure($result);
                break;
            default:
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        while (it.hasNext()) {
            Object element = it.next();
            Object invoke = sequencesKt___SequencesKt$runningFold$1.$operation.invoke(accumulator, element);
            sequencesKt___SequencesKt$runningFold$1.L$0 = $this$sequence2;
            sequencesKt___SequencesKt$runningFold$1.L$1 = invoke;
            sequencesKt___SequencesKt$runningFold$1.L$2 = it;
            sequencesKt___SequencesKt$runningFold$1.label = 2;
            if ($this$sequence2.yield(invoke, sequencesKt___SequencesKt$runningFold$1) == coroutine_suspended) {
                return coroutine_suspended;
            }
            accumulator = invoke;
        }
        return Unit.INSTANCE;
    }
}