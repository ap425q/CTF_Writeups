package kotlin.text;

import androidx.core.internal.view.SupportMenu;
import kotlin.ExceptionsH;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UInt;
import kotlin.ULong;
import kotlin.UShort;
import kotlin.UnsignedUtils;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m20d1 = {"\u0000,\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0013\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b\u0005\u0010\u0006\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b\b\u0010\t\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\n2\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b\u000b\u0010\f\u001a\u001e\u0010\u0000\u001a\u00020\u0001*\u00020\r2\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000f\u001a\u0014\u0010\u0010\u001a\u00020\u0002*\u00020\u0001H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0011\u001a\u001c\u0010\u0010\u001a\u00020\u0002*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0012\u001a\u0011\u0010\u0013\u001a\u0004\u0018\u00010\u0002*\u00020\u0001H\u0007ø\u0001\u0000\u001a\u0019\u0010\u0013\u001a\u0004\u0018\u00010\u0002*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000\u001a\u0014\u0010\u0014\u001a\u00020\u0007*\u00020\u0001H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0015\u001a\u001c\u0010\u0014\u001a\u00020\u0007*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0016\u001a\u0011\u0010\u0017\u001a\u0004\u0018\u00010\u0007*\u00020\u0001H\u0007ø\u0001\u0000\u001a\u0019\u0010\u0017\u001a\u0004\u0018\u00010\u0007*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000\u001a\u0014\u0010\u0018\u001a\u00020\n*\u00020\u0001H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u0019\u001a\u001c\u0010\u0018\u001a\u00020\n*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u001a\u001a\u0011\u0010\u001b\u001a\u0004\u0018\u00010\n*\u00020\u0001H\u0007ø\u0001\u0000\u001a\u0019\u0010\u001b\u001a\u0004\u0018\u00010\n*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000\u001a\u0014\u0010\u001c\u001a\u00020\r*\u00020\u0001H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u001d\u001a\u001c\u0010\u001c\u001a\u00020\r*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\u001e\u001a\u0011\u0010\u001f\u001a\u0004\u0018\u00010\r*\u00020\u0001H\u0007ø\u0001\u0000\u001a\u0019\u0010\u001f\u001a\u0004\u0018\u00010\r*\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\u0007ø\u0001\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006 "}, m19d2 = {"toString", "", "Lkotlin/UByte;", "radix", "", "toString-LxnNnR4", "(BI)Ljava/lang/String;", "Lkotlin/UInt;", "toString-V7xB4Y4", "(II)Ljava/lang/String;", "Lkotlin/ULong;", "toString-JSWoG40", "(JI)Ljava/lang/String;", "Lkotlin/UShort;", "toString-olVBNx4", "(SI)Ljava/lang/String;", "toUByte", "(Ljava/lang/String;)B", "(Ljava/lang/String;I)B", "toUByteOrNull", "toUInt", "(Ljava/lang/String;)I", "(Ljava/lang/String;I)I", "toUIntOrNull", "toULong", "(Ljava/lang/String;)J", "(Ljava/lang/String;I)J", "toULongOrNull", "toUShort", "(Ljava/lang/String;)S", "(Ljava/lang/String;I)S", "toUShortOrNull", "kotlin-stdlib"}, m18k = 2, m17mv = {1, 5, 1})
/* renamed from: kotlin.text.UStringsKt */
/* loaded from: classes.dex */
public final class UStrings {
    /* renamed from: toString-LxnNnR4  reason: not valid java name */
    public static final String m1342toStringLxnNnR4(byte $this$toString, int radix) {
        String num = Integer.toString($this$toString & UByte.MAX_VALUE, CharsKt.checkRadix(radix));
        Intrinsics.checkNotNullExpressionValue(num, "java.lang.Integer.toStri…(this, checkRadix(radix))");
        return num;
    }

    /* renamed from: toString-olVBNx4  reason: not valid java name */
    public static final String m1344toStringolVBNx4(short $this$toString, int radix) {
        String num = Integer.toString(65535 & $this$toString, CharsKt.checkRadix(radix));
        Intrinsics.checkNotNullExpressionValue(num, "java.lang.Integer.toStri…(this, checkRadix(radix))");
        return num;
    }

    /* renamed from: toString-V7xB4Y4  reason: not valid java name */
    public static final String m1343toStringV7xB4Y4(int $this$toString, int radix) {
        String l = Long.toString($this$toString & 4294967295L, CharsKt.checkRadix(radix));
        Intrinsics.checkNotNullExpressionValue(l, "java.lang.Long.toString(this, checkRadix(radix))");
        return l;
    }

