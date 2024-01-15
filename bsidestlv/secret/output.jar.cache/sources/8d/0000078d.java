package androidx.emoji2.text.flatbuffer;

import androidx.emoji2.text.flatbuffer.Utf8;
import java.nio.ByteBuffer;

/* loaded from: output.jar:androidx/emoji2/text/flatbuffer/Utf8Safe.class */
public final class Utf8Safe extends Utf8 {

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: output.jar:androidx/emoji2/text/flatbuffer/Utf8Safe$UnpairedSurrogateException.class */
    public static class UnpairedSurrogateException extends IllegalArgumentException {
        UnpairedSurrogateException(int i, int i2) {
            super("Unpaired surrogate at index " + i + " of " + i2);
        }
    }

    private static int computeEncodedLength(CharSequence charSequence) {
        int i;
        int length = charSequence.length();
        int i2 = 0;
        while (i2 < length && charSequence.charAt(i2) < 128) {
            i2++;
        }
        int i3 = length;
        while (true) {
            i = i3;
            if (i2 < length) {
                char charAt = charSequence.charAt(i2);
                if (charAt >= 2048) {
                    i = i3 + encodedLengthGeneral(charSequence, i2);
                    break;
                }
                i3 += (127 - charAt) >>> 31;
                i2++;
            } else {
                break;
            }
        }
        if (i >= length) {
            return i;
        }
        throw new IllegalArgumentException("UTF-8 length does not fit in int: " + (i + 4294967296L));
    }

    public static String decodeUtf8Array(byte[] bArr, int i, int i2) {
        if ((i | i2 | ((bArr.length - i) - i2)) >= 0) {
            int i3 = i + i2;
            char[] cArr = new char[i2];
            int i4 = 0;
            while (i < i3) {
                byte b = bArr[i];
                if (!Utf8.DecodeUtil.isOneByte(b)) {
                    break;
                }
                i++;
                Utf8.DecodeUtil.handleOneByte(b, cArr, i4);
                i4++;
            }
            int i5 = i4;
            int i6 = i;
            int i7 = i5;
            while (i6 < i3) {
                int i8 = i6 + 1;
                byte b2 = bArr[i6];
                if (Utf8.DecodeUtil.isOneByte(b2)) {
                    Utf8.DecodeUtil.handleOneByte(b2, cArr, i7);
                    i7++;
                    i6 = i8;
                    while (i6 < i3) {
                        byte b3 = bArr[i6];
                        if (!Utf8.DecodeUtil.isOneByte(b3)) {
                            break;
                        }
                        i6++;
                        Utf8.DecodeUtil.handleOneByte(b3, cArr, i7);
                        i7++;
                    }
                } else if (Utf8.DecodeUtil.isTwoBytes(b2)) {
                    if (i8 >= i3) {
                        throw new IllegalArgumentException("Invalid UTF-8");
                    }
                    Utf8.DecodeUtil.handleTwoBytes(b2, bArr[i8], cArr, i7);
                    i6 = i8 + 1;
                    i7++;
                } else if (Utf8.DecodeUtil.isThreeBytes(b2)) {
                    if (i8 >= i3 - 1) {
                        throw new IllegalArgumentException("Invalid UTF-8");
                    }
                    int i9 = i8 + 1;
                    Utf8.DecodeUtil.handleThreeBytes(b2, bArr[i8], bArr[i9], cArr, i7);
                    i6 = i9 + 1;
                    i7++;
                } else if (i8 >= i3 - 2) {
                    throw new IllegalArgumentException("Invalid UTF-8");
                } else {
                    int i10 = i8 + 1;
                    byte b4 = bArr[i8];
                    int i11 = i10 + 1;
                    Utf8.DecodeUtil.handleFourBytes(b2, b4, bArr[i10], bArr[i11], cArr, i7);
                    i6 = i11 + 1;
                    i7 = i7 + 1 + 1;
                }
            }
            return new String(cArr, 0, i7);
        }
        throw new ArrayIndexOutOfBoundsException(String.format("buffer length=%d, index=%d, size=%d", Integer.valueOf(bArr.length), Integer.valueOf(i), Integer.valueOf(i2)));
    }

