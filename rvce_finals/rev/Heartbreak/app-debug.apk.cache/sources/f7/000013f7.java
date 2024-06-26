package kotlin.coroutines.jvm.internal;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.debug.internal.DebugProbesImpl;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Classes with same name are omitted:
  classes.dex
 */
@Metadata(m26mv = {1, 6, 0}, m27k = 2, m24xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE, m29d1 = {"��\u0012\n��\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\u001a\"\u0010��\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b��\u0010\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001H��\u001a\u0014\u0010\u0004\u001a\u00020\u00052\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0001H��\u001a\u0014\u0010\u0007\u001a\u00020\u00052\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0001H��¨\u0006\b"}, m28d2 = {"probeCoroutineCreated", "Lkotlin/coroutines/Continuation;", "T", "completion", "probeCoroutineResumed", "", TypedValues.AttributesType.S_FRAME, "probeCoroutineSuspended", "kotlinx-coroutines-integration-testing_debugAgentTest"})
/* renamed from: kotlin.coroutines.jvm.internal.DebugProbesKt */
/* loaded from: app-debug.apk:DebugProbesKt.bin */
public final class DebugProbes {
    @NotNull
    public static final <T> Continuation<T> probeCoroutineCreated(@NotNull Continuation<? super T> completion) {
        Intrinsics.checkNotNullParameter(completion, "completion");
        return DebugProbesImpl.INSTANCE.probeCoroutineCreated$kotlinx_coroutines_core(completion);
    }

    public static final void probeCoroutineResumed(@NotNull Continuation<?> frame) {
        Intrinsics.checkNotNullParameter(frame, "frame");
        DebugProbesImpl.INSTANCE.probeCoroutineResumed$kotlinx_coroutines_core(frame);
    }

    public static final void probeCoroutineSuspended(@NotNull Continuation<?> frame) {
        Intrinsics.checkNotNullParameter(frame, "frame");
        DebugProbesImpl.INSTANCE.probeCoroutineSuspended$kotlinx_coroutines_core(frame);
    }
}