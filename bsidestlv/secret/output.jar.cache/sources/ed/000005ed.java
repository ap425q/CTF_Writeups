package androidx.core.view;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

/* loaded from: output.jar:androidx/core/view/MenuHost.class */
public interface MenuHost {
    void addMenuProvider(MenuProvider menuProvider);

    void addMenuProvider(MenuProvider menuProvider, LifecycleOwner lifecycleOwner);

    void addMenuProvider(MenuProvider menuProvider, LifecycleOwner lifecycleOwner, Lifecycle.State state);

    void invalidateMenu();

    void removeMenuProvider(MenuProvider menuProvider);
}