    public static String decodeUtf8Buffer(ByteBuffer byteBuffer, int i, int i2) {
        if ((i | i2 | ((byteBuffer.limit() - i) - i2)) >= 0) {
            int i3 = i + i2;
            char[] cArr = new char[i2];
            int i4 = 0;
            while (i < i3) {
                byte b = byteBuffer.get(i);
                if (!Utf8.DecodeUtil.isOneByte(b)) {
                    break;
                }
                i++;
                Utf8.DecodeUtil.handleOneByte(b, cArr, i4);
                i4++;
            }
            int i5 = i4;
            int i6 = i;
            int i7 = i5;
            while (i6 < i3) {
                int i8 = i6 + 1;
                byte b2 = byteBuffer.get(i6);
                if (Utf8.DecodeUtil.isOneByte(b2)) {
                    Utf8.DecodeUtil.handleOneByte(b2, cArr, i7);
                    i7++;
                    i6 = i8;
                    while (i6 < i3) {
                        byte b3 = byteBuffer.get(i6);
                        if (!Utf8.DecodeUtil.isOneByte(b3)) {
                            break;
                        }
                        i6++;
                        Utf8.DecodeUtil.handleOneByte(b3, cArr, i7);
                        i7++;
                    }
                } else if (Utf8.DecodeUtil.isTwoBytes(b2)) {
                    if (i8 >= i3) {
                        throw new IllegalArgumentException("Invalid UTF-8");
                    }
                    Utf8.DecodeUtil.handleTwoBytes(b2, byteBuffer.get(i8), cArr, i7);
                    i6 = i8 + 1;
                    i7++;
                } else if (Utf8.DecodeUtil.isThreeBytes(b2)) {
                    if (i8 >= i3 - 1) {
                        throw new IllegalArgumentException("Invalid UTF-8");
                    }
                    int i9 = i8 + 1;
                    Utf8.DecodeUtil.handleThreeBytes(b2, byteBuffer.get(i8), byteBuffer.get(i9), cArr, i7);
                    i6 = i9 + 1;
                    i7++;
                } else if (i8 >= i3 - 2) {
                    throw new IllegalArgumentException("Invalid UTF-8");
                } else {
                    int i10 = i8 + 1;
                    byte b4 = byteBuffer.get(i8);
                    int i11 = i10 + 1;
                    Utf8.DecodeUtil.handleFourBytes(b2, b4, byteBuffer.get(i10), byteBuffer.get(i11), cArr, i7);
                    i6 = i11 + 1;
                    i7 = i7 + 1 + 1;
                }
            }
            return new String(cArr, 0, i7);
        }
        throw new ArrayIndexOutOfBoundsException(String.format("buffer limit=%d, index=%d, limit=%d", Integer.valueOf(byteBuffer.limit()), Integer.valueOf(i), Integer.valueOf(i2)));
    }

