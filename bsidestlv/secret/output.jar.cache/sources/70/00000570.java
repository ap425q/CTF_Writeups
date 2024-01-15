package androidx.core.os;

import android.content.Context;
import android.os.UserManager;

/* loaded from: output.jar:androidx/core/os/UserManagerCompat.class */
public class UserManagerCompat {
    private UserManagerCompat() {
    }

    public static boolean isUserUnlocked(Context context) {
        return ((UserManager) context.getSystemService(UserManager.class)).isUserUnlocked();
    }
}