    /* renamed from: toString-JSWoG40  reason: not valid java name */
    public static final String m1341toStringJSWoG40(long $this$toString, int radix) {
        return UnsignedUtils.ulongToString($this$toString, CharsKt.checkRadix(radix));
    }

    public static final byte toUByte(String toUByte) {
        Intrinsics.checkNotNullParameter(toUByte, "$this$toUByte");
        UByte uByteOrNull = toUByteOrNull(toUByte);
        if (uByteOrNull != null) {
            return uByteOrNull.m137unboximpl();
        }
        StringsKt.numberFormatError(toUByte);
        throw new ExceptionsH();
    }

    public static final byte toUByte(String toUByte, int radix) {
        Intrinsics.checkNotNullParameter(toUByte, "$this$toUByte");
        UByte uByteOrNull = toUByteOrNull(toUByte, radix);
        if (uByteOrNull != null) {
            return uByteOrNull.m137unboximpl();
        }
        StringsKt.numberFormatError(toUByte);
        throw new ExceptionsH();
    }

    public static final short toUShort(String toUShort) {
        Intrinsics.checkNotNullParameter(toUShort, "$this$toUShort");
        UShort uShortOrNull = toUShortOrNull(toUShort);
        if (uShortOrNull != null) {
            return uShortOrNull.m397unboximpl();
        }
        StringsKt.numberFormatError(toUShort);
        throw new ExceptionsH();
    }

    public static final short toUShort(String toUShort, int radix) {
        Intrinsics.checkNotNullParameter(toUShort, "$this$toUShort");
        UShort uShortOrNull = toUShortOrNull(toUShort, radix);
        if (uShortOrNull != null) {
            return uShortOrNull.m397unboximpl();
        }
        StringsKt.numberFormatError(toUShort);
        throw new ExceptionsH();
    }

    public static final int toUInt(String toUInt) {
        Intrinsics.checkNotNullParameter(toUInt, "$this$toUInt");
        UInt uIntOrNull = toUIntOrNull(toUInt);
        if (uIntOrNull != null) {
            return uIntOrNull.m215unboximpl();
        }
        StringsKt.numberFormatError(toUInt);
        throw new ExceptionsH();
    }

    public static final int toUInt(String toUInt, int radix) {
        Intrinsics.checkNotNullParameter(toUInt, "$this$toUInt");
        UInt uIntOrNull = toUIntOrNull(toUInt, radix);
        if (uIntOrNull != null) {
            return uIntOrNull.m215unboximpl();
        }
        StringsKt.numberFormatError(toUInt);
        throw new ExceptionsH();
    }

    public static final long toULong(String toULong) {
        Intrinsics.checkNotNullParameter(toULong, "$this$toULong");
        ULong uLongOrNull = toULongOrNull(toULong);
        if (uLongOrNull != null) {
            return uLongOrNull.m293unboximpl();
        }
        StringsKt.numberFormatError(toULong);
        throw new ExceptionsH();
    }

    public static final long toULong(String toULong, int radix) {
        Intrinsics.checkNotNullParameter(toULong, "$this$toULong");
        ULong uLongOrNull = toULongOrNull(toULong, radix);
        if (uLongOrNull != null) {
            return uLongOrNull.m293unboximpl();
        }
        StringsKt.numberFormatError(toULong);
        throw new ExceptionsH();
    }

    public static final UByte toUByteOrNull(String toUByteOrNull) {
        Intrinsics.checkNotNullParameter(toUByteOrNull, "$this$toUByteOrNull");
        return toUByteOrNull(toUByteOrNull, 10);
    }

    public static final UByte toUByteOrNull(String toUByteOrNull, int radix) {
        Intrinsics.checkNotNullParameter(toUByteOrNull, "$this$toUByteOrNull");
        UInt uIntOrNull = toUIntOrNull(toUByteOrNull, radix);
        if (uIntOrNull != null) {
            int m215unboximpl = uIntOrNull.m215unboximpl();
            if (UnsignedUtils.uintCompare(m215unboximpl, UInt.m164constructorimpl(255)) > 0) {
                return null;
            }
            return UByte.m82boximpl(UByte.m88constructorimpl((byte) m215unboximpl));
        }
        return null;
    }

    public static final UShort toUShortOrNull(String toUShortOrNull) {
        Intrinsics.checkNotNullParameter(toUShortOrNull, "$this$toUShortOrNull");
        return toUShortOrNull(toUShortOrNull, 10);
    }

