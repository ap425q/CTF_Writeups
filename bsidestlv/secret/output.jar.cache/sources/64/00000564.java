package androidx.core.os;

import androidx.core.util.ObjectsCompat;

/* loaded from: output.jar:androidx/core/os/OperationCanceledException.class */
public class OperationCanceledException extends RuntimeException {
    public OperationCanceledException() {
        this(null);
    }

    public OperationCanceledException(String str) {
        super(ObjectsCompat.toString(str, "The operation has been canceled."));
    }
}