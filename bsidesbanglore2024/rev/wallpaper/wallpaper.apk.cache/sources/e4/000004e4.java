package androidx.core.net;

import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m20d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\r\u0010\u0003\u001a\u00020\u0002*\u00020\u0001H\u0086\b\u001a\r\u0010\u0003\u001a\u00020\u0002*\u00020\u0004H\u0086\b¨\u0006\u0005"}, m19d2 = {"toFile", "Ljava/io/File;", "Landroid/net/Uri;", "toUri", "", "core-ktx_release"}, m18k = 2, m17mv = {1, 5, 1}, m15xi = 48)
/* renamed from: androidx.core.net.UriKt */
/* loaded from: classes.dex */
public final class Uri {
    public static final android.net.Uri toUri(String $this$toUri) {
        Intrinsics.checkNotNullParameter($this$toUri, "<this>");
        android.net.Uri parse = android.net.Uri.parse($this$toUri);
        Intrinsics.checkNotNullExpressionValue(parse, "parse(this)");
        return parse;
    }

    public static final android.net.Uri toUri(File $this$toUri) {
        Intrinsics.checkNotNullParameter($this$toUri, "<this>");
        android.net.Uri fromFile = android.net.Uri.fromFile($this$toUri);
        Intrinsics.checkNotNullExpressionValue(fromFile, "fromFile(this)");
        return fromFile;
    }

    public static final File toFile(android.net.Uri $this$toFile) {
        Intrinsics.checkNotNullParameter($this$toFile, "<this>");
        if (!Intrinsics.areEqual($this$toFile.getScheme(), "file")) {
            throw new IllegalArgumentException(Intrinsics.stringPlus("Uri lacks 'file' scheme: ", $this$toFile).toString());
        }
        String path = $this$toFile.getPath();
        if (path != null) {
            return new File(path);
        }
        throw new IllegalArgumentException(Intrinsics.stringPlus("Uri path is null: ", $this$toFile).toString());
    }
}