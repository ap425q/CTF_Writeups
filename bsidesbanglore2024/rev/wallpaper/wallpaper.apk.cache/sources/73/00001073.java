package kotlin.text;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.RestrictedSuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequenceScope;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Regex.kt */
@Metadata(m20d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u008a@¢\u0006\u0004\b\u0004\u0010\u0005"}, m19d2 = {"<anonymous>", "", "Lkotlin/sequences/SequenceScope;", "", "invoke", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"}, m18k = 3, m17mv = {1, 5, 1})
@DebugMetadata(m11c = "kotlin.text.Regex$splitToSequence$1", m10f = "Regex.kt", m9i = {}, m8l = {243, 251, 255}, m7m = "invokeSuspend", m6n = {}, m5s = {})
/* loaded from: classes.dex */
public final class Regex$splitToSequence$1 extends RestrictedSuspendLambda implements Function2<SequenceScope<? super String>, Continuation<? super Unit>, Object> {
    final /* synthetic */ CharSequence $input;
    final /* synthetic */ int $limit;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ Regex this$0;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Regex$splitToSequence$1(Regex regex, CharSequence charSequence, int i, Continuation continuation) {
        super(2, continuation);
        this.this$0 = regex;
        this.$input = charSequence;
        this.$limit = i;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> completion) {
        Intrinsics.checkNotNullParameter(completion, "completion");
        Regex$splitToSequence$1 regex$splitToSequence$1 = new Regex$splitToSequence$1(this.this$0, this.$input, this.$limit, completion);
        regex$splitToSequence$1.L$0 = obj;
        return regex$splitToSequence$1;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(SequenceScope<? super String> sequenceScope, Continuation<? super Unit> continuation) {
        return ((Regex$splitToSequence$1) create(sequenceScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARN: Removed duplicated region for block: B:17:0x0070 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:20:0x007b  */
    /* JADX WARN: Removed duplicated region for block: B:26:0x009f A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:27:0x00a0  */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:16:0x006e -> B:18:0x0071). Please submit an issue!!! */
    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object invokeSuspend(java.lang.Object r11) {
        /*
            r10 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r10.label
            r2 = 1
            switch(r1) {
                case 0: goto L2d;
                case 1: goto L27;
                case 2: goto L18;
                case 3: goto L12;
                default: goto La;
            }
        La:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L12:
            r0 = r10
            kotlin.ResultKt.throwOnFailure(r11)
            goto La1
        L18:
            r1 = r10
            int r3 = r1.I$0
            java.lang.Object r4 = r1.L$1
            java.util.regex.Matcher r4 = (java.util.regex.Matcher) r4
            java.lang.Object r5 = r1.L$0
            kotlin.sequences.SequenceScope r5 = (kotlin.sequences.SequenceScope) r5
            kotlin.ResultKt.throwOnFailure(r11)
            goto L71
        L27:
            r0 = r10
            kotlin.ResultKt.throwOnFailure(r11)
            goto Lb4
        L2d:
            kotlin.ResultKt.throwOnFailure(r11)
            r1 = r10
            java.lang.Object r3 = r1.L$0
            kotlin.sequences.SequenceScope r3 = (kotlin.sequences.SequenceScope) r3
            kotlin.text.Regex r4 = r1.this$0
            java.util.regex.Pattern r4 = kotlin.text.Regex.access$getNativePattern$p(r4)
            java.lang.CharSequence r5 = r1.$input
            java.util.regex.Matcher r4 = r4.matcher(r5)
            int r5 = r1.$limit
            if (r5 == r2) goto La4
            boolean r5 = r4.find()
            if (r5 != 0) goto L4c
            goto La4
        L4c:
            r5 = 0
            r6 = 0
            r9 = r5
            r5 = r3
            r3 = r6
            r6 = r9
        L52:
            java.lang.CharSequence r7 = r1.$input
            int r8 = r4.start()
            java.lang.CharSequence r7 = r7.subSequence(r6, r8)
            java.lang.String r6 = r7.toString()
            r1.L$0 = r5
            r1.L$1 = r4
            r1.I$0 = r3
            r7 = 2
            r1.label = r7
            java.lang.Object r6 = r5.yield(r6, r1)
            if (r6 != r0) goto L71
            return r0
        L71:
            int r6 = r4.end()
            int r3 = r3 + r2
            int r7 = r1.$limit
            int r7 = r7 - r2
            if (r3 == r7) goto L83
            boolean r7 = r4.find()
            if (r7 != 0) goto L82
            goto L83
        L82:
            goto L52
        L83:
            java.lang.CharSequence r2 = r1.$input
            int r3 = r2.length()
            java.lang.CharSequence r2 = r2.subSequence(r6, r3)
            java.lang.String r2 = r2.toString()
            r3 = 0
            r1.L$0 = r3
            r1.L$1 = r3
            r3 = 3
            r1.label = r3
            java.lang.Object r2 = r5.yield(r2, r1)
            if (r2 != r0) goto La0
            return r0
        La0:
            r0 = r1
        La1:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        La4:
            java.lang.CharSequence r4 = r1.$input
            java.lang.String r4 = r4.toString()
            r1.label = r2
            java.lang.Object r2 = r3.yield(r4, r1)
            if (r2 != r0) goto Lb3
            return r0
        Lb3:
            r0 = r1
        Lb4:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlin.text.Regex$splitToSequence$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}