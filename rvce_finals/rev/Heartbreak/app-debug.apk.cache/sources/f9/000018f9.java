package kotlinx.coroutines.flow.internal;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: FlowExceptions.common.kt */
@Metadata(m29d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0011\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0001H\u0081\b\u001a\u0018\u0010\u0003\u001a\u00020\u0004*\u00020\u00052\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0007H\u0000¨\u0006\b"}, m28d2 = {"checkIndexOverflow", "", "index", "checkOwnership", "", "Lkotlinx/coroutines/flow/internal/AbortFlowException;", "owner", "Lkotlinx/coroutines/flow/FlowCollector;", "kotlinx-coroutines-core"}, m27k = 2, m26mv = {1, 6, 0}, m24xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* loaded from: classes.dex */
public final class FlowExceptions_commonKt {
    public static final void checkOwnership(FlowExceptions $this$checkOwnership, FlowCollector<?> flowCollector) {
        if ($this$checkOwnership.owner != flowCollector) {
            throw $this$checkOwnership;
        }
    }

    public static final int checkIndexOverflow(int index) {
        if (index < 0) {
            throw new ArithmeticException("Index overflow has happened");
        }
        return index;
    }
}