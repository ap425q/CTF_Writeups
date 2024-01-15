package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;

/* loaded from: output.jar:androidx/emoji2/text/flatbuffer/FloatVector.class */
public final class FloatVector extends BaseVector {
    public FloatVector __assign(int i, ByteBuffer byteBuffer) {
        __reset(i, 4, byteBuffer);
        return this;
    }

    public float get(int i) {
        return this.bb.getFloat(__element(i));
    }
}