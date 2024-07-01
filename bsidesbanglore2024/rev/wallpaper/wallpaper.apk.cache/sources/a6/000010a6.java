package kotlin.time;

import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.DurationUnitKt;

@Metadata(m20d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u001a\u001c\u0010\u0000\u001a\u00060\u0001j\u0002`\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0001\u001a\u0014\u0010\u0007\u001a\u00060\u0001j\u0002`\u00022\u0006\u0010\b\u001a\u00020\tH\u0001\u001a\u0010\u0010\b\u001a\u00020\t*\u00060\u0001j\u0002`\u0002H\u0001¨\u0006\n"}, m19d2 = {"durationUnitByIsoChar", "Ljava/util/concurrent/TimeUnit;", "Lkotlin/time/DurationUnit;", "isoChar", "", "isTimeComponent", "", "durationUnitByShortName", "shortName", "", "kotlin-stdlib"}, m18k = 5, m17mv = {1, 5, 1}, m15xi = 1, m14xs = "kotlin/time/DurationUnitKt")
/* renamed from: kotlin.time.DurationUnitKt__DurationUnitKt */
/* loaded from: classes.dex */
class DurationUnit extends DurationUnitJvm {
    public static final String shortName(TimeUnit shortName) {
        Intrinsics.checkNotNullParameter(shortName, "$this$shortName");
        switch (DurationUnitKt.WhenMappings.$EnumSwitchMapping$0[shortName.ordinal()]) {
            case 1:
                return "ns";
            case 2:
                return "us";
            case 3:
                return "ms";
            case 4:
                return "s";
            case 5:
                return "m";
            case 6:
                return "h";
            case 7:
                return "d";
            default:
                throw new IllegalStateException(("Unknown unit: " + shortName).toString());
        }
    }

    public static final TimeUnit durationUnitByShortName(String shortName) {
        Intrinsics.checkNotNullParameter(shortName, "shortName");
        switch (shortName.hashCode()) {
            case 100:
                if (shortName.equals("d")) {
                    return TimeUnit.DAYS;
                }
                break;
            case 104:
                if (shortName.equals("h")) {
                    return TimeUnit.HOURS;
                }
                break;
            case 109:
                if (shortName.equals("m")) {
                    return TimeUnit.MINUTES;
                }
                break;
            case 115:
                if (shortName.equals("s")) {
                    return TimeUnit.SECONDS;
                }
                break;
            case 3494:
                if (shortName.equals("ms")) {
                    return TimeUnit.MILLISECONDS;
                }
                break;
            case 3525:
                if (shortName.equals("ns")) {
                    return TimeUnit.NANOSECONDS;
                }
                break;
            case 3742:
                if (shortName.equals("us")) {
                    return TimeUnit.MICROSECONDS;
                }
                break;
        }
        throw new IllegalArgumentException("Unknown duration unit short name: " + shortName);
    }

    public static final TimeUnit durationUnitByIsoChar(char isoChar, boolean isTimeComponent) {
        if (!isTimeComponent) {
            switch (isoChar) {
                case 'D':
                    return TimeUnit.DAYS;
                default:
                    throw new IllegalArgumentException("Invalid or unsupported duration ISO non-time unit: " + isoChar);
            }
        }
        switch (isoChar) {
            case 'H':
                return TimeUnit.HOURS;
            case 'M':
                return TimeUnit.MINUTES;
            case 'S':
                return TimeUnit.SECONDS;
            default:
                throw new IllegalArgumentException("Invalid duration ISO time unit: " + isoChar);
        }
    }
}