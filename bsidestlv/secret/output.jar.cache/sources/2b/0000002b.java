package androidx.activity.contextaware;

import android.content.Context;

/* loaded from: output.jar:androidx/activity/contextaware/ContextAware.class */
public interface ContextAware {
    void addOnContextAvailableListener(OnContextAvailableListener onContextAvailableListener);

    Context peekAvailableContext();

    void removeOnContextAvailableListener(OnContextAvailableListener onContextAvailableListener);
}