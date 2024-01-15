package androidx.core.os;

import android.os.Message;

/* loaded from: output.jar:androidx/core/os/MessageCompat.class */
public final class MessageCompat {
    private static boolean sTryIsAsynchronous = true;
    private static boolean sTrySetAsynchronous = true;

    private MessageCompat() {
    }

    public static boolean isAsynchronous(Message message) {
        return message.isAsynchronous();
    }

    public static void setAsynchronous(Message message, boolean z) {
        message.setAsynchronous(z);
    }
}