    private static int encodeUtf8Array(CharSequence charSequence, byte[] bArr, int i, int i2) {
        int i3;
        int i4;
        int i5;
        char charAt;
        int length = charSequence.length();
        int i6 = i2 + i;
        int i7 = 0;
        while (i7 < length && (i5 = i7 + i) < i6 && (charAt = charSequence.charAt(i7)) < 128) {
            bArr[i5] = (byte) charAt;
            i7++;
        }
        if (i7 == length) {
            return i + length;
        }
        int i8 = i + i7;
        int i9 = i7;
        while (i9 < length) {
            char charAt2 = charSequence.charAt(i9);
            if (charAt2 < 128 && i8 < i6) {
                i3 = i8 + 1;
                bArr[i8] = (byte) charAt2;
            } else if (charAt2 < 2048 && i8 <= i6 - 2) {
                int i10 = i8 + 1;
                bArr[i8] = (byte) ((charAt2 >>> 6) | 960);
                i3 = i10 + 1;
                bArr[i10] = (byte) ((charAt2 & '?') | 128);
            } else if ((charAt2 >= 55296 && 57343 >= charAt2) || i8 > i6 - 3) {
                if (i8 > i6 - 4) {
                    if (55296 > charAt2 || charAt2 > 57343 || ((i4 = i9 + 1) != charSequence.length() && Character.isSurrogatePair(charAt2, charSequence.charAt(i4)))) {
                        throw new ArrayIndexOutOfBoundsException("Failed writing " + charAt2 + " at index " + i8);
                    }
                    throw new UnpairedSurrogateException(i9, length);
                }
                int i11 = i9 + 1;
                if (i11 != charSequence.length()) {
                    char charAt3 = charSequence.charAt(i11);
                    if (Character.isSurrogatePair(charAt2, charAt3)) {
                        int codePoint = Character.toCodePoint(charAt2, charAt3);
                        int i12 = i8 + 1;
                        bArr[i8] = (byte) ((codePoint >>> 18) | 240);
                        int i13 = i12 + 1;
                        bArr[i12] = (byte) (((codePoint >>> 12) & 63) | 128);
                        int i14 = i13 + 1;
                        bArr[i13] = (byte) (((codePoint >>> 6) & 63) | 128);
                        bArr[i14] = (byte) ((codePoint & 63) | 128);
                        i9 = i11;
                        i3 = i14 + 1;
                    } else {
                        i9 = i11;
                    }
                }
                throw new UnpairedSurrogateException(i9 - 1, length);
            } else {
                int i15 = i8 + 1;
                bArr[i8] = (byte) ((charAt2 >>> '\f') | 480);
                int i16 = i15 + 1;
                bArr[i15] = (byte) (((charAt2 >>> 6) & 63) | 128);
                i3 = i16 + 1;
                bArr[i16] = (byte) ((charAt2 & '?') | 128);
            }
            i9++;
            i8 = i3;
        }
        return i8;
    }

