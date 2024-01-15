package androidx.emoji2.text.flatbuffer;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: output.jar:androidx/emoji2/text/flatbuffer/MetadataItem.class */
public final class MetadataItem extends Table {

    /* loaded from: output.jar:androidx/emoji2/text/flatbuffer/MetadataItem$Vector.class */
    public static final class Vector extends BaseVector {
        public Vector __assign(int i, int i2, ByteBuffer byteBuffer) {
            __reset(i, i2, byteBuffer);
            return this;
        }

        public MetadataItem get(int i) {
            return get(new MetadataItem(), i);
        }

        public MetadataItem get(MetadataItem metadataItem, int i) {
            return metadataItem.__assign(MetadataItem.__indirect(__element(i), this.bb), this.bb);
        }
    }

    public static void ValidateVersion() {
        Constants.FLATBUFFERS_1_12_0();
    }

    public static void addCodepoints(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(6, i, 0);
    }

    public static void addCompatAdded(FlatBufferBuilder flatBufferBuilder, short s) {
        flatBufferBuilder.addShort(3, s, 0);
    }

    public static void addEmojiStyle(FlatBufferBuilder flatBufferBuilder, boolean z) {
        flatBufferBuilder.addBoolean(1, z, false);
    }

    public static void addHeight(FlatBufferBuilder flatBufferBuilder, short s) {
        flatBufferBuilder.addShort(5, s, 0);
    }

    public static void addId(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addInt(0, i, 0);
    }

    public static void addSdkAdded(FlatBufferBuilder flatBufferBuilder, short s) {
        flatBufferBuilder.addShort(2, s, 0);
    }

    public static void addWidth(FlatBufferBuilder flatBufferBuilder, short s) {
        flatBufferBuilder.addShort(4, s, 0);
    }

    public static int createCodepointsVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addInt(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static int createMetadataItem(FlatBufferBuilder flatBufferBuilder, int i, boolean z, short s, short s2, short s3, short s4, int i2) {
        flatBufferBuilder.startTable(7);
        addCodepoints(flatBufferBuilder, i2);
        addId(flatBufferBuilder, i);
        addHeight(flatBufferBuilder, s4);
        addWidth(flatBufferBuilder, s3);
        addCompatAdded(flatBufferBuilder, s2);
        addSdkAdded(flatBufferBuilder, s);
        addEmojiStyle(flatBufferBuilder, z);
        return endMetadataItem(flatBufferBuilder);
    }

    public static int endMetadataItem(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endTable();
    }

    public static MetadataItem getRootAsMetadataItem(ByteBuffer byteBuffer) {
        return getRootAsMetadataItem(byteBuffer, new MetadataItem());
    }

    public static MetadataItem getRootAsMetadataItem(ByteBuffer byteBuffer, MetadataItem metadataItem) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return metadataItem.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public static void startCodepointsVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static void startMetadataItem(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startTable(7);
    }

    public MetadataItem __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        __reset(i, byteBuffer);
    }

    public int codepoints(int i) {
        int __offset = __offset(16);
        return __offset != 0 ? this.bb.getInt(__vector(__offset) + (i * 4)) : 0;
    }

    public ByteBuffer codepointsAsByteBuffer() {
        return __vector_as_bytebuffer(16, 4);
    }

    public ByteBuffer codepointsInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 16, 4);
    }

    public int codepointsLength() {
        int __offset = __offset(16);
        return __offset != 0 ? __vector_len(__offset) : 0;
    }

    public IntVector codepointsVector() {
        return codepointsVector(new IntVector());
    }

    public IntVector codepointsVector(IntVector intVector) {
        int __offset = __offset(16);
        return __offset != 0 ? intVector.__assign(__vector(__offset), this.bb) : null;
    }

    public short compatAdded() {
        int __offset = __offset(10);
        return __offset != 0 ? this.bb.getShort(__offset + this.bb_pos) : (short) 0;
    }

    public boolean emojiStyle() {
        int __offset = __offset(6);
        boolean z = false;
        if (__offset != 0) {
            z = false;
            if (this.bb.get(__offset + this.bb_pos) != 0) {
                z = true;
            }
        }
        return z;
    }

    public short height() {
        int __offset = __offset(14);
        return __offset != 0 ? this.bb.getShort(__offset + this.bb_pos) : (short) 0;
    }

    public int id() {
        int __offset = __offset(4);
        return __offset != 0 ? this.bb.getInt(__offset + this.bb_pos) : 0;
    }

    public short sdkAdded() {
        int __offset = __offset(8);
        return __offset != 0 ? this.bb.getShort(__offset + this.bb_pos) : (short) 0;
    }

    public short width() {
        int __offset = __offset(12);
        return __offset != 0 ? this.bb.getShort(__offset + this.bb_pos) : (short) 0;
    }
}