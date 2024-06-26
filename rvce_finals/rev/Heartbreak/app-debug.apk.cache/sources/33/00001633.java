package kotlin.time;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DurationUnitJvm.kt */
@Metadata(m29d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a \u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0001\u001a \u0010\u0000\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0001\u001a \u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0002\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H\u0001\u001a\f\u0010\b\u001a\u00020\u0004*\u00020\tH\u0007\u001a\f\u0010\n\u001a\u00020\t*\u00020\u0004H\u0007¨\u0006\u000b"}, m28d2 = {"convertDurationUnit", "", "value", "sourceUnit", "Lkotlin/time/DurationUnit;", "targetUnit", "", "convertDurationUnitOverflow", "toDurationUnit", "Ljava/util/concurrent/TimeUnit;", "toTimeUnit", "kotlin-stdlib"}, m27k = 5, m26mv = {1, 8, 0}, m24xi = ConstraintLayout.LayoutParams.Table.LAYOUT_EDITOR_ABSOLUTEX, m23xs = "kotlin/time/DurationUnitKt")
/* loaded from: classes.dex */
class DurationUnitKt__DurationUnitJvmKt {

    /* compiled from: DurationUnitJvm.kt */
    @Metadata(m27k = 3, m26mv = {1, 8, 0}, m24xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
    /* loaded from: classes.dex */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[TimeUnit.values().length];
            try {
                iArr[TimeUnit.NANOSECONDS.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                iArr[TimeUnit.MICROSECONDS.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                iArr[TimeUnit.MILLISECONDS.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
            try {
                iArr[TimeUnit.SECONDS.ordinal()] = 4;
            } catch (NoSuchFieldError e4) {
            }
            try {
                iArr[TimeUnit.MINUTES.ordinal()] = 5;
            } catch (NoSuchFieldError e5) {
            }
            try {
                iArr[TimeUnit.HOURS.ordinal()] = 6;
            } catch (NoSuchFieldError e6) {
            }
            try {
                iArr[TimeUnit.DAYS.ordinal()] = 7;
            } catch (NoSuchFieldError e7) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final TimeUnit toTimeUnit(DurationUnitJvm $this$toTimeUnit) {
        Intrinsics.checkNotNullParameter($this$toTimeUnit, "<this>");
        return $this$toTimeUnit.getTimeUnit$kotlin_stdlib();
    }

    public static final DurationUnitJvm toDurationUnit(TimeUnit $this$toDurationUnit) {
        Intrinsics.checkNotNullParameter($this$toDurationUnit, "<this>");
        switch (WhenMappings.$EnumSwitchMapping$0[$this$toDurationUnit.ordinal()]) {
            case 1:
                return DurationUnitJvm.NANOSECONDS;
            case 2:
                return DurationUnitJvm.MICROSECONDS;
            case 3:
                return DurationUnitJvm.MILLISECONDS;
            case 4:
                return DurationUnitJvm.SECONDS;
            case 5:
                return DurationUnitJvm.MINUTES;
            case 6:
                return DurationUnitJvm.HOURS;
            case 7:
                return DurationUnitJvm.DAYS;
            default:
                throw new NoWhenBranchMatchedException();
        }
    }

    public static final double convertDurationUnit(double value, DurationUnitJvm sourceUnit, DurationUnitJvm targetUnit) {
        Intrinsics.checkNotNullParameter(sourceUnit, "sourceUnit");
        Intrinsics.checkNotNullParameter(targetUnit, "targetUnit");
        long sourceInTargets = targetUnit.getTimeUnit$kotlin_stdlib().convert(1L, sourceUnit.getTimeUnit$kotlin_stdlib());
        if (sourceInTargets > 0) {
            return sourceInTargets * value;
        }
        long otherInThis = sourceUnit.getTimeUnit$kotlin_stdlib().convert(1L, targetUnit.getTimeUnit$kotlin_stdlib());
        return value / otherInThis;
    }

    public static final long convertDurationUnitOverflow(long value, DurationUnitJvm sourceUnit, DurationUnitJvm targetUnit) {
        Intrinsics.checkNotNullParameter(sourceUnit, "sourceUnit");
        Intrinsics.checkNotNullParameter(targetUnit, "targetUnit");
        return targetUnit.getTimeUnit$kotlin_stdlib().convert(value, sourceUnit.getTimeUnit$kotlin_stdlib());
    }

    public static final long convertDurationUnit(long value, DurationUnitJvm sourceUnit, DurationUnitJvm targetUnit) {
        Intrinsics.checkNotNullParameter(sourceUnit, "sourceUnit");
        Intrinsics.checkNotNullParameter(targetUnit, "targetUnit");
        return targetUnit.getTimeUnit$kotlin_stdlib().convert(value, sourceUnit.getTimeUnit$kotlin_stdlib());
    }
}