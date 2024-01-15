package androidx.appcompat.app;

import androidx.appcompat.view.ActionMode;

/* loaded from: output.jar:androidx/appcompat/app/AppCompatCallback.class */
public interface AppCompatCallback {
    void onSupportActionModeFinished(ActionMode actionMode);

    void onSupportActionModeStarted(ActionMode actionMode);

    ActionMode onWindowStartingSupportActionMode(ActionMode.Callback callback);
}