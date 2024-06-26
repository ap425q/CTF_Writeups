package kotlin.time.jdk8;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.time.Duration;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnitJvm;

@Metadata(m29d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u0015\u0010\u0005\u001a\u00020\u0002*\u00020\u0001H\u0087\bø\u0001\u0001¢\u0006\u0002\u0010\u0006\u0082\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\u0007"}, m28d2 = {"toJavaDuration", "Ljava/time/Duration;", "Lkotlin/time/Duration;", "toJavaDuration-LRDsOJo", "(J)Ljava/time/Duration;", "toKotlinDuration", "(Ljava/time/Duration;)J", "kotlin-stdlib-jdk8"}, m27k = 2, m26mv = {1, 8, 0}, m25pn = "kotlin.time", m24xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* renamed from: kotlin.time.jdk8.DurationConversionsJDK8Kt */
/* loaded from: classes.dex */
public final class DurationConversions {
    private static final long toKotlinDuration(Duration $this$toKotlinDuration) {
        Intrinsics.checkNotNullParameter($this$toKotlinDuration, "<this>");
        return kotlin.time.Duration.m1583plusLRDsOJo(DurationKt.toDuration($this$toKotlinDuration.getSeconds(), DurationUnitJvm.SECONDS), DurationKt.toDuration($this$toKotlinDuration.getNano(), DurationUnitJvm.NANOSECONDS));
    }

    /* renamed from: toJavaDuration-LRDsOJo  reason: not valid java name */
    private static final Duration m1708toJavaDurationLRDsOJo(long $this$toJavaDuration_u2dLRDsOJo) {
        long seconds = kotlin.time.Duration.m1568getInWholeSecondsimpl($this$toJavaDuration_u2dLRDsOJo);
        int nanoseconds = kotlin.time.Duration.m1570getNanosecondsComponentimpl($this$toJavaDuration_u2dLRDsOJo);
        Duration ofSeconds = Duration.ofSeconds(seconds, nanoseconds);
        Intrinsics.checkNotNullExpressionValue(ofSeconds, "toJavaDuration-LRDsOJo");
        return ofSeconds;
    }
}