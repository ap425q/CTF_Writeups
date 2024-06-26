package androidx.core.p003os;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Functions;

@Metadata(m29d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u001a1\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u000e\b\u0004\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0086\b\u001a1\u0010\n\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u000b\u001a\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u000e\b\u0004\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0086\b¨\u0006\f"}, m28d2 = {"postAtTime", "Ljava/lang/Runnable;", "Landroid/os/Handler;", "uptimeMillis", "", "token", "", "action", "Lkotlin/Function0;", "", "postDelayed", "delayInMillis", "core-ktx_release"}, m27k = 2, m26mv = {1, 8, 0}, m24xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* renamed from: androidx.core.os.HandlerKt */
/* loaded from: classes.dex */
public final class Handler {
    public static /* synthetic */ Runnable postDelayed$default(android.os.Handler $this$postDelayed_u24default, long delayInMillis, Object token, Functions action, int i, Object obj) {
        if ((i & 2) != 0) {
            token = null;
        }
        Runnable runnable = new HandlerKt$postDelayed$runnable$1(action);
        if (token == null) {
            $this$postDelayed_u24default.postDelayed(runnable, delayInMillis);
        } else {
            HandlerCompat.postDelayed($this$postDelayed_u24default, runnable, token, delayInMillis);
        }
        return runnable;
    }

    public static final Runnable postDelayed(android.os.Handler $this$postDelayed, long delayInMillis, Object token, Functions<Unit> functions) {
        Runnable runnable = new HandlerKt$postDelayed$runnable$1(functions);
        if (token == null) {
            $this$postDelayed.postDelayed(runnable, delayInMillis);
        } else {
            HandlerCompat.postDelayed($this$postDelayed, runnable, token, delayInMillis);
        }
        return runnable;
    }

    public static /* synthetic */ Runnable postAtTime$default(android.os.Handler $this$postAtTime_u24default, long uptimeMillis, Object token, Functions action, int i, Object obj) {
        if ((i & 2) != 0) {
            token = null;
        }
        Runnable runnable = new HandlerKt$postAtTime$runnable$1(action);
        $this$postAtTime_u24default.postAtTime(runnable, token, uptimeMillis);
        return runnable;
    }

    public static final Runnable postAtTime(android.os.Handler $this$postAtTime, long uptimeMillis, Object token, Functions<Unit> functions) {
        Runnable runnable = new HandlerKt$postAtTime$runnable$1(functions);
        $this$postAtTime.postAtTime(runnable, token, uptimeMillis);
        return runnable;
    }
}