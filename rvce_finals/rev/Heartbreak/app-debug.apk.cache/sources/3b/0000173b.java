package kotlinx.coroutines.channels;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: Channels.common.kt */
@Metadata(m27k = 3, m26mv = {1, 6, 0}, m24xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
@DebugMetadata(m14c = "kotlinx.coroutines.channels.ChannelsKt__Channels_commonKt", m13f = "Channels.common.kt", m12i = {0, 0}, m11l = {148}, m10m = "toList", m9n = {"$this$toList_u24lambda_u2d3", "$this$consume$iv$iv"}, m8s = {"L$1", "L$2"})
/* loaded from: classes.dex */
public final class ChannelsKt__Channels_commonKt$toList$1<E> extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;

    /* JADX INFO: Access modifiers changed from: package-private */
    public ChannelsKt__Channels_commonKt$toList$1(Continuation<? super ChannelsKt__Channels_commonKt$toList$1> continuation) {
        super(continuation);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ChannelsKt.toList(null, this);
    }
}