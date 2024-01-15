package androidx.core.widget;

import android.widget.ListView;

/* loaded from: output.jar:androidx/core/widget/ListViewCompat.class */
public final class ListViewCompat {
    private ListViewCompat() {
    }

    public static boolean canScrollList(ListView listView, int i) {
        return listView.canScrollList(i);
    }

    public static void scrollListBy(ListView listView, int i) {
        listView.scrollListBy(i);
    }
}