    private static void encodeUtf8Buffer(CharSequence charSequence, ByteBuffer byteBuffer) {
        int i;
        int length = charSequence.length();
        int position = byteBuffer.position();
        int i2 = 0;
        while (i2 < length) {
            i = position;
            try {
                char charAt = charSequence.charAt(i2);
                if (charAt >= 128) {
                    break;
                }
                byteBuffer.put(position + i2, (byte) charAt);
                i2++;
            } catch (IndexOutOfBoundsException e) {
                i2 = i2;
                throw new ArrayIndexOutOfBoundsException("Failed writing " + charSequence.charAt(i2) + " at index " + (byteBuffer.position() + Math.max(i2, (i - byteBuffer.position()) + 1)));
            }
        }
        if (i2 == length) {
            byteBuffer.position(position + i2);
            return;
        }
        int i3 = position + i2;
        while (i2 < length) {
            char charAt2 = charSequence.charAt(i2);
            if (charAt2 < 128) {
                byteBuffer.put(i3, (byte) charAt2);
            } else if (charAt2 < 2048) {
                int i4 = i3 + 1;
                try {
                    byteBuffer.put(i3, (byte) ((charAt2 >>> 6) | 192));
                    byteBuffer.put(i4, (byte) ((charAt2 & '?') | 128));
                    i3 = i4;
                } catch (IndexOutOfBoundsException e2) {
                    i = i4;
                    throw new ArrayIndexOutOfBoundsException("Failed writing " + charSequence.charAt(i2) + " at index " + (byteBuffer.position() + Math.max(i2, (i - byteBuffer.position()) + 1)));
                }
            } else if (charAt2 >= 55296 && 57343 >= charAt2) {
                int i5 = i2 + 1;
                if (i5 != length) {
                    int i6 = i3;
                    try {
                        char charAt3 = charSequence.charAt(i5);
                        int i7 = i3;
                        if (Character.isSurrogatePair(charAt2, charAt3)) {
                            int i8 = i3;
                            int codePoint = Character.toCodePoint(charAt2, charAt3);
                            int i9 = i3 + 1;
                            i6 = i9;
                            try {
                                byteBuffer.put(i3, (byte) ((codePoint >>> 18) | 240));
                                int i10 = i9 + 1;
                                byteBuffer.put(i9, (byte) (((codePoint >>> 12) & 63) | 128));
                                i3 = i10 + 1;
                                byteBuffer.put(i10, (byte) (((codePoint >>> 6) & 63) | 128));
                                byteBuffer.put(i3, (byte) ((codePoint & 63) | 128));
                                i2 = i5;
                            } catch (IndexOutOfBoundsException e3) {
                                i = i6;
                                i2 = i5;
                                throw new ArrayIndexOutOfBoundsException("Failed writing " + charSequence.charAt(i2) + " at index " + (byteBuffer.position() + Math.max(i2, (i - byteBuffer.position()) + 1)));
                            }
                        } else {
                            i2 = i5;
                        }
                    } catch (IndexOutOfBoundsException e4) {
                    }
                }
                int i11 = i3;
                UnpairedSurrogateException unpairedSurrogateException = new UnpairedSurrogateException(i2, length);
                int i12 = i3;
                throw unpairedSurrogateException;
            } else {
                int i13 = i3 + 1;
                byteBuffer.put(i3, (byte) ((charAt2 >>> '\f') | 224));
                i3 = i13 + 1;
                byteBuffer.put(i13, (byte) (((charAt2 >>> 6) & 63) | 128));
                byteBuffer.put(i3, (byte) ((charAt2 & '?') | 128));
            }
            i2++;
            i3++;
        }
        byteBuffer.position(i3);
    }

    private static int encodedLengthGeneral(CharSequence charSequence, int i) {
        int i2;
        int length = charSequence.length();
        int i3 = 0;
        while (i < length) {
            char charAt = charSequence.charAt(i);
            if (charAt < 2048) {
                i3 += (127 - charAt) >>> 31;
                i2 = i;
            } else {
                int i4 = i3 + 2;
                i3 = i4;
                i2 = i;
                if (55296 <= charAt) {
                    i3 = i4;
                    i2 = i;
                    if (charAt > 57343) {
                        continue;
                    } else if (Character.codePointAt(charSequence, i) < 65536) {
                        throw new UnpairedSurrogateException(i, length);
                    } else {
                        i2 = i + 1;
                        i3 = i4;
                    }
                } else {
                    continue;
                }
            }
            i = i2 + 1;
        }
        return i3;
    }

    @Override // androidx.emoji2.text.flatbuffer.Utf8
    public String decodeUtf8(ByteBuffer byteBuffer, int i, int i2) throws IllegalArgumentException {
        return byteBuffer.hasArray() ? decodeUtf8Array(byteBuffer.array(), byteBuffer.arrayOffset() + i, i2) : decodeUtf8Buffer(byteBuffer, i, i2);
    }

    @Override // androidx.emoji2.text.flatbuffer.Utf8
    public void encodeUtf8(CharSequence charSequence, ByteBuffer byteBuffer) {
        if (!byteBuffer.hasArray()) {
            encodeUtf8Buffer(charSequence, byteBuffer);
            return;
        }
        int arrayOffset = byteBuffer.arrayOffset();
        byteBuffer.position(encodeUtf8Array(charSequence, byteBuffer.array(), byteBuffer.position() + arrayOffset, byteBuffer.remaining()) - arrayOffset);
    }

    @Override // androidx.emoji2.text.flatbuffer.Utf8
    public int encodedLength(CharSequence charSequence) {
        return computeEncodedLength(charSequence);
    }
}