    public static final UShort toUShortOrNull(String toUShortOrNull, int radix) {
        Intrinsics.checkNotNullParameter(toUShortOrNull, "$this$toUShortOrNull");
        UInt uIntOrNull = toUIntOrNull(toUShortOrNull, radix);
        if (uIntOrNull != null) {
            int m215unboximpl = uIntOrNull.m215unboximpl();
            if (UnsignedUtils.uintCompare(m215unboximpl, UInt.m164constructorimpl(SupportMenu.USER_MASK)) > 0) {
                return null;
            }
            return UShort.m342boximpl(UShort.m348constructorimpl((short) m215unboximpl));
        }
        return null;
    }

    public static final UInt toUIntOrNull(String toUIntOrNull) {
        Intrinsics.checkNotNullParameter(toUIntOrNull, "$this$toUIntOrNull");
        return toUIntOrNull(toUIntOrNull, 10);
    }

    public static final UInt toUIntOrNull(String toUIntOrNull, int radix) {
        int start;
        Intrinsics.checkNotNullParameter(toUIntOrNull, "$this$toUIntOrNull");
        CharsKt.checkRadix(radix);
        int length = toUIntOrNull.length();
        if (length == 0) {
            return null;
        }
        char firstChar = toUIntOrNull.charAt(0);
        if (Intrinsics.compare((int) firstChar, 48) < 0) {
            if (length == 1 || firstChar != '+') {
                return null;
            }
            start = 1;
        } else {
            start = 0;
        }
        int limitBeforeMul = 119304647;
        int uradix = UInt.m164constructorimpl(radix);
        int result = 0;
        for (int i = start; i < length; i++) {
            int digit = CharsKt.digitOf(toUIntOrNull.charAt(i), radix);
            if (digit < 0) {
                return null;
            }
            if (UnsignedUtils.uintCompare(result, limitBeforeMul) > 0) {
                if (limitBeforeMul != 119304647) {
                    return null;
                }
                limitBeforeMul = UnsignedUtils.m417uintDivideJ1ME1BU(-1, uradix);
                if (UnsignedUtils.uintCompare(result, limitBeforeMul) > 0) {
                    return null;
                }
            }
            int result2 = UInt.m164constructorimpl(result * uradix);
            result = UInt.m164constructorimpl(UInt.m164constructorimpl(digit) + result2);
            if (UnsignedUtils.uintCompare(result, result2) < 0) {
                return null;
            }
        }
        return UInt.m158boximpl(result);
    }

    public static final ULong toULongOrNull(String toULongOrNull) {
        Intrinsics.checkNotNullParameter(toULongOrNull, "$this$toULongOrNull");
        return toULongOrNull(toULongOrNull, 10);
    }

    public static final ULong toULongOrNull(String $this$toULongOrNull, int radix) {
        int start;
        String toULongOrNull = $this$toULongOrNull;
        int i = radix;
        Intrinsics.checkNotNullParameter(toULongOrNull, "$this$toULongOrNull");
        CharsKt.checkRadix(radix);
        int length = $this$toULongOrNull.length();
        if (length == 0) {
            return null;
        }
        char firstChar = toULongOrNull.charAt(0);
        if (Intrinsics.compare((int) firstChar, 48) < 0) {
            if (length == 1 || firstChar != '+') {
                return null;
            }
            start = 1;
        } else {
            start = 0;
        }
        long limitBeforeMul = 512409557603043100L;
        long uradix = ULong.m242constructorimpl(i);
        long result = 0;
        int i2 = start;
        while (i2 < length) {
            int length2 = length;
            int digit = CharsKt.digitOf(toULongOrNull.charAt(i2), i);
            if (digit < 0) {
                return null;
            }
            if (UnsignedUtils.ulongCompare(result, limitBeforeMul) > 0) {
                if (limitBeforeMul == 512409557603043100L) {
                    limitBeforeMul = UnsignedUtils.m419ulongDivideeb3DHEI(-1L, uradix);
                    if (UnsignedUtils.ulongCompare(result, limitBeforeMul) > 0) {
                        return null;
                    }
                } else {
                    return null;
                }
            }
            long result2 = ULong.m242constructorimpl(result * uradix);
            result = ULong.m242constructorimpl(ULong.m242constructorimpl(UInt.m164constructorimpl(digit) & 4294967295L) + result2);
            if (UnsignedUtils.ulongCompare(result, result2) < 0) {
                return null;
            }
            i2++;
            toULongOrNull = $this$toULongOrNull;
            i = radix;
            length = length2;
        }
        return ULong.m236boximpl(result);
    }
}