package kotlinx.coroutines.channels;

import androidx.constraintlayout.widget.ConstraintLayout;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.channels.ChannelResult;

@Metadata(m29d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a%\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0004\u001a\u0002H\u0002H\u0007¢\u0006\u0002\u0010\u0005\u001a,\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007\"\u0004\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0004\u001a\u0002H\u0002ø\u0001\u0000¢\u0006\u0002\u0010\b\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\t"}, m28d2 = {"sendBlocking", "", "E", "Lkotlinx/coroutines/channels/SendChannel;", "element", "(Lkotlinx/coroutines/channels/SendChannel;Ljava/lang/Object;)V", "trySendBlocking", "Lkotlinx/coroutines/channels/ChannelResult;", "(Lkotlinx/coroutines/channels/SendChannel;Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, m27k = 5, m26mv = {1, 6, 0}, m24xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE, m23xs = "kotlinx/coroutines/channels/ChannelsKt")
/* renamed from: kotlinx.coroutines.channels.ChannelsKt__ChannelsKt */
/* loaded from: classes.dex */
final /* synthetic */ class Channels {
    /* JADX WARN: Multi-variable type inference failed */
    public static final <E> Object trySendBlocking(SendChannel<? super E> sendChannel, E e) {
        Object $this$onSuccess_u2dWpGqRn0$iv = sendChannel.mo1726trySendJP2dKIU(e);
        if (!($this$onSuccess_u2dWpGqRn0$iv instanceof ChannelResult.Failed)) {
            Unit unit = (Unit) $this$onSuccess_u2dWpGqRn0$iv;
            return ChannelResult.Companion.m1746successJP2dKIU(Unit.INSTANCE);
        }
        return ((ChannelResult) BuildersKt.runBlocking$default(null, new ChannelsKt__ChannelsKt$trySendBlocking$2(sendChannel, e, null), 1, null)).m1743unboximpl();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Deprecated(level = DeprecationLevel.ERROR, message = "Deprecated in the favour of 'trySendBlocking'. Consider handling the result of 'trySendBlocking' explicitly and rethrow exception if necessary", replaceWith = @ReplaceWith(expression = "trySendBlocking(element)", imports = {}))
    public static final <E> void sendBlocking(SendChannel<? super E> sendChannel, E e) {
        if (ChannelResult.m1741isSuccessimpl(sendChannel.mo1726trySendJP2dKIU(e))) {
            return;
        }
        BuildersKt.runBlocking$default(null, new ChannelsKt__ChannelsKt$sendBlocking$1(sendChannel, e, null), 1, null);
    }
}