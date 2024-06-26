package kotlinx.coroutines.internal;

import kotlin.Metadata;
import kotlin.jvm.functions.Functions;
import kotlinx.coroutines.internal.LockFreeLinkedListNode;

/* compiled from: LockFreeLinkedList.kt */
@Metadata(m29d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u0004\u0018\u00010\u00032\n\u0010\u0004\u001a\u00060\u0005j\u0002`\u0006H\u0016¨\u0006\u0007"}, m28d2 = {"kotlinx/coroutines/internal/LockFreeLinkedListNode$makeCondAddOp$1", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode$CondAddOp;", "prepare", "", "affected", "Lkotlinx/coroutines/internal/LockFreeLinkedListNode;", "Lkotlinx/coroutines/internal/Node;", "kotlinx-coroutines-core"}, m27k = 1, m26mv = {1, 6, 0}, m24xi = 176)
/* loaded from: classes.dex */
public final class LockFreeLinkedListNode$makeCondAddOp$1 extends LockFreeLinkedListNode.CondAddOp {
    final /* synthetic */ Functions<Boolean> $condition;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public LockFreeLinkedListNode$makeCondAddOp$1(LockFreeLinkedListNode $node, Functions<Boolean> functions) {
        super($node);
        this.$condition = functions;
    }

    @Override // kotlinx.coroutines.internal.AtomicOp
    public Object prepare(LockFreeLinkedListNode affected) {
        if (this.$condition.invoke().booleanValue()) {
            return null;
        }
        return LockFreeLinkedListKt.getCONDITION_FALSE();
    }
}