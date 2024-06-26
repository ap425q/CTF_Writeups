package androidx.core.content.p002pm;

import java.util.List;

/* renamed from: androidx.core.content.pm.ShortcutInfoChangeListener */
/* loaded from: classes.dex */
public abstract class ShortcutInfoChangeListener {
    public void onShortcutAdded(List<ShortcutInfoCompat> shortcuts) {
    }

    public void onShortcutUpdated(List<ShortcutInfoCompat> shortcuts) {
    }

    public void onShortcutRemoved(List<String> shortcutIds) {
    }

    public void onAllShortcutsRemoved() {
    }

    public void onShortcutUsageReported(List<String> shortcutIds) {
    }
}