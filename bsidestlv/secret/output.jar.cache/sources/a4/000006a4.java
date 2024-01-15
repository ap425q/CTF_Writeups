package androidx.core.widget;

import android.view.View;
import android.widget.PopupMenu;

/* loaded from: output.jar:androidx/core/widget/PopupMenuCompat.class */
public final class PopupMenuCompat {
    private PopupMenuCompat() {
    }

    public static View.OnTouchListener getDragToOpenListener(Object obj) {
        return ((PopupMenu) obj).getDragToOpenListener();
    }
}