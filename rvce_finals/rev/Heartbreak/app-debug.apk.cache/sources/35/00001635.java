package kotlin.time;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.location.LocationRequestCompat;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DurationUnit.kt */
@Metadata(m29d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0001\u001a\u0010\u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0007\u001a\u00020\bH\u0001\u001a\f\u0010\u0007\u001a\u00020\b*\u00020\u0001H\u0001¨\u0006\t"}, m28d2 = {"durationUnitByIsoChar", "Lkotlin/time/DurationUnit;", "isoChar", "", "isTimeComponent", "", "durationUnitByShortName", "shortName", "", "kotlin-stdlib"}, m27k = 5, m26mv = {1, 8, 0}, m24xi = ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX, m23xs = "kotlin/time/DurationUnitKt")
/* loaded from: classes.dex */
class DurationUnitKt__DurationUnitKt extends DurationUnitKt__DurationUnitJvmKt {

    /* compiled from: DurationUnit.kt */
    @Metadata(m27k = 3, m26mv = {1, 8, 0}, m24xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[DurationUnitJvm.values().length];
            try {
                iArr[DurationUnitJvm.NANOSECONDS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[DurationUnitJvm.MICROSECONDS.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[DurationUnitJvm.MILLISECONDS.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[DurationUnitJvm.SECONDS.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[DurationUnitJvm.MINUTES.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[DurationUnitJvm.HOURS.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[DurationUnitJvm.DAYS.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final String shortName(DurationUnitJvm $this$shortName) {
        Intrinsics.checkNotNullParameter($this$shortName, "<this>");
        switch (WhenMappings.$EnumSwitchMapping$0[$this$shortName.ordinal()]) {
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
                throw new IllegalStateException(("Unknown unit: " + $this$shortName).toString());
        }
    }

    public static final DurationUnitJvm durationUnitByShortName(String shortName) {
        Intrinsics.checkNotNullParameter(shortName, "shortName");
        switch (shortName.hashCode()) {
            case 100:
                if (shortName.equals("d")) {
                    return DurationUnitJvm.DAYS;
                }
                break;
            case LocationRequestCompat.QUALITY_LOW_POWER /* 104 */:
                if (shortName.equals("h")) {
                    return DurationUnitJvm.HOURS;
                }
                break;
            case AppCompatDelegate.FEATURE_SUPPORT_ACTION_BAR_OVERLAY /* 109 */:
                if (shortName.equals("m")) {
                    return DurationUnitJvm.MINUTES;
                }
                break;
            case 115:
                if (shortName.equals("s")) {
                    return DurationUnitJvm.SECONDS;
                }
                break;
            case 3494:
                if (shortName.equals("ms")) {
                    return DurationUnitJvm.MILLISECONDS;
                }
                break;
            case 3525:
                if (shortName.equals("ns")) {
                    return DurationUnitJvm.NANOSECONDS;
                }
                break;
            case 3742:
                if (shortName.equals("us")) {
                    return DurationUnitJvm.MICROSECONDS;
                }
                break;
        }
        throw new IllegalArgumentException("Unknown duration unit short name: " + shortName);
    }

    public static final DurationUnitJvm durationUnitByIsoChar(char isoChar, boolean isTimeComponent) {
        if (!isTimeComponent) {
            if (isoChar == 'D') {
                return DurationUnitJvm.DAYS;
            }
            throw new IllegalArgumentException("Invalid or unsupported duration ISO non-time unit: " + isoChar);
        } else if (isoChar == 'H') {
            return DurationUnitJvm.HOURS;
        } else {
            if (isoChar == 'M') {
                return DurationUnitJvm.MINUTES;
            }
            if (isoChar == 'S') {
                return DurationUnitJvm.SECONDS;
            }
            throw new IllegalArgumentException("Invalid duration ISO time unit: " + isoChar);
        }
    }
}