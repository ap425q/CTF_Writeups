package kotlin.internal;

import kotlin.Metadata;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UnsignedUtils;

@Metadata(m20d1 = {"\u0000 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u001a*\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001H\u0002ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a*\u0010\u0000\u001a\u00020\u00072\u0006\u0010\u0002\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u0007H\u0002ø\u0001\u0000¢\u0006\u0004\b\b\u0010\t\u001a*\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\u00012\u0006\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000eH\u0001ø\u0001\u0000¢\u0006\u0004\b\u000f\u0010\u0006\u001a*\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0010H\u0001ø\u0001\u0000¢\u0006\u0004\b\u0011\u0010\t\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, m19d2 = {"differenceModulo", "Lkotlin/UInt;", "a", "b", "c", "differenceModulo-WZ9TVnA", "(III)I", "Lkotlin/ULong;", "differenceModulo-sambcqE", "(JJJ)J", "getProgressionLastElement", "start", "end", "step", "", "getProgressionLastElement-Nkh28Cs", "", "getProgressionLastElement-7ftBX0g", "kotlin-stdlib"}, m18k = 2, m17mv = {1, 5, 1})
/* renamed from: kotlin.internal.UProgressionUtilKt */
/* loaded from: classes.dex */
public final class UProgressionUtil {
    /* renamed from: differenceModulo-WZ9TVnA  reason: not valid java name */
    private static final int m1258differenceModuloWZ9TVnA(int a, int b, int c) {
        int ac = UnsignedUtils.m418uintRemainderJ1ME1BU(a, c);
        int bc = UnsignedUtils.m418uintRemainderJ1ME1BU(b, c);
        return UInt.m164constructorimpl(UnsignedUtils.uintCompare(ac, bc) >= 0 ? ac - bc : UInt.m164constructorimpl(ac - bc) + c);
    }

    /* renamed from: differenceModulo-sambcqE  reason: not valid java name */
    private static final long m1259differenceModulosambcqE(long a, long b, long c) {
        long ac = UnsignedUtils.m420ulongRemaindereb3DHEI(a, c);
        long bc = UnsignedUtils.m420ulongRemaindereb3DHEI(b, c);
        return ULong.m242constructorimpl(UnsignedUtils.ulongCompare(ac, bc) >= 0 ? ac - bc : ULong.m242constructorimpl(ac - bc) + c);
    }

    /* renamed from: getProgressionLastElement-Nkh28Cs  reason: not valid java name */
    public static final int m1261getProgressionLastElementNkh28Cs(int start, int end, int step) {
        if (step > 0) {
            if (UnsignedUtils.uintCompare(start, end) < 0) {
                return UInt.m164constructorimpl(end - m1258differenceModuloWZ9TVnA(end, start, UInt.m164constructorimpl(step)));
            }
        } else if (step >= 0) {
            throw new IllegalArgumentException("Step is zero.");
        } else {
            if (UnsignedUtils.uintCompare(start, end) > 0) {
                return UInt.m164constructorimpl(m1258differenceModuloWZ9TVnA(start, end, UInt.m164constructorimpl(-step)) + end);
            }
        }
        return end;
    }

    /* renamed from: getProgressionLastElement-7ftBX0g  reason: not valid java name */
    public static final long m1260getProgressionLastElement7ftBX0g(long start, long end, long step) {
        if (step > 0) {
            if (UnsignedUtils.ulongCompare(start, end) < 0) {
                return ULong.m242constructorimpl(end - m1259differenceModulosambcqE(end, start, ULong.m242constructorimpl(step)));
            }
        } else if (step >= 0) {
            throw new IllegalArgumentException("Step is zero.");
        } else {
            if (UnsignedUtils.ulongCompare(start, end) > 0) {
                return ULong.m242constructorimpl(m1259differenceModulosambcqE(start, end, ULong.m242constructorimpl(-step)) + end);
            }
        }
        return end;
    }
}