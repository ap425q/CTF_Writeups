package androidx.savedstate;

import androidx.lifecycle.LifecycleOwner;

/* loaded from: output.jar:androidx/savedstate/SavedStateRegistryOwner.class */
public interface SavedStateRegistryOwner extends LifecycleOwner {
    SavedStateRegistry getSavedStateRegistry();
}