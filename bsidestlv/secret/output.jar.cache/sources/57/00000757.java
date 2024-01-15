package androidx.emoji2.text;

import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.util.SparseArray;
import androidx.core.os.TraceCompat;
import androidx.core.util.Preconditions;
import androidx.emoji2.text.flatbuffer.MetadataList;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: output.jar:androidx/emoji2/text/MetadataRepo.class */
public final class MetadataRepo {
    private static final int DEFAULT_ROOT_SIZE = 1024;
    private static final String S_TRACE_CREATE_REPO = "EmojiCompat.MetadataRepo.create";
    private final char[] mEmojiCharArray;
    private final MetadataList mMetadataList;
    private final Node mRootNode = new Node(1024);
    private final Typeface mTypeface;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: output.jar:androidx/emoji2/text/MetadataRepo$Node.class */
    public static class Node {
        private final SparseArray<Node> mChildren;
        private EmojiMetadata mData;

        private Node() {
            this(1);
        }

        Node(int i) {
            this.mChildren = new SparseArray<>(i);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public Node get(int i) {
            SparseArray<Node> sparseArray = this.mChildren;
            return sparseArray == null ? null : sparseArray.get(i);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public final EmojiMetadata getData() {
            return this.mData;
        }

        void put(EmojiMetadata emojiMetadata, int i, int i2) {
            Node node = get(emojiMetadata.getCodepointAt(i));
            Node node2 = node;
            if (node == null) {
                node2 = new Node();
                this.mChildren.put(emojiMetadata.getCodepointAt(i), node2);
            }
            if (i2 > i) {
                node2.put(emojiMetadata, i + 1, i2);
            } else {
                node2.mData = emojiMetadata;
            }
        }
    }

    private MetadataRepo(Typeface typeface, MetadataList metadataList) {
        this.mTypeface = typeface;
        this.mMetadataList = metadataList;
        this.mEmojiCharArray = new char[metadataList.listLength() * 2];
        constructIndex(metadataList);
    }

    private void constructIndex(MetadataList metadataList) {
        int listLength = metadataList.listLength();
        for (int i = 0; i < listLength; i++) {
            EmojiMetadata emojiMetadata = new EmojiMetadata(this, i);
            Character.toChars(emojiMetadata.getId(), this.mEmojiCharArray, i * 2);
            put(emojiMetadata);
        }
    }

    public static MetadataRepo create(AssetManager assetManager, String str) throws IOException {
        try {
            TraceCompat.beginSection(S_TRACE_CREATE_REPO);
            MetadataRepo metadataRepo = new MetadataRepo(Typeface.createFromAsset(assetManager, str), MetadataListReader.read(assetManager, str));
            TraceCompat.endSection();
            return metadataRepo;
        } catch (Throwable th) {
            TraceCompat.endSection();
            throw th;
        }
    }

    public static MetadataRepo create(Typeface typeface) {
        try {
            TraceCompat.beginSection(S_TRACE_CREATE_REPO);
            MetadataRepo metadataRepo = new MetadataRepo(typeface, new MetadataList());
            TraceCompat.endSection();
            return metadataRepo;
        } catch (Throwable th) {
            TraceCompat.endSection();
            throw th;
        }
    }

    public static MetadataRepo create(Typeface typeface, InputStream inputStream) throws IOException {
        try {
            TraceCompat.beginSection(S_TRACE_CREATE_REPO);
            MetadataRepo metadataRepo = new MetadataRepo(typeface, MetadataListReader.read(inputStream));
            TraceCompat.endSection();
            return metadataRepo;
        } catch (Throwable th) {
            TraceCompat.endSection();
            throw th;
        }
    }

    public static MetadataRepo create(Typeface typeface, ByteBuffer byteBuffer) throws IOException {
        try {
            TraceCompat.beginSection(S_TRACE_CREATE_REPO);
            MetadataRepo metadataRepo = new MetadataRepo(typeface, MetadataListReader.read(byteBuffer));
            TraceCompat.endSection();
            return metadataRepo;
        } catch (Throwable th) {
            TraceCompat.endSection();
            throw th;
        }
    }

    public char[] getEmojiCharArray() {
        return this.mEmojiCharArray;
    }

    public MetadataList getMetadataList() {
        return this.mMetadataList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public int getMetadataVersion() {
        return this.mMetadataList.version();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Node getRootNode() {
        return this.mRootNode;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Typeface getTypeface() {
        return this.mTypeface;
    }

    void put(EmojiMetadata emojiMetadata) {
        Preconditions.checkNotNull(emojiMetadata, "emoji metadata cannot be null");
        Preconditions.checkArgument(emojiMetadata.getCodepointsLength() > 0, "invalid metadata codepoint length");
        this.mRootNode.put(emojiMetadata, 0, emojiMetadata.getCodepointsLength() - 1);
    }
}