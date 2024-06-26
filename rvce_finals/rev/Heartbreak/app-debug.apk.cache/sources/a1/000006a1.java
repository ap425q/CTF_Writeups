package androidx.core.net;

import androidx.constraintlayout.widget.ConstraintLayout;
import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(m29d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\r\u0010\u0003\u001a\u00020\u0002*\u00020\u0001H\u0086\b\u001a\r\u0010\u0003\u001a\u00020\u0002*\u00020\u0004H\u0086\b¨\u0006\u0005"}, m28d2 = {"toFile", "Ljava/io/File;", "Landroid/net/Uri;", "toUri", "", "core-ktx_release"}, m27k = 2, m26mv = {1, 8, 0}, m24xi = ConstraintLayout.LayoutParams.Table.LAYOUT_CONSTRAINT_VERTICAL_CHAINSTYLE)
/* renamed from: androidx.core.net.UriKt */
/* loaded from: classes.dex */
public final class Uri {
    public static final android.net.Uri toUri(String $this$toUri) {
        return android.net.Uri.parse($this$toUri);
    }

    public static final android.net.Uri toUri(File $this$toUri) {
        return android.net.Uri.fromFile($this$toUri);
    }

    public static final File toFile(android.net.Uri $this$toFile) {
        if (!Intrinsics.areEqual($this$toFile.getScheme(), "file")) {
            throw new IllegalArgumentException(("Uri lacks 'file' scheme: " + $this$toFile).toString());
        }
        String path = $this$toFile.getPath();
        if (path != null) {
            return new File(path);
        }
        throw new IllegalArgumentException(("Uri path is null: " + $this$toFile).toString());
    }
}