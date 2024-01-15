package androidx.savedstate;

import android.view.View;
import android.view.ViewParent;

/* loaded from: output.jar:androidx/savedstate/ViewTreeSavedStateRegistryOwner.class */
public final class ViewTreeSavedStateRegistryOwner {
    private ViewTreeSavedStateRegistryOwner() {
    }

    public static SavedStateRegistryOwner get(View view) {
        SavedStateRegistryOwner savedStateRegistryOwner = (SavedStateRegistryOwner) view.getTag(R.id.view_tree_saved_state_registry_owner);
        if (savedStateRegistryOwner != null) {
            return savedStateRegistryOwner;
        }
        ViewParent parent = view.getParent();
        while (true) {
            ViewParent viewParent = parent;
            if (savedStateRegistryOwner != null || !(viewParent instanceof View)) {
                break;
            }
            View view2 = (View) viewParent;
            savedStateRegistryOwner = (SavedStateRegistryOwner) view2.getTag(R.id.view_tree_saved_state_registry_owner);
            parent = view2.getParent();
        }
        return savedStateRegistryOwner;
    }

    public static void set(View view, SavedStateRegistryOwner savedStateRegistryOwner) {
        view.setTag(R.id.view_tree_saved_state_registry_owner, savedStateRegistryOwner);
    }
}