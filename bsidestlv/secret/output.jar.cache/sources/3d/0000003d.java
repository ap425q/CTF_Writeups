package androidx.activity.result.contract;

import android.content.Context;
import android.content.Intent;

/* loaded from: output.jar:androidx/activity/result/contract/ActivityResultContract.class */
public abstract class ActivityResultContract<I, O> {

    /* loaded from: output.jar:androidx/activity/result/contract/ActivityResultContract$SynchronousResult.class */
    public static final class SynchronousResult<T> {
        private final T mValue;

        public SynchronousResult(T t) {
            this.mValue = t;
        }

        public T getValue() {
            return this.mValue;
        }
    }

    public abstract Intent createIntent(Context context, I i);

    public SynchronousResult<O> getSynchronousResult(Context context, I i) {
        return null;
    }

    public abstract O parseResult(int i, Intent intent);
}