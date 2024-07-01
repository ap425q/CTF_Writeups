package kotlin.time.jdk8;

import java.time.Duration;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m20d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0087\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0003\u0010\u0004\u001a\u0015\u0010\u0005\u001a\u00020\u0002*\u00020\u0001H\u0087\bø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0082\u0002\u000b\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001¨\u0006\u0007"}, m19d2 = {"toJavaDuration", "Ljava/time/Duration;", "Lkotlin/time/Duration;", "toJavaDuration-LRDsOJo", "(J)Ljava/time/Duration;", "toKotlinDuration", "(Ljava/time/Duration;)J", "kotlin-stdlib-jdk8"}, m18k = 2, m17mv = {1, 5, 1}, m16pn = "kotlin.time")
/* renamed from: kotlin.time.jdk8.DurationConversionsJDK8Kt */
/* loaded from: classes.dex */
public final class DurationConversions {
    private static final long toKotlinDuration(Duration $this$toKotlinDuration) {
        return kotlin.time.Duration.m1391plusLRDsOJo(kotlin.time.Duration.Companion.m1437secondsUwyO8pc($this$toKotlinDuration.getSeconds()), kotlin.time.Duration.Companion.m1429nanosecondsUwyO8pc($this$toKotlinDuration.getNano()));
    }

    /* renamed from: toJavaDuration-LRDsOJo  reason: not valid java name */
    private static final Duration m1447toJavaDurationLRDsOJo(long $this$toJavaDuration) {
        long seconds = kotlin.time.Duration.m1376getInWholeSecondsimpl($this$toJavaDuration);
        int nanoseconds = kotlin.time.Duration.m1378getNanosecondsComponentimpl($this$toJavaDuration);
        Duration ofSeconds = Duration.ofSeconds(seconds, nanoseconds);
        Intrinsics.checkNotNullExpressionValue(ofSeconds, "toComponents { seconds, …, nanoseconds.toLong()) }");
        return ofSeconds;